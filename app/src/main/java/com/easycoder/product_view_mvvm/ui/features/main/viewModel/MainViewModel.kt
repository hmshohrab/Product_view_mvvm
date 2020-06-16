package com.easycoder.product_view_mvvm.ui.features.main.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.easycoder.product_view_mvvm.core.data.network.NetworkService
import com.easycoder.product_view_mvvm.core.interfaces.RequestCompleteListener
import com.easycoder.product_view_mvvm.ui.features.main.model.Report
import com.easycoder.product_view_mvvm.ui.features.main.model.ReportBase


/**
 * Created by HM SHOHRAB on 02,June,2020
 * easyCoder company,
 * Dhaka, Bangladesh.
 * hmshohrabpc@gmail.com
 * Let's start coding :)
 * Bismillah Hir Rahman Nir Raheem
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var progress: MutableLiveData<Boolean> = MutableLiveData()
    var reportBase: MutableLiveData<ReportBase> = MutableLiveData()
    var report: MutableLiveData<MutableList<Report>> = MutableLiveData()
    var isError: MutableLiveData<String> = MutableLiveData()
    private var context: Context = application

    init {
        getReportBase()
    }

    private fun getReportBase() {
        progress.postValue(true)
        NetworkService.execute(getApplication(),
            NetworkService.API.REPORT, object : RequestCompleteListener<ReportBase> {
                override fun onRequestSuccess(data: ReportBase) {
                    progress.postValue(false)
                    reportBase.value = data
                    report.value = data.report

                }

                override fun onRequestFailed(errorMessage: String) {
                    isError.value = errorMessage
                    progress.postValue(false)

                }

            })
    }
}