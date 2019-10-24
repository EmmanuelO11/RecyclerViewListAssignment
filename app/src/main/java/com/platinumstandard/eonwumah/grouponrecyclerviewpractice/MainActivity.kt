package com.platinumstandard.eonwumah.grouponrecyclerviewpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.platinumstandard.eonwumah.grouponrecyclerviewpractice.adapters.ProductRecyclerAdapter
import com.platinumstandard.eonwumah.grouponrecyclerviewpractice.models.Category
import com.platinumstandard.eonwumah.grouponrecyclerviewpractice.models.DataSource
import com.platinumstandard.eonwumah.grouponrecyclerviewpractice.models.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var productAdapter: ProductRecyclerAdapter
    private var data = DataSource().createProductList
    private val newData = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()
        initRecyclerView()
        val sortLowToHighBtn = sort_low_high
        val sortHighToLowBtn = sort_high_low
        val clearAllFilters = clear_filters

        sortLowToHighBtn.setOnClickListener {
            sortByPriceLowToHigh()
        }

        sortHighToLowBtn.setOnClickListener {
            sortByPriceHighToLow()
        }

        clearAllFilters.setOnClickListener {
            addDataSet()
        }


    }

    private fun sortByPriceLowToHigh() {
        newData.removeIf { it.price == null }
        val sortedLowToHigh = newData.sortedBy { it.price }
        productAdapter.submitList(sortedLowToHigh)
        productAdapter.notifyDataSetChanged()

    }

    private fun addDataSet(){
        newData.removeAll(newData)
        newData.addAll(data)
        productAdapter.submitList(newData)
        productAdapter.notifyDataSetChanged()
    }


    private fun sortByPriceHighToLow() {
        newData.removeIf { it.price == null }
        val sortedHighToLow = newData.sortedByDescending { it.price }
        productAdapter.submitList(sortedHighToLow)
        productAdapter.notifyDataSetChanged()
    }


    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            productAdapter = ProductRecyclerAdapter()
            adapter = productAdapter
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun initSpinner() {
        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1,
            Category.values().map { it.value })
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filter_category.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = this@MainActivity
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("Spinner", "Item selected: ${Category.values()[position]}")
        newData.removeAll(newData)
        productAdapter.notifyDataSetChanged()
        filterByCategory(Category.values()[position])
    }

    private fun filterByCategory(category: Category) {
        var selectedCategoryList = data.filter { it.category == category }
        newData.addAll(selectedCategoryList)
        productAdapter.submitList(selectedCategoryList)
        productAdapter.notifyDataSetChanged()
    }
}
