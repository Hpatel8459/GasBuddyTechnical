package com.interview.gasbuddytechnical.ui.view.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    var toolbarTitle = ObservableField<String>("Photos")

}