package uz.ipanda.ussd.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection
import uz.ipanda.ussd.databinding.OperatorItemBinding
import uz.ipanda.ussd.databinding.SmsItemBinding
import uz.ipanda.ussd.databinding.UssdItemBinding
import uz.ipanda.ussd.databinding.ViewpagerMenuItemBinding
import uz.ipanda.ussd.models.Tarif
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.sms.Sms
import uz.ipanda.ussd.models.tariff.Tariff
import uz.ipanda.ussd.models.ussd.Ussd

class SmsRvAdapter(var list: List<Sms>, val onButtonClickListener: OnItemClickListener) :
    RecyclerView.Adapter<SmsRvAdapter.Vh>() {
    inner class Vh(var view: SmsItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(sms: Sms) {
            view.name.text = "SMS ${sms.count}"
            view.infoTv.text = sms.info
            view.codeTv.text = sms.count.toString()
            view.shareBtnUssdItem.setOnClickListener {
                onButtonClickListener.onButtonClickListener()
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            SmsItemBinding.inflate(
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
        fun onButtonClickListener()

    }
}