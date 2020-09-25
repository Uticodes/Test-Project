package com.example.testproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonsViewHolder> {

    LayoutInflater mInflater;
    List<Persons> names;

    public RecyclerAdapter(Context context, List<Persons> people) {
        mInflater = LayoutInflater.from(context);
        names = people;
    }

    @NonNull
    @Override
    public PersonsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.recycler_item, viewGroup, false);
        PersonsViewHolder myHolder = new PersonsViewHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonsViewHolder myHolder, int position) {
        Persons person = names.get(position);
        myHolder.mName.setText("Name: " + person.getName());
        myHolder.mGender.setText("Gender: " + person.getGender());
        myHolder.mAge.setText("Age: " + person.getAge());
        myHolder.mHeight.setText("Height: " + String.valueOf(person.getHeight()));
        myHolder.image.setImageResource(R.drawable.ic_android_black_24dp);

    }

    @Override
    public int getItemCount() {
        return names.size();
    }


    public class PersonsViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        TextView mGender;
        TextView mAge;
        TextView mHeight;
        ImageView image;

        public PersonsViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tvName);
            mGender = itemView.findViewById(R.id.tvGender);
            mAge = itemView.findViewById(R.id.tvAge);
            mHeight = itemView.findViewById(R.id.tvHieght);
            image = itemView.findViewById(R.id.imgImge);
        }
    }
}


