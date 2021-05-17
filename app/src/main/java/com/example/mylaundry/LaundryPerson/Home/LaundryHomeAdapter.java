package com.example.mylaundry.LaundryPerson.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylaundry.R;

import java.util.List;

public class LaundryHomeAdapter extends RecyclerView.Adapter<LaundryHomeAdapter.ViewHolder> {
    List<LaundryHomeModel> orderList;

    public LaundryHomeAdapter(List<LaundryHomeModel> orderList) {
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public LaundryHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_orders , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull LaundryHomeAdapter.ViewHolder holder, int position) {
        holder.orderImage.setImageResource(orderList.get(position).getImage());
        holder.orderId.setText(orderList.get(position).getOrderId());
        holder.orderStatus.setText(orderList.get(position).getOrderStatus());
        holder.orderPrice.setText(orderList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {

        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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

