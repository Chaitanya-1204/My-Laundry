package com.example.mylaundry.LaundryPerson.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylaundry.LaundryPerson.OrderDetail;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class LaundryActiveOrderAdapter extends FirebaseRecyclerAdapter<activeOrder , LaundryActiveOrderAdapter.activeOrderViewHolder> {

    private Context mContext;


    public LaundryActiveOrderAdapter(@NonNull FirebaseRecyclerOptions<activeOrder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull activeOrderViewHolder holder, int position, @NonNull activeOrder model) {
        holder.customerName.setText(model.getCustomerName());
        String Price =  "$" + model.getPrice() ;
        holder.price.setText(Price);
        holder.itemCount.setText(model.getItemCount());
        Glide.with(holder.image.getContext()).load(model.getImageUrl()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , OrderDetail.class);
                intent.putExtra("phoneNumber" , model.getPhoneNumber());

                mContext.startActivity(intent);


            }
        });



    }


    @NonNull
    @Override
    public activeOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.row_item_orders , parent , false);
        mContext = view.getContext();

        return new activeOrderViewHolder(view);
    }

    static class activeOrderViewHolder extends RecyclerView.ViewHolder{
        TextView customerName,  price , itemCount;
        ImageView image;

        public activeOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_order_image);
            customerName = itemView.findViewById(R.id.item_order_customer_name);

            price= itemView.findViewById(R.id.item_order_price);
            itemCount = itemView.findViewById(R.id.item_order_itemCount);


        }




    }


}
