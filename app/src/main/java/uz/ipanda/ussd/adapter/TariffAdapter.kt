package uz.ipanda.ussd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ipanda.ussd.databinding.TariffItemBinding
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.tariff.Tariff

class TariffAdapter(var list: List<Tariff>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TariffAdapter.Vh>() {
    inner class Vh(var view: TariffItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(tariff: Tariff) {
            view.name.text = tariff.name
            view.minTv.text = "O`zbekiston bo`yicha ${tariff.payment} daqiqa"
            view.mgTv.text = "Mobile internet ${tariff.mg} mg"
            view.smsTv.text = "SMS to`plam  ${tariff.sms} sms"
            view.root.setOnClickListener {

                onItemClickListener.onItemClickListener(tariff)
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
        fun onItemClickListener(tariff: Tariff)

    }
}