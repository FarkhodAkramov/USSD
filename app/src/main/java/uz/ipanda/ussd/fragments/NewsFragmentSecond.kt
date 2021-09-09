package uz.ipanda.ussd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import uz.ipanda.ussd.R
import uz.ipanda.ussd.databinding.FragmentNewsSecondBinding
import uz.ipanda.ussd.models.news.News
import uz.ipanda.ussd.models.tariff.Tariff

class NewsFragmentSecond : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var binding: FragmentNewsSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsSecondBinding.inflate(layoutInflater, container, false)

        val news = arguments?.getSerializable("news") as News
        binding.apply {
            titleTv.text = news.title
            Picasso.get().load(news.imageURL).into(image)
            infoTv.text = news.info
        }

        return binding.root
    }


}