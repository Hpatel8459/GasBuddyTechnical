package com.interview.gasbuddytechnical.ui.view.activities

import android.os.Build
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interview.gasbuddytechnical.R
import com.interview.gasbuddytechnical.databinding.ActivityMainBinding
import com.interview.gasbuddytechnical.ui.view.viewmodel.PhotoViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        setToolbar()
        initialize()
    }

    private fun setToolbar() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        setSupportActionBar(activityMainBinding.toolbarMain.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        activityMainBinding.toolbarMain.toolbarSearch.visibility = VISIBLE
    }

    private fun initialize() {
        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)

        activityMainBinding.recyclerMain.layoutManager = LinearLayoutManager(this)
        activityMainBinding.recyclerMain.adapter = photoViewModel.adapter

        photoViewModel.loadImages(this, page.toString())

        activityMainBinding.toolbarMain.viewModel = photoViewModel


        activityMainBinding.toolbarMain.toolbarSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                photoViewModel.adapter.setFilterText(newText)
                photoViewModel.adapter.filter.filter(newText)
                return false
            }
        })


        activityMainBinding.recyclerMain.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    page++
                    photoViewModel.loadImages(this@MainActivity, page.toString())
                }
            }
        })

    }
}
