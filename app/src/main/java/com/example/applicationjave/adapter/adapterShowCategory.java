package com.example.applicationjave.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationjave.Model.category;
import com.example.applicationjave.Model.notes;
import com.example.applicationjave.R;

import java.util.List;

//public class adapterShowCategory {
//}
//


public class adapterShowCategory extends RecyclerView.Adapter<adapterShowCategory.ViewHolder> {

    private List<category> mData;
    private LayoutInflater mInflater;
    private  ItemClickListener2 itemClickListener2;



    public adapterShowCategory(Context context, List<category> data , ItemClickListener2 onClick2 ) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.itemClickListener2 = onClick2;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.name.setText(mData.get(position).getName());
        holder.id.setText(mData.get(position).getId());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener2.onItemClick2(holder.getAdapterPosition(), mData.get(position).getId());

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         public TextView name;
         public TextView id;

         public CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.id = itemView.findViewById(R.id.id);
            this.card = itemView.findViewById(R.id.card2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }

    category getItem(int id) {
        return mData.get(id);
    }




    public interface ItemClickListener2{
        void onItemClick2(int position, String id);
    }
}