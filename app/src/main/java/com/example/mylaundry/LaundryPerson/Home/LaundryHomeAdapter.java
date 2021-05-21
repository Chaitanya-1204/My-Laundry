package com.example.mylaundry.LaundryPerson.Home;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylaundry.Model.addOrderData;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.List;

public class LaundryHomeAdapter extends FirebaseRecyclerAdapter<addOrderData , LaundryHomeAdapter.myViewHolder  > {
    List<LaundryHomeModel> orderList;


    public LaundryHomeAdapter(@NonNull FirebaseRecyclerOptions<addOrderData> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull addOrderData model) {

        holder.imageView.setImageResource(R.drawable.bg_post1);
        holder.itemCount.setText(model.getBedSheet());
        holder.price.setText(model.getShirts());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_orders , parent , false) ;
        return new myViewHolder(view);
    }

    static class  myViewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView price, itemCount;
       public myViewHolder(@NonNull View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.item_order_image);
           price = itemView.findViewById(R.id.item_order_price);
           itemCount = itemView.findViewById(R.id.item_order_itemCount);
       }
   }
}

