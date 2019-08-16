package com.dev.mahmoud_ashraf.tagssolutionstask.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.tagssolutionstask.models.mSplashSteps
import kotlinx.android.synthetic.main.splash_row_item.view.*
import com.bumptech.glide.Glide
import com.dev.mahmoud_ashraf.tagssolutionstask.R


class SplashStepsAdapter(private val offersList: MutableList<mSplashSteps.ToolData?>) :
    RecyclerView.Adapter<SplashStepsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init { }

        fun bind(item: mSplashSteps.ToolData) {
            with(itemView) {
                this.setOnClickListener {}
                title.text= item.title

                Glide.with(this.context)
                    .load(item.picPath)
                    .into(splashImage)

            }
        }}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.splash_row_item, parent, false)
    )

    override fun getItemCount(): Int = offersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(offersList[position]!!)
    }




}