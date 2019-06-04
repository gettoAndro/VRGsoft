package com.getto.vrgsoft.ui.favorite

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getto.vrgsoft.data.nyt.service.Results
import kotlinx.android.synthetic.main.item_emailed.view.*
import android.util.Log
import com.getto.vrgsoft.R
import java.io.File
import com.getto.vrgsoft.util.ImageSaver


class FavoriteAdapter (private  val results : List<Results>) : RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>(){



    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCats(result: Results){
            itemView.title_emailed.text = result.title
            val bitmap = ImageSaver(itemView.context).
                setFileName("${result.id}.jpg").
                setDirectoryName("images").
                load()
            itemView.img_emailed.setImageBitmap(bitmap)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_emailed, parent, false))
    }

    override fun getItemCount(): Int {
        return results.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindCats(results[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FavoriteDetailsActivity::class.java)
            intent.putExtra("favorite", results[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}