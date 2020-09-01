package com.app.assignmentaxiomtelecom.ui.activity

import CatalogItem
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.app.assignmentaxiomtelecom.R
import com.app.assignmentaxiomtelecom.repository.ApiService
import com.app.assignmentaxiomtelecom.ui.tab.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var disposable: Disposable? = null
    var secretKey: String? = null
    var allProducts:ArrayList<CatalogItem>? = null
    var allBrandList : ArrayList<String>? = null
    var searchWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        secretKey = getString(R.string.secret_key)
        fetchCatalogProducts(secretKey!!)

    }

    private fun fetchCatalogProducts(secretKey: String) {
        val apiServe by lazy {
            ApiService.create()
        }
        disposable =
            apiServe.getCatalogItems(secretKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        allProducts = result
                        allBrandList = ArrayList<String>()
                        for (product:CatalogItem in result!!)
                        {
                            if (!allBrandList!!.contains(product.brand))
                                allBrandList!!.add(product.brand)
                        }
                        showResults()
                    },
                    {
                            error -> Log.e("Error", "error")
                    }
                )
    }

    private fun showResults() {
        if (allBrandList != null)
        {
             val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,
                allBrandList!!, allProducts!!, searchWord)
            val viewPager: ViewPager = findViewById(R.id.view_pager)
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            tabs.setupWithViewPager(viewPager)
        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.searchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchWord = newText!!
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}