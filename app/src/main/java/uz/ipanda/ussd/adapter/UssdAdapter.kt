package uz.ipanda.ussd.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection
import uz.ipanda.ussd.databinding.OperatorItemBinding
import uz.ipanda.ussd.databinding.UssdItemBinding
import uz.ipanda.ussd.databinding.ViewpagerMenuItemBinding
import uz.ipanda.ussd.models.Tarif
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.tariff.Tariff
import uz.ipanda.ussd.models.ussd.Ussd

class UssdAdapter(var list: List<Ussd>,val onItemClickListener: UssdAdapter.OnItemClickListener) : RecyclerView.Adapter<UssdAdapter.Vh>() {
    inner class Vh(var view: UssdItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(ussd: Ussd) {
            view.codeTv.text = ussd.code
            view.infoTv.text = ussd.info
            view.shareBtnUssdItem.setOnClickListener {

                onItemClickListener.onItemClickListener(ussd)
            }


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            UssdItemBinding.inflate(
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
        fun onItemClickListener(ussd: Ussd)

    }
}