package com.hajesha.recipelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<String> listOfFood;
    private OnItemClickListener mItemClickListener;

    FoodAdapter() {
        this.listOfFood = new ArrayList<>();
    }

    void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_view_holder, parent, false);
        return new FoodViewHolder(v, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.name.setText(listOfFood.get(position));
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return listOfFood.size();
    }

    void removeFoodAt(int pos){
        listOfFood.remove(pos);
    }

    void addFood(@NonNull String food){
        listOfFood.add(food);
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView closeButton;

        FoodViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            name = view.findViewById(R.id.food_name);
            closeButton = view.findViewById(R.id.close_btn);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
