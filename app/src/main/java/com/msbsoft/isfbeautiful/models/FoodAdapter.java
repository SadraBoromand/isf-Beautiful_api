package com.msbsoft.isfbeautiful.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.msbsoft.isfbeautiful.R;
import com.msbsoft.isfbeautiful.activities.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    Context context1;
    List<Food> places;
    Food food;

    public FoodAdapter(Context context1, List<Food> places) {
        this.context1 = context1;
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View coView = inflater.inflate(R.layout.item_recycler_view, parent, false);

        return new ViewHolder(coView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        food = places.get(position);

        TextView textView = holder.txtAdapter;
        ImageView imageView = holder.imgAdapter;
        CardView cardView = holder.cardAdapter;

        textView.setText(food.title);
        Picasso.get().load(food.image).into(imageView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("sa ada", food.desc1);
                Intent intent = new Intent(context1, DetailActivity.class);

                intent.putExtra("title", food.title);
                intent.putExtra("desc", food.desc1);
                intent.putExtra("img", food.image);

                context1.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtAdapter;
        public ImageView imgAdapter;
        public CardView cardAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAdapter = itemView.findViewById(R.id.txtAdapter);
            imgAdapter = itemView.findViewById(R.id.imgAdapter);
            cardAdapter = itemView.findViewById(R.id.cardAddapter);

        }
    }
}