package com.easycoder.product_view_mvvm.ui.features.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.easycoder.product_view_mvvm.BuildConfig
import com.easycoder.product_view_mvvm.R
import com.easycoder.product_view_mvvm.core.interfaces.SimpleCallback
import com.easycoder.product_view_mvvm.ui.features.main.model.Report
import kotlinx.android.synthetic.main.raw_view_items.view.*


/**
 * Created by HM SHOHRAB on 03,June,2020
 * easyCoder company,
 * Dhaka, Bangladesh.
 * hmshohrabpc@gmail.com
 * Let's start coding :)
 * Bismillah Hir Rahman Nir Raheem
 */
class ReportAdapter(private val reportList: MutableList<Report>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mListener: SimpleCallback<Report>? = null

    fun setListener(listener: SimpleCallback<Report>) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.raw_view_items, parent, false)
        return ReportViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ReportViewHolder
        val report = reportList[position]
        holder.container(report)

        holder.itemView.setOnClickListener {
            mListener?.callback(report)
        }

    }

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.txtTitleId
        val img = itemView.img1
        val price = itemView.txtPriceId


        fun container(report: Report) {
            title.text = report.title
            price.text = report.price.toString() + " Tk"
            Glide.with(itemView.context).load(BuildConfig.BASE_URL + report.image).into(img)


        }
    }
}