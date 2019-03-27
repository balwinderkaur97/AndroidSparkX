package gurtek.mrgurtekbase.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter

import java.util.ArrayList

/**
 * * Created by Gurtek Singh on 30/1/18.
 * Sachtech Solution
 * gurtek@protonmail.ch
 */

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var frag_list: MutableList<Fragment> = ArrayList()
    private var titleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return frag_list[position]
    }

    fun addFragment(fragment: Fragment, title: String = "") {
        frag_list.add(fragment)
        titleList.add(title)
    }

    override fun getCount(): Int {
        return frag_list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }


}
