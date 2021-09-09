package uz.ipanda.ussd.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.ipanda.ussd.adapter.SmsRvAdapter
import uz.ipanda.ussd.databinding.FragmentSmsListBinding
import uz.ipanda.ussd.models.sms.Sms
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SmsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SmsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentSmsListBinding
    lateinit var smsList: ArrayList<Sms>
    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: SmsRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSmsListBinding.inflate(layoutInflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        smsList = ArrayList()
        firestore.collection("sms").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val sms = documentChange.document.toObject(Sms::class.java)
                        Log.d("SMS", "onCreateView: $sms")
                        if (sms.type.equals(param2, ignoreCase = true)) {
                            smsList.add(sms)

                        }

                    }
                }
                adapter = SmsRvAdapter(smsList, object : SmsRvAdapter.OnItemClickListener {
                    override fun onButtonClickListener() {
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.setData(Uri.parse("tel:" + "*100" + Uri.encode("#")))
                        startActivity(intent)
                    }

                })
                binding.rvSms.adapter = adapter


            }


        }



        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SmsListFragment
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            SmsListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}