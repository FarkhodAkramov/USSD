package uz.ipanda.ussd.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.ipanda.ussd.MainActivity
import uz.ipanda.ussd.adapter.MinutViewPagerAdapter
import uz.ipanda.ussd.databinding.FragmentMinuteBinding
import uz.ipanda.ussd.databinding.TabItemBinding
import uz.ipanda.ussd.models.Category




class MinuteFragment : Fragment() {



    lateinit var binding: FragmentMinuteBinding
    lateinit var listCategory: ArrayList<Category>
    lateinit var viewPagerAdapter: MinutViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMinuteBinding.inflate(layoutInflater, container, false)
        loadCategory()
        viewPagerAdapter = MinutViewPagerAdapter(listCategory,requireActivity())
        binding.viewPager2.adapter = viewPagerAdapter
        (activity as MainActivity).supportActionBar?.title = "Minute"

        TabLayoutMediator(binding.tabLayout,binding.viewPager2){ tab,position->
            tab.text = listCategory[position].category_name
        }.attach()
        statTab()

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab!!.customView
                var tabItemBinding = TabItemBinding.bind(customView!!)
                tabItemBinding.cons2.setBackgroundColor(Color.WHITE)
                tabItemBinding.nameCategory.setTextColor(Color.parseColor("#01B4FF"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab!!.customView
                var tabItemBinding = TabItemBinding.bind(customView!!)
                tabItemBinding.cons2.setBackgroundColor(Color.parseColor("#01B4FF"))
                tabItemBinding.nameCategory.setTextColor(Color.WHITE)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })



        return binding.root
    }

    private fun statTab() {
        val tabCount = binding.tabLayout.tabCount
        for (i in 0 until tabCount){
            var tabItemBinding = TabItemBinding.inflate(LayoutInflater.from(binding.root.context),null,false)
            val tabAt = binding.tabLayout.getTabAt(i)
            tabAt!!.customView = tabItemBinding.root
            tabItemBinding.nameCategory.text = listCategory[i].category_name
            if (i==0){
                tabItemBinding.cons2.setBackgroundColor(Color.WHITE)
                tabItemBinding.nameCategory.setTextColor(Color.parseColor("#01B4FF"))
            }else{
                tabItemBinding.cons2.setBackgroundColor(Color.parseColor("#01B4FF"))
                tabItemBinding.nameCategory.setTextColor(Color.WHITE)

            }
        }
    }
    private fun loadCategory() {
        listCategory = ArrayList()
        listCategory.add(Category("Daqiqa to`plamlar",0))
        listCategory.add(Category("Foydali",1))
        listCategory.add(Category("Constructor",2))
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Minute"

    }
}