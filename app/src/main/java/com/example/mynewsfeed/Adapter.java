package com.example.mynewsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsfeed.Model.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;
    List<Articles> articles;
    //Generate > Constructor
    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {   //Si da problemas le quito el "Adapter." antes de ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);  //items.xml
        return new ViewHolder(view);
    }

    //Obtenemos el titulo de los articulos y lo ponemos en su textview de items.xml correspondiente
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a = articles.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(a.getPublishedAt());

        String imageUrl = a.getUrlToImage();

        Picasso.get().load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //Inicializar variables
        TextView tvTitle, tvSource, tvDate; //TextViews de items.xml
        ImageView imageView; //imageView de items.xml
        CardView cardView; //cardView de items.xml

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignar variables obtenidas de la api a las variables de items.xml
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
