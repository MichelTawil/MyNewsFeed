package com.example.mynewsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsfeed.Model.Articles
import com.example.mynewsfeed.Model.Headlines
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_URL = "url_param"
private const val ARG_TEMA = "tema_param"

class NewsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var url: String? = null //?si queremos que sea null
    private var tema: String? = null //?si queremos que sea null
    var rv: RecyclerView? = null

    val API_KEY = "31f26933fa804cfd9a23407a58c0d55a"
    var adapter: Adapter? = null
    var articles: ArrayList<Articles> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL) //Obtenemos esta variable desde Sections page Adapter "us", "mx, "es"
            tema = it.getString(ARG_TEMA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container, false) //Inflamos vista para poder acceder a ella
        init(view);
        return view;
    }

    private fun init(view: View) {
        rv = view.findViewById(R.id.recyclerView)
        rv?.setLayoutManager(LinearLayoutManager(activity))
        val country: String? = getCountry()
        if (!country.isNullOrEmpty() && !url.isNullOrEmpty() && !tema.isNullOrEmpty()) {  //Validamos que no sea null ni este vacio
            retrieveJson(country, API_KEY, url!!, tema!!)
        }

    }

    private fun retrieveJson(country: String, apiKey: String, url: String, tema: String) { //Obtenemos url desde SectionsPagerAdapter y se la enviamos a la api
        val call = ApiClient.getInstance().api.getHeadlines(url, apiKey, tema) //Enviamos variable obtenida de Sections Page Adpater hacia el API request
        call.enqueue(object : Callback<Headlines> {
            override fun onResponse(call: Call<Headlines>, response: Response<Headlines>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    articles.removeAll(articles) //Clear
                    val list = response.body()!!.articles.toList();
                    articles.addAll(list);
                    adapter = Adapter(requireContext(), articles)
                    rv?.setAdapter(adapter)
                }
            }

            override fun onFailure(call: Call<Headlines>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(url: String, tema: String) = //: regresa un tipo de dato de ese tipo
                NewsListFragment().apply {//SectionsPagerAdapter.java linea 38
                    arguments = Bundle().apply { //Pasa info de activity a fragment y viceversa
                        putString(ARG_URL, url) //(Key, Valor)
                        putString(ARG_TEMA, tema) //(Key, Valor)
                    }
                }
    }

    fun getCountry(): String? {
        val locale = Locale.getDefault()
        val country = locale.country
        return country.toLowerCase()
    }
}