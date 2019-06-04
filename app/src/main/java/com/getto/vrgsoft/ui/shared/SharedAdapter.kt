package com.getto.vrgsoft.ui.shared

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.getto.vrgsoft.R
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.ui.shared.description.SharedDetailsActivity
import kotlinx.android.synthetic.main.item_emailed.view.*

class SharedAdapter(private  val results : List<Results>) : RecyclerView.Adapter<SharedAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindResult(result: Results) {
            itemView.title_emailed.text = result.title
            Glide.with(itemView.context).load(result.media[0].metadata?.get(0)?.url).crossFade().into(itemView.img_emailed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_emailed, parent, false))
    }

    override fun getItemCount(): Int {
        return results.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindResult(results[position])
        holder.itemView.setOnClickListener {
                        val intent = Intent(holder.itemView.context, SharedDetailsActivity::class.java)
            intent.putExtra("shared", results[position])
            holder.itemView.context.startActivity(intent)
            }
    }
}