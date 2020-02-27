package com.hajesha.recipelist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AddRecipeDialogFragment extends DialogFragment {

    public interface AddRecipeDialogListener {
        void onDialogPositiveClick(AddRecipeDialogFragment dialog, String text);

        void onDialogNegativeClick(AddRecipeDialogFragment dialog);
    }

    private AddRecipeDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AddRecipeDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_add_recipe, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogLayout)
                .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = dialogLayout.findViewById(R.id.recipe_name);
                        listener.onDialogPositiveClick(AddRecipeDialogFragment.this, editText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(AddRecipeDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
