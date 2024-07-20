package com.basebox.mytimbushop.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.basebox.mytimbushop.util.NetworkChecker

class NetworkViewModel(application: Application) : AndroidViewModel(application) {
    private val networkHelper = NetworkChecker(application)

    val isNetworkAvailable: LiveData<Boolean> get() = networkHelper.isNetworkAvailable
}