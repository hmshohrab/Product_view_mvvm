package com.easycoder.product_view_mvvm.core.data.network

import com.easycoder.product_view_mvvm.ui.features.main.model.ReportBase
import io.reactivex.Maybe
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {


    @GET("api/home_page.php")
    fun callApiForReport(): Maybe<Response<ReportBase>>
}