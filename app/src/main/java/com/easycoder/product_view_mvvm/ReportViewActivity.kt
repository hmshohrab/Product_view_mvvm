package com.easycoder.product_view_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.easycoder.product_view_mvvm.ui.features.main.model.Report
import kotlinx.android.synthetic.main.activity_report_view.*

class ReportViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_view)

        val reportBundle = intent.extras

        val report = reportBundle?.getSerializable("REPORT") as Report
        productView(report)


    }


    fun productView(report: Report){
        txtTitleId.setText(report.title)
        txtPriceId.setText(report.price.toString() + " Tk" )
        Glide.with(this).load(BuildConfig.BASE_URL + report.image).into(img1)


    }
}
