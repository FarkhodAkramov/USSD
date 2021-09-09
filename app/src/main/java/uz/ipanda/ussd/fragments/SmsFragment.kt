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
import uz.ipanda.ussd.adapter.SmsViewPagerAdapter
import uz.ipanda.ussd.databinding.FragmentSmsBinding
import uz.ipanda.ussd.databinding.TabItemBinding
import uz.ipanda.ussd.models.Category


class SmsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    lateinit var listCategory:ArrayList<Category>
    lateinit var binding: FragmentSmsBinding
    lateinit var viewPagerAdapter: SmsViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =FragmentSmsBinding.inflate(layoutInflater, container, false)
        loadCategory()
        (activity as MainActivity).supportActionBar?.title = "SMS"

        viewPagerAdapter = SmsViewPagerAdapter(listCategory,requireActivity())
        binding.viewPager2.adapter = viewPagerAdapter

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
        listCategory.add(Category("Kunlik",0))
        listCategory.add(Category("Haftalik",1))
        listCategory.add(Category("Oylik",2))
        listCategory.add(Category("Xalqaro",3))
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "SMS"

    }

}