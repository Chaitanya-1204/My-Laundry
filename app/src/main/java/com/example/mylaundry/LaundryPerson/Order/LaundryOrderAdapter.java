package com.example.mylaundry.LaundryPerson.Order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylaundry.R;

import java.util.List;

public class LaundryOrderAdapter extends RecyclerView.Adapter<LaundryOrderAdapter.ViewHolder> {
    List<LaundryOrderModel> orderHistoryList;


    public LaundryOrderAdapter(List<LaundryOrderModel> orderHistoryList) {

        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public LaundryOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_orders , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(@NonNull LaundryOrderAdapter.ViewHolder holder, int position) {
        holder.orderImage.setImageResource(orderHistoryList.get(position).getImage());
        holder.orderItemCount.setText(orderHistoryList.get(position).getItemCount());

        holder.orderPrice.setText(orderHistoryList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {

        return orderHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView  orderPrice , orderItemCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.item_order_image);
            orderItemCount = itemView.findViewById(R.id.item_order_itemCount);
            orderPrice  = itemView.findViewById(R.id.item_order_price);
        }
    }
}
