package com.example.yashladha.android_seller.navigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.example.yashladha.android_seller.LoginActivity;

/**
 * Created by Shiv_PC on 11/10/2017.
 */

public class SomeDialog extends DialogFragment {

    Context mContext;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Logging Out")
                .setMessage("Do you really want to log out!")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing (will close dialog)
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do something
                        MyAccountFragment m = new MyAccountFragment();
                        //m.logOut();
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myprfs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        getContext().startActivity(i);
                        getActivity().finish();
                    }
                })
                .create();
    }
}

