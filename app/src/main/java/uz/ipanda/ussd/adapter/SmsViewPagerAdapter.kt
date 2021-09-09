package uz.ipanda.ussd.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import uz.ipanda.ussd.fragments.SmsListFragment
import uz.ipanda.ussd.models.Category

class SmsViewPagerAdapter(var list: List<Category>, fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {
    var fragments:ArrayList<SmsListFragment> = ArrayList()
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment =  SmsListFragment.newInstance(list[position].category_position!!, list[position].category_name!!)
        fragments.add(fragment)
        return fragment
    }
}