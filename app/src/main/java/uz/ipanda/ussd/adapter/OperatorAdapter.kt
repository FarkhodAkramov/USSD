package uz.ipanda.ussd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ipanda.ussd.databinding.OperatorItemBinding
import uz.ipanda.ussd.databinding.ViewpagerMenuItemBinding
import uz.ipanda.ussd.models.Tarif
import uz.ipanda.ussd.models.operator.Operator

class OperatorAdapter(var list: List<Operator>) : RecyclerView.Adapter<OperatorAdapter.Vh>() {
    inner class Vh(var view: OperatorItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(operator: Operator) {
            view.codeTv.text = operator.code
            view.infoTv.text = operator.info

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            OperatorItemBinding.inflate(
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
}