package com.example.laundry_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class OTPDialog extends AppCompatDialogFragment{

    EditText etxtOtp;
    static String otp;
    Intent intent;
    private OTPDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        etxtOtp = (EditText) view.findViewById(R.id.etxt_otp);

        builder.setView(view)
                .setTitle("Authentication" + "\n")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            String otp = etxtOtp.getText().toString();
                            listener.applTexts(otp);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OTPDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "must implement Dialog Box");
        }
    }


    public interface OTPDialogListener{
        void applTexts(String otp);
    }

}
