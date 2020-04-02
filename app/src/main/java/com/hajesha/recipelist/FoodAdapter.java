package com.hajesha.recipelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hajesha.recipelist.model.RecipeObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> implements Filterable {

    private List<RecipeObject> listOfFood;
    private OnItemClickListener mItemClickListener;
    private ItemFilter mFilter = new ItemFilter();
    private List<RecipeObject> filteredData;

    FoodAdapter() {
        this.filteredData = new ArrayList<>();
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
        holder.name.setText(filteredData.get(position).getName());
        holder.ingred.setText(filteredData.get(position).getIngredients());
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            //todo : need to split and iterate
            FilterResults results = new FilterResults();
            final List<RecipeObject> list = listOfFood;
            int count = list.size();
            final ArrayList<RecipeObject> nlist = new ArrayList<>(count);

            String filterableString; //individual item

            for (int i = 0; i < count; i++) {
                RecipeObject food = list.get(i);
                String[] separated = food.getIngredients().split(",");
                if (separated.length > 0) {
                    filterableString = separated[0];
                    filterableString = filterableString.trim();
                    if (filterableString.toLowerCase().contains(filterString)) {
                        nlist.add(food);
                    }
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<RecipeObject>) results.values;
            notifyDataSetChanged();
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    void removeFoodAt(int pos) {
        listOfFood.remove(pos);
        filteredData.remove(pos);
    }

    void addFood(@NonNull RecipeObject food) {
        listOfFood.add(food);
        filteredData.add(food);
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView ingred;
        ImageView closeButton;

        FoodViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            name = view.findViewById(R.id.food_name);
            ingred = view.findViewById(R.id.food_ingred);
            closeButton = view.findViewById(R.id.close_btn);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
