package com.example.mynewsfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsfeed.Model.Articles;
import com.example.mynewsfeed.Model.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mynewsfeed.R.id.recyclerView;

public class Fragment1 extends Fragment {

    RecyclerView recyclerView;
    final String API_KEY = "31f26933fa804cfd9a23407a58c0d55a";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    private Object View;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_layout, container, false);

        //recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //String country = getCountry();
        //retrieveJson(country,API_KEY);
    }




    //private void retrieveJson(String country, String apiKey){

       // Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, apiKey, tema);
        //call.enqueue(new Callback<Headlines>() {
           // @Override
            //public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                //if(response.isSuccessful() && response.body().getArticles() != null){
                  //  articles.clear();
                  //  articles = response.body().getArticles();
                   // adapter = new Adapter(getActivity(),articles);
                   // recyclerView.setAdapter(adapter);
               // }
           // }
//
//            @Override
//            public void onFailure(Call<Headlines> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public String getCountry(){
//
//        Locale locale = Locale.getDefault();
//        String country = locale.getCountry();
//        return country.toLowerCase();
//    }


}



