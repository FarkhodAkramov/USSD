package uz.ipanda.ussd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ipanda.ussd.databinding.TariffItemBinding
import uz.ipanda.ussd.models.minute.Minute
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.tariff.Tariff

class MinuteRvAdapter(var list: List<Minute>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MinuteRvAdapter.Vh>() {
    inner class Vh(var view: TariffItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(minute: Minute) {
            view.name.text = "${minute.count} daqiqa"
            view.minTv.text = "Narxi: ${minute.price} so`m "
            view.mgTv.text = "Berilgan daqiqalar soni: ${minute.count}"
            view.smsTv.text = "Amal qilish mudati: ${minute.period}"
            view.root.setOnClickListener {

                onItemClickListener.onItemClickListener(minute)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            TariffItemBinding.inflate(
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
        fun onItemClickListener(minute: Minute)

    }
}