package com.example.mylaundry.menu.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylaundry.R;


import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<OrderModel> orderHistoryList;


    public OrderAdapter(List<OrderModel> orderHistoryList) {

        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_orders , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.orderImage.setImageResource(orderHistoryList.get(position).getImage());
        holder.orderId.setText(orderHistoryList.get(position).getOrderId());
        holder.orderStatus.setText(orderHistoryList.get(position).getOrderStatus());
        holder.orderPrice.setText(orderHistoryList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {

        return orderHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView orderId , orderPrice , orderStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.item_order_image);
            orderId = itemView.findViewById(R.id.item_order_name);
            orderStatus = itemView.findViewById(R.id.item_order_status);
            orderPrice  = itemView.findViewById(R.id.item_order_price);
        }
    }
}
