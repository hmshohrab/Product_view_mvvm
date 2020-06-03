package com.easycoder.product_view_mvvm.ui.features.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easycoder.product_view_mvvm.R
import com.easycoder.product_view_mvvm.core.interfaces.SimpleCallback
import com.easycoder.product_view_mvvm.ui.features.main.model.Report
import com.easycoder.product_view_mvvm.ui.features.main.model.ReportBase
import com.easycoder.product_view_mvvm.ui.features.main.view.adapter.ReportAdapter
import com.easycoder.product_view_mvvm.ui.features.main.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var reportBase: ReportBase? = null
    var reportList: MutableList<Report>? = null
    var reportAdapter: ReportAdapter? = null


    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reportList = mutableListOf()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]




        observer()
    }

    private fun observer() {

        mainViewModel.progress.observe(this, Observer {
            pb_home.visibility = if (it) View.VISIBLE else View.GONE
        })
        mainViewModel.reportBase.observe(this, Observer {
            reportBase = it
        })

        mainViewModel.report.observe(this, Observer {
            reportList!!.clear()
            reportList = it.toCollection(reportList!!)

            setRecyclerView()
        })

        mainViewModel.isError.observe(this, Observer {
            showErrorSnack(this, it)
        })


    }


    private fun setRecyclerView() {
        rc_report.layoutManager = LinearLayoutManager(this)
        reportAdapter = ReportAdapter(reportList!!)
        rc_report.adapter = reportAdapter

        reportAdapter!!.setListener(SimpleCallback<Report> {
            val bundle = Bundle()
            bundle.putSerializable("REPORT", it)
            startActivity(ReportViewActivity::class.java, bundle)
        })

    }

    private fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    private fun showErrorSnack(activity: Activity, message: String?) {
        try {
            Snackbar.make(
                activity.findViewById(android.R.id.content), message!!,
                Snackbar.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
