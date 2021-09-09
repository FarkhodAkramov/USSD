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
import uz.ipanda.ussd.adapter.InternetViewPagerAdapter
import uz.ipanda.ussd.databinding.FragmentInternetBinding
import uz.ipanda.ussd.databinding.TabItemBinding

import uz.ipanda.ussd.models.Category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InternetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InternetFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding: FragmentInternetBinding
    lateinit var listCategory:ArrayList<Category>

    lateinit var viewPagerAdapter: InternetViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInternetBinding.inflate(layoutInflater, container, false)
        loadCategory()
        viewPagerAdapter = InternetViewPagerAdapter(listCategory,requireActivity())
        binding.viewPager2.adapter = viewPagerAdapter
        (activity as MainActivity).supportActionBar?.title = "Internet"

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
        listCategory.add(Category("Tungi",3))
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Internet"

    }

}