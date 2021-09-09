package uz.ipanda.ussd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ipanda.ussd.databinding.ViewpagerMenuItemBinding
import uz.ipanda.ussd.models.Tarif
import uz.ipanda.ussd.models.tariff.Tariff

class ViewpagerAdapter(var list: List<Tariff>,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewpagerAdapter.Vh>() {
    inner class Vh(var view: ViewpagerMenuItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(tarif: Tariff) {
            view.tarifNameTv.text = tarif.name
            view.minTv.text="${tarif.min} MIN"
            view.mgTv.text="${tarif.mg} MB"
            view.smsTv.text="${tarif.sms} SMS"
            view.root.setOnClickListener {

                onItemClickListener.onItemClickListener(tarif)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ViewpagerMenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemClickListener(tariff: Tariff)

    }
}