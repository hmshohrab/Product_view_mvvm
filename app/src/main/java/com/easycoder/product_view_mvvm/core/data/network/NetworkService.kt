package com.easycoder.product_view_mvvm.core.data.network

import android.content.Context
import com.easycoder.product_view_mvvm.core.interfaces.RequestCompleteListener
import com.easycoder.product_view_mvvm.ui.features.main.model.ReportBase
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


/**
 * Created by HM SHOHRAB on 02,June,2020
 * easyCoder company,
 * Dhaka, Bangladesh.
 * hmshohrabpc@gmail.com
 * Let's start coding :)
 * Bismillah Hir Rahman Nir Raheem
 */
object NetworkService {
    enum class API {
        REPORT
    }

    fun execute(context: Context, api: API, listener: RequestCompleteListener<ReportBase>) {
        val retrofit = RestApiClient.getRetrofit(context)
        val call = when (api) {
            API.REPORT -> retrofit.create(ApiInterface::class.java).callApiForReport()


        }

        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MaybeObserver<Response<ReportBase>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    RxJavaDisposableManager.add(d)
                }


                override fun onError(e: Throwable) {
                    listener.onRequestFailed(e.localizedMessage)
                }

                override fun onSuccess(t: Response<ReportBase>) {
                    listener.onRequestSuccess(t.body()!!)
                }

            })

    }

}