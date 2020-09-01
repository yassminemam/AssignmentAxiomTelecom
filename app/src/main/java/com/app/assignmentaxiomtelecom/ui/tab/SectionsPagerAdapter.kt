package com.app.assignmentaxiomtelecom.ui.tab

import CatalogItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class SectionsPagerAdapter(fm: FragmentManager,
                           val tabsList:ArrayList<String>, val allProducts:ArrayList<CatalogItem>,
                            val searchWord:String) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        val currentProducts = ArrayList<CatalogItem>()
        for (product:CatalogItem in allProducts )
        {
            if (product.brand == tabsList.get(position)) {
                currentProducts.add(product)
            }
        }
        return PlaceholderFragment.newInstance(currentProducts, searchWord)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabsList.get(position)
    }

    override fun getCount(): Int {
        return tabsList.size
    }
}