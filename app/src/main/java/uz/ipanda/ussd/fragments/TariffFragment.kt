package uz.ipanda.ussd.fragments

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

import uz.ipanda.ussd.adapter.TariffAdapter
import uz.ipanda.ussd.databinding.FragmentTariffBinding
import uz.ipanda.ussd.models.tariff.Tariff
import java.util.ArrayList


class TariffFragment : Fragment() {


    lateinit var binding: FragmentTariffBinding
    lateinit var firestore: FirebaseFirestore
    lateinit var tariffList: ArrayList<Tariff>
    lateinit var adapter: TariffAdapter
    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

        ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTariffBinding.inflate(layoutInflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        tariffList = ArrayList()
        (activity as MainActivity).supportActionBar?.title = "Tarif"

        firestore.collection("tariff").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val tariff = documentChange.document.toObject(Tariff::class.java)
                        Log.d("TAG", "onCreateView: $tariff")
                        tariffList.add(tariff)
                    }
                }
                adapter = TariffAdapter(tariffList, object : TariffAdapter.OnItemClickListener {
                    override fun onItemClickListener(tariff: Tariff) {
                        val bundle = Bundle()
                        bundle.putSerializable("tariff", tariff)
                        findNavController().navigate(R.id.tariffSecondFragment, bundle)

                    }

                })
                binding.ussdRv.adapter = adapter


            }


        }



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Tarif"

    }

}