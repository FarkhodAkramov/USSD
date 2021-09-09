package uz.ipanda.ussd.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.ipanda.ussd.MainActivity
import uz.ipanda.ussd.R
import uz.ipanda.ussd.adapter.NewsAdapter
import uz.ipanda.ussd.databinding.FragmentNewsBinding
import uz.ipanda.ussd.models.news.News
import java.util.ArrayList


class NewsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: NewsAdapter
    lateinit var newsList: ArrayList<News>
    lateinit var binding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "News"

        firestore = FirebaseFirestore.getInstance()
        newsList = ArrayList()

        firestore.collection("news").addSnapshotListener { value, error ->
            value?.documentChanges?.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.ADDED -> {
                        val news = documentChange.document.toObject(News::class.java)
                        Log.d("TAG", "onCreateView: $news")
                        newsList.add(news)
                    }
                }
                adapter = NewsAdapter(newsList, object : NewsAdapter.OnItemClickListener {
                    override fun onItemClickListener(news: News) {
                        val bundle = Bundle()
                        bundle.putSerializable("news", news)

                        findNavController().navigate(R.id.newsFragmentSecond, bundle)

                    }

                })
                binding.rvNews.adapter = adapter


            }

        }



        return binding.root
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).supportActionBar?.title = "News"


    }

}