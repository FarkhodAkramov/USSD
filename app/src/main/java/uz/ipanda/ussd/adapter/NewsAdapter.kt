package uz.ipanda.ussd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.ipanda.ussd.databinding.NewsItemBinding

import uz.ipanda.ussd.models.Tarif
import uz.ipanda.ussd.models.news.News
import uz.ipanda.ussd.models.operator.Operator
import uz.ipanda.ussd.models.tariff.Tariff

class NewsAdapter(var list: List<News>,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<NewsAdapter.Vh>() {
    inner class Vh(var view: NewsItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun onBind(news: News) {
            Picasso.get().load(news.imageURL).into(view.image)
            view.infoTv.text = news.info
            view.root.setOnClickListener {
                onItemClickListener.onItemClickListener(news)

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            NewsItemBinding.inflate(
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
        fun onItemClickListener(news: News)

    }
}