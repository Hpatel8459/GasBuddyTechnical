package com.interview.gasbuddytechnical.ui.view.activities

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.interview.gasbuddytechnical.R
import com.interview.gasbuddytechnical.databinding.ActivityDetailsBinding
import com.interview.gasbuddytechnical.ui.view.viewmodel.DetailsViewModel
import com.interview.technicalround.utils.CommonUtils


class DetailsActivity : AppCompatActivity() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var activityDetailsBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = DataBindingUtil.setContentView<ActivityDetailsBinding>(
            this,
            R.layout.activity_details
        )

        setToolbar()
        initialize()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initialize() {
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        activityDetailsBinding.viewModel = detailsViewModel
        activityDetailsBinding.toolbarDetails.viewModel = detailsViewModel

        if (intent.hasExtra(CommonUtils.IMAGE_ID)) {
            detailsViewModel.loadImage(this, intent.getStringExtra(CommonUtils.IMAGE_ID))
        }
    }

    private fun setToolbar() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        setSupportActionBar(activityDetailsBinding.toolbarDetails.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }
}
