package uz.ipanda.ussd.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.ipanda.ussd.MainActivity
import uz.ipanda.ussd.R
import uz.ipanda.ussd.adapter.OperatorAdapter
import uz.ipanda.ussd.adapter.UssdAdapter
import uz.ipanda.ussd.databinding.FragmentUSSDBinding
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.ussd.Ussd
import java.util.ArrayList


class USSDFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: UssdAdapter
    lateinit var operatorsList: ArrayList<Ussd>
    lateinit var binding: FragmentUSSDBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUSSDBinding.inflate(layoutInflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        operatorsList = ArrayList()
        firestore.collection("ussd").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val operator = documentChange.document.toObject(Ussd::class.java)
                        Log.d("TAG", "onCreateView: $operator")
                        operatorsList.add(operator)
                    }
                }
                adapter = UssdAdapter(operatorsList, object : UssdAdapter.OnItemClickListener {
                    override fun onItemClickListener(ussd: Ussd) {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.setData(Uri.parse("tel:" + "*100" + Uri.encode("#")))
                        startActivity(intent)

                    }

                })
                binding.ussdRv.adapter = adapter


            }

        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "USSD"

    }


}