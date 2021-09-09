package uz.ipanda.ussd.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.ipanda.ussd.adapter.ViewpagerAdapter
import uz.ipanda.ussd.databinding.FragmentHomeBinding
import uz.ipanda.ussd.models.Tarif
import java.util.ArrayList
import io.github.vejei.viewpagerindicator.indicator.CircleIndicator


import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.ipanda.ussd.MainActivity
import uz.ipanda.ussd.R
import uz.ipanda.ussd.models.tariff.Tariff


class Home_Fragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    var handler = Handler(Looper.getMainLooper())
    lateinit var binding: FragmentHomeBinding
    lateinit var list: ArrayList<Tariff>
    lateinit var firestore: FirebaseFirestore
    lateinit var viewpagerAdapter: ViewpagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).supportActionBar?.title = "Home"

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        list = ArrayList()



        firestore.collection("tariff").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val tariff = documentChange.document.toObject(Tariff::class.java)
                        Log.d("TAG", "onCreateView: $tariff")
                        list.add(tariff)
                    }
                }
                viewpagerAdapter =
                    ViewpagerAdapter(list, object : ViewpagerAdapter.OnItemClickListener {
                        override fun onItemClickListener(tariff: Tariff) {
                            val bundle = Bundle()
                            bundle.putSerializable("tariff", tariff)
                            findNavController().navigate(R.id.tariffSecondFragment, bundle)

                        }

                    })

                binding.viewpagerHome.adapter = viewpagerAdapter

                binding.circleIndicator.setViewPager2(binding.viewpagerHome)

            }
            binding.viewpagerHome.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(slideRunnable)
                    handler.postDelayed(slideRunnable, 3000)
                    if (position == list.size - 1) {
                        handler.postDelayed(
                            { binding.viewpagerHome.setCurrentItem(0, false) },
                            5000
                        )
                    }
                }

            })
        }
        var calback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, calback)

        binding.ussdBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_Fragment_to_usdFragment)

        }
        binding.tariffBtn.setOnClickListener {
            findNavController().navigate(R.id.tariffFragment)

        }
        binding.smsBtn.setOnClickListener {
            findNavController().navigate(R.id.smsFragment)

        }
        binding.internetBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("tariff", "internet")
            findNavController().navigate(R.id.internetFragment, bundle)

        }
        binding.serviceBtn.setOnClickListener {
            findNavController().navigate(R.id.serviceFragment)
        }
        binding.minuteBtn.setOnClickListener {
            findNavController().navigate(R.id.minuteFragment)

        }

        return binding.root
    }

    private val slideRunnable: Runnable = Runnable {
        binding.viewpagerHome.setCurrentItem(binding.viewpagerHome.currentItem + 1)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Home"
    }



}