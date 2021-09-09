package uz.ipanda.ussd.fragments

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
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.ipanda.ussd.BuildConfig
import uz.ipanda.ussd.R
import uz.ipanda.ussd.adapter.MinuteRvAdapter
import uz.ipanda.ussd.adapter.SmsRvAdapter
import uz.ipanda.ussd.databinding.DialogBinding
import uz.ipanda.ussd.databinding.FragmentInternetListBinding
import uz.ipanda.ussd.databinding.FragmentMinuteListBinding
import uz.ipanda.ussd.models.minute.Minute
import uz.ipanda.ussd.models.sms.Sms
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MinuteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MinuteListFragment : Fragment() {
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

    lateinit var binding: FragmentMinuteListBinding
    lateinit var minuteList: ArrayList<Minute>
    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: MinuteRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMinuteListBinding.inflate(layoutInflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        minuteList = ArrayList()
        firestore.collection("minute").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val sms = documentChange.document.toObject(Minute::class.java)
                        Log.d("SMS", "onCreateView: $sms")
                        if (sms.type.equals(param2, ignoreCase = true)) {
                            minuteList.add(sms)

                        }

                    }
                }
                adapter = MinuteRvAdapter(minuteList, object : MinuteRvAdapter.OnItemClickListener {
                    override fun onItemClickListener(minute: Minute) {

                        val dialog = Dialog(requireContext())
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setCancelable(true)

                        val bindingDialog: DialogBinding = DialogBinding.inflate(inflater)
                        dialog.setContentView(bindingDialog.root)

                        bindingDialog.textView2.text = "${minute.count} daqiqa"
                        bindingDialog.textView3.text = "Narxi: ${minute.price} so`m \nBerilgan daqiqalar soni: ${minute.count}\nAmal qilish mudati: ${minute.period}"
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
                binding.rvMinute.adapter = adapter


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
         * @return A new instance of fragment MinuteListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            MinuteListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}