package uz.ipanda.ussd.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.ipanda.ussd.BuildConfig
import uz.ipanda.ussd.MainActivity
import uz.ipanda.ussd.R
import uz.ipanda.ussd.adapter.ServiceAdapter
import uz.ipanda.ussd.adapter.TariffAdapter
import uz.ipanda.ussd.databinding.DialogBinding
import uz.ipanda.ussd.databinding.DialogBinding.*
import uz.ipanda.ussd.databinding.FragmentServiceBinding
import uz.ipanda.ussd.models.service.Service
import uz.ipanda.ussd.models.tariff.Tariff
import java.util.ArrayList


class ServiceFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var adapter: ServiceAdapter
    lateinit var serviceList: ArrayList<Service>
    lateinit var firestore: FirebaseFirestore
    lateinit var binding: FragmentServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Service"

        firestore = FirebaseFirestore.getInstance()
        serviceList = ArrayList()
        firestore.collection("service").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val service = documentChange.document.toObject(Service::class.java)
                        Log.d("TAG", "onCreateView: $service")
                        serviceList.add(service)
                    }
                }
                adapter = ServiceAdapter(serviceList, object : ServiceAdapter.OnItemClickListener {
                    override fun onItemClickListener(service: Service) {


                        val dialog = Dialog(requireContext())
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setCancelable(true)

                        val bindingDialog: DialogBinding = DialogBinding.inflate(inflater)
                        dialog.setContentView(bindingDialog.root)

                        bindingDialog.textView2.text = service.name
                        bindingDialog.textView3.text = service.info
                        bindingDialog.canelText.setOnClickListener { dialog.cancel() }
                        bindingDialog.cardView4.setOnClickListener {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.setData(Uri.parse("tel:" + "*100" + Uri.encode("#")))
                            startActivity(intent)
                        }
                        bindingDialog.imageView5.setOnClickListener {
                            try {
                                val shareIntent = Intent(Intent.ACTION_SEND)
                                shareIntent.type = "text/plain"
                                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                                var shareMessage = "\nLet me recommend you this application\n\n"
                                shareMessage =
                                    """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
                                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                                startActivity(Intent.createChooser(shareIntent, "USSD by Panda"))
                            } catch (e: java.lang.Exception) {
                                //e.toString();
                            }
                        }
                        dialog.show()


                    }

                })
                binding.rv.adapter = adapter


            }
        }

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Service"

    }

}