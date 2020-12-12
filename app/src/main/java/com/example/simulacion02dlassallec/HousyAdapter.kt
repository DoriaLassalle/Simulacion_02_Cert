package com.example.simulacion02dlassallec

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simulacion02dlassallec.model.local.Housy

class HousyAdapter(val callback: FirstFragment) :
    RecyclerView.Adapter<HousyAdapter.HousyViewHolder>() {

    private var housesList = emptyList<Housy>()
    fun updateAdapter(myList: List<Housy>) {
        housesList = myList
        notifyDataSetChanged()
    }

    inner class HousyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenLista = itemView.findViewById<ImageView>(R.id.imageRecycler)
        val precioLista = itemView.findViewById<TextView>(R.id.tvPrecioLista)
        val nombreLista = itemView.findViewById<TextView>(R.id.tvNombreLista)
        val click = itemView.setOnClickListener {
            callback.passHouseDetails(housesList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): HousyAdapter.HousyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.houselist_item, parent, false)

        return HousyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HousyAdapter.HousyViewHolder, position: Int) {
        val image = housesList[position].photo
        Glide.with(holder.itemView.context).load(image).into(holder.imagenLista)


        //Profe no puedo usar aqui "getString" no me deja, como para haber traido el $ de strings

        holder.precioLista.text = "$" + housesList[position].price.toString()
        holder.nombreLista.text = housesList[position].name


    }

    override fun getItemCount() = housesList.size


    interface PassHouseData {

        fun passHouseDetails(house: Housy)
    }


}