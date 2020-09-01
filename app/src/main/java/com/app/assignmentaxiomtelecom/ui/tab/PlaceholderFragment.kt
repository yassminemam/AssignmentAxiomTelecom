package com.app.assignmentaxiomtelecom.ui.tab

import CatalogItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.assignmentaxiomtelecom.R
import com.app.assignmentaxiomtelecom.ui.recycleradapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
@Suppress("DEPRECATION")
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var filteredProducts: ArrayList<CatalogItem>? = null
    var allProducts: ArrayList<CatalogItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_products.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = RecyclerViewAdapter(allProducts!!)
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(
            productsList: ArrayList<CatalogItem>,
            searchWord: String
        ): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    filteredProducts = ArrayList<CatalogItem>()
                    allProducts = productsList
                    for (product: CatalogItem in productsList) {
                        if (product.brand.contains(searchWord)
                            || product.phone.contains(searchWord)
                            || product.priceEur.toString().contains(searchWord)
                            || product.sim.contains(searchWord)
                            || product.gps.contains(searchWord)
                            || product.audioJack.contains(searchWord)
                        ) {
                            filteredProducts!!.add(product)
                        }
                    }
                }
            }
        }
    }
}