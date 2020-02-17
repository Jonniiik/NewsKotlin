package com.example.newskotlin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Common.Common
import com.example.newskotlin.Model.RootObject
import com.example.newskotlin.R
import com.example.newskotlin.WebNewsActivity
import com.squareup.picasso.Picasso

class RvNewsAdapter(val rootObject: RootObject, val mContext: Context) :
    RecyclerView.Adapter<RvNewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var txtTitle: TextView = view.findViewById(R.id.txtTitle)
        internal var txtDescription: TextView = view.findViewById(R.id.txtDescription)
        internal var txtDate: TextView = view.findViewById(R.id.txtDate)
        internal var imgImageNews: ImageView = view.findViewById(R.id.imgImageNews)
        internal var newsCard: CardView = view.findViewById(R.id.newsCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvNewsAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(mContext).inflate(R.layout.rv_news_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return rootObject.articles.size
    }

    override fun onBindViewHolder(holder: RvNewsAdapter.MyViewHolder, position: Int) {
        Picasso.get().load(rootObject.articles[position].urlToImage).centerCrop().fit()
            .into(holder.imgImageNews)
        holder.txtTitle.text = rootObject.articles[position].title
        holder.txtDescription.text = rootObject.articles[position].description
        holder.txtDate.text = Common.ConverterDate(rootObject.articles[position].publishedAt)
        holder.newsCard.setOnClickListener {
            val intent = Intent(mContext, WebNewsActivity::class.java)
            intent.putExtra("LinkNews", rootObject.articles[position].url)
            mContext.startActivity(intent)
        }
    }
}