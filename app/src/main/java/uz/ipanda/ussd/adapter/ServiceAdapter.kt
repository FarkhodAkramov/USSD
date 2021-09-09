package uz.ipanda.ussd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ipanda.ussd.databinding.ServiceItemBinding
import uz.ipanda.ussd.databinding.TariffItemBinding
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.service.Service
import uz.ipanda.ussd.models.tariff.Tariff

class ServiceAdapter(var list: List<Service>, val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ServiceAdapter.Vh>() {
    inner class Vh(var view: ServiceItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(service: Service) {
            view.name.text = service.name
            view.infoTv.text = service.info
            view.root.setOnClickListener {

                onItemClickListener.onItemClickListener(service)

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ServiceItemBinding.inflate(
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
        fun onItemClickListener(service: Service)

    }
}