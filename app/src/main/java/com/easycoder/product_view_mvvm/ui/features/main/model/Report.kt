package com.easycoder.product_view_mvvm.ui.features.main.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by HM SHOHRAB on 02,June,2020
 * easyCoder company,
 * Dhaka, Bangladesh.
 * hmshohrabpc@gmail.com
 * Let's start coding :)
 * Bismillah Hir Rahman Nir Raheem
 */
data class Report(

    @SerializedName("Id") val id: Int,
    @SerializedName("Title") val title: String,
    @SerializedName("Price") val price: Int,
    @SerializedName("Image") val image: String
) : Serializable