package com.example.android3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    ArrayList<Contact> contact;
    Context context;
    ItemClickListener mListener;


    public Adapter(ArrayList<Contact> contact, Context context, ItemClickListener mListener) {
        this.contact = contact;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(contact.get(position).getName());
        holder.number.setText(contact.get(position).getNumber());
        if (contact.get(position).image != null) {
            holder.image.setImageResource(R.drawable.ic_person);
        }
//        Glide.with(holder.image)
//                .load(contact.get(position).getImage())
//                .circleCrop()
//                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, number;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvContactName);
            number = itemView.findViewById(R.id.tvContactNumber);
            image = itemView.findViewById(R.id.imContact);


            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            mListener.makeCall(contact.get(getAdapterPosition()));
        }

    }

    interface ItemClickListener {
        public void makeCall(Contact contact);
    }

}
