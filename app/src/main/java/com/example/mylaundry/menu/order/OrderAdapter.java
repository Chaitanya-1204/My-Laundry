package com.example.mylaundry.menu.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylaundry.CustomerOrderHistory;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class OrderAdapter extends FirebaseRecyclerAdapter<OrderModel, OrderAdapter.orderViewHolder> {

    private Context mContext;
    public OrderAdapter(@NonNull FirebaseRecyclerOptions<OrderModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull orderViewHolder holder, int position, @NonNull OrderModel model) {
        holder.customerName.setText(model.getCustomerName());
        holder.itemCount.setText(model.getItemCount());
        String Price = "$" + model.getPrice() ;
        holder.price.setText(Price);
        Glide.with(holder.image.getContext()).load(model.getImageUrl()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , CustomerOrderHistory.class);
                intent.putExtra("phoneNumber" , model.getPhoneNumber());

                mContext.startActivity(intent);


            }
        });

    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_orders , parent , false);
        mContext = view.getContext();
        return new orderViewHolder(view);
    }

    static class orderViewHolder extends RecyclerView.ViewHolder{
        TextView customerName , price , itemCount;
        ImageView image;

        public orderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_order_image);
            customerName = itemView.findViewById(R.id.item_order_customer_name);

            price= itemView.findViewById(R.id.item_order_price);
            itemCount = itemView.findViewById(R.id.item_order_itemCount);
        }
    }
}
