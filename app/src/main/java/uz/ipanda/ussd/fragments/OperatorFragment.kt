package uz.ipanda.ussd.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import uz.ipanda.ussd.R
import uz.ipanda.ussd.adapter.OperatorAdapter
import uz.ipanda.ussd.databinding.FragmentOperatorBinding
import uz.ipanda.ussd.models.operator.Operator
import java.util.ArrayList
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection

import android.R.string.no
import uz.ipanda.ussd.MainActivity


class OperatorFragment : Fragment() {

    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: OperatorAdapter
    lateinit var operatorsList: ArrayList<Operator>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var binding: FragmentOperatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOperatorBinding.inflate(layoutInflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        operatorsList = ArrayList()
        (activity as MainActivity).supportActionBar?.title = "Operator"

        firestore.collection("operator").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val operator = documentChange.document.toObject(Operator::class.java)
                        Log.d("TAG", "onCreateView: $operator")
                        operatorsList.add(operator)
                    }
                }
                adapter = OperatorAdapter(operatorsList)

                binding.rvOperator.adapter = adapter


            }

        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                findNavController().navigate(R.id.home_Fragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Operator"

    }

}