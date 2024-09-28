package com.spearson.pawpal.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spearson.pawpal.R
import com.spearson.pawpal.domain.model.Pal
import org.w3c.dom.Text

class HomeAdapter () : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var list: List<Pal> = emptyList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img_popular_card)
        val name = itemView.findViewById<TextView>(R.id.tv_popular_name)
        val info = itemView.findViewById<TextView>(R.id.tv_popular_info)
        val species = itemView.findViewById<TextView>(R.id.tv_popular_species)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPal = list[position]

        holder.name.text = currentPal.name
        holder.species.text = currentPal.location

        Glide.with(holder.img.context)
            .load(currentPal.photos)
            .into(holder.img)

    }

    fun setData(list: List<Pal>){
        this.list = list
        notifyDataSetChanged()
    }

}