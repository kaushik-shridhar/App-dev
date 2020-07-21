package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<ItemList> mlist;

    public Adapter(ArrayList<ItemList> list) {
        mlist = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemList item = mlist.get(position);
        //holder.imageView.setImageResource(item.getImgId());
        Picasso.get().load(item.getImgId()).into(holder.imageView);
        holder.textView.setText(item.getHeading());
        holder.textView2.setText(item.getLocation());
        holder.textView3.setText(item.getPrice());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+ item.getHeading(),Toast.LENGTH_LONG).show();
                //methodStartActivity(view.getContext());
                Intent intent = new Intent(view.getContext(), InfoActivity.class);
                intent.putExtra("EVENT_IMG", item.getImgId());
                intent.putExtra("EVENT_HEADING", item.getHeading());
                intent.putExtra("EVENT_LOCATION", item.getLocation());
                intent.putExtra("EVENT_PRICE", item.getPrice());
                intent.putExtra("EVENT_DATE", item.getDate());
                view.getContext().startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView textView2;
        public TextView textView3;
        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            button = itemView.findViewById(R.id.button);
        }
    }


}
