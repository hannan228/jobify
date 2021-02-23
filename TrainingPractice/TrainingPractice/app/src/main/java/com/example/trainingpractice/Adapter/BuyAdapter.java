package com.example.trainingpractice.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingpractice.DetailActivity;
import com.example.trainingpractice.Model.ItemList;
import com.example.trainingpractice.Model.ModelBuy;
import com.example.trainingpractice.R;

import java.util.List;

public class BuyAdapter extends RecyclerView.Adapter <BuyAdapter.ViewHolder>{

        private Context context;
        private List<ModelBuy> itemLists;

    public BuyAdapter(Context context, List<ModelBuy> itemListList) {

            this.context = context;
            this.itemLists = itemListList;


        }

        @NonNull
        @Override
        public BuyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_model ,parent,false);
            return new BuyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ModelBuy item = itemLists.get(position);
            holder.id.setText(item.getId());
            holder.name.setText(item.getName());
            holder.price.setText(item.getPrice());
            holder.quantity.setText(item.getQuantity());
            holder.type.setText(item.getType());
        }

        @Override
        public int getItemCount() {
            return itemLists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView id;
            public TextView name;
            public TextView price;
            public TextView quantity;
            public TextView type;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                itemView.setOnClickListener(this::onClick);

                id = (TextView) itemView.findViewById(R.id.buyer_id);
                name = (TextView) itemView.findViewById(R.id.buyer_Name);
                price = (TextView) itemView.findViewById(R.id.buyer_price);
                quantity = (TextView) itemView.findViewById(R.id.buyer_quantity);
                type = (TextView) itemView.findViewById(R.id.buyer_type);

            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

            }
        }
}
