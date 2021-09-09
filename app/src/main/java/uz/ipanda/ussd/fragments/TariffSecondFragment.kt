package uz.ipanda.ussd.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ipanda.ussd.R
import uz.ipanda.ussd.databinding.FragmentTariffSecondBinding
import uz.ipanda.ussd.models.tariff.Tariff


class TariffSecondFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var binding: FragmentTariffSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTariffSecondBinding.inflate(layoutInflater, container, false)
        val tariff = arguments?.getSerializable("tariff") as Tariff
        binding.apply {
            tarifNameTv.text = tariff.name
            mgTv.text = "${tariff.mg} MB"
            smsTv.text = "${tariff.sms} SMS"
            minTv.text = "${tariff.min} MIN"
            priceTv.text = "${tariff.payment} so`m"

            infoTv.text = tariff.info
            tarifNameBlue.text = "Tarif ${tariff.name}"
            connectBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_CALL)
                intent.setData(Uri.parse("tel:" + "*100" + Uri.encode("#")))
                startActivity(intent)
            }
        }


        return binding.root
    }


}