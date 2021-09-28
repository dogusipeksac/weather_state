package com.example.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Model.WeatherRVModel;
import com.example.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRVModel> weatherRVModelArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(context).inflate(R.layout.weather_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherRVModel model=weatherRVModelArrayList.get(position);
        holder.textViewTemperatures.setText(model.getTemperature()+"°C");
        //date için
        SimpleDateFormat input=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output=new SimpleDateFormat("hh:mm aa");
        try {
            Date t=input.parse(model.getTime());
            holder.textViewTime.setText(output.format(t));

        }catch (Exception e){
            e.printStackTrace();
        }
        holder.textViewWindSpeed.setText(model.getWindSpeed()+"Km/h");
        Picasso.get().load("http:".concat(model.getIcon())).into(holder.imageViewCondition);
    }

    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTime,textViewTemperatures,textViewWindSpeed;
        private ImageView imageViewCondition;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime=itemView.findViewById(R.id.idTVTime);
            textViewTemperatures=itemView.findViewById(R.id.idTVTemperatures);
            textViewWindSpeed=itemView.findViewById(R.id.idTVWindSpeed);
            imageViewCondition=itemView.findViewById(R.id.idImageViewCondition);
        }
    }
}
