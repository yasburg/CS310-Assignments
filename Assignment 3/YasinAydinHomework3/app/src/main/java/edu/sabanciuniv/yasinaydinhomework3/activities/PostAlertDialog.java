package edu.sabanciuniv.yasinaydinhomework3.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static androidx.core.os.LocaleListCompat.create;

public class PostAlertDialog extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //AlertDialog: AlertDialogBuilder (Builder Pattern)
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());

        androidx.appcompat.app.AlertDialog dialog = builder.setTitle("Warning").setMessage("Your comment could not be posted! There is a problem with this service!")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();

        return dialog;
    }
}
