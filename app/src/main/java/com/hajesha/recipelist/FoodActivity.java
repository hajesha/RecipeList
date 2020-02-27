package com.hajesha.recipelist;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hajesha.recipelist.model.RecipeObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodActivity extends AppCompatActivity implements AddRecipeDialogFragment.AddRecipeDialogListener {
    private FoodAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.food_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FoodAdapter();
        recyclerView.setAdapter(mAdapter);

        searchView = findViewById(R.id.search_food);

        mAdapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mAdapter.removeFoodAt(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddRecipeDialog();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {
                    mAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void showAddRecipeDialog() {
        DialogFragment dialog = new AddRecipeDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddRecipeDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(AddRecipeDialogFragment dialog, @NonNull String name, String ingred) {
        mAdapter.addFood(new RecipeObject(name, ingred));
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(AddRecipeDialogFragment dialog) {
        dialog.dismiss();
    }

}
