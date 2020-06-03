package com.easycoder.product_view_mvvm.ui.features.main.model

import com.google.gson.annotations.SerializedName


/**
 * Created by HM SHOHRAB on 02,June,2020
 * easyCoder company,
 * Dhaka, Bangladesh.
 * hmshohrabpc@gmail.com
 * Let's start coding :)
 * Bismillah Hir Rahman Nir Raheem
 */
data class ReportBase(

    @SerializedName("error") val error: Int,
    @SerializedName("error_report") val error_report: String,
    @SerializedName("report") val report: MutableList<Report>
)