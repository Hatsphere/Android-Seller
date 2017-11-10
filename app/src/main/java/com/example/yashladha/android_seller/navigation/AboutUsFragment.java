package com.example.yashladha.android_seller.navigation;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

public class AboutUsFragment extends android.support.v4.app.Fragment {

    TextView tvAbout;
    ImageView ivAbout;
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_nav_about_us,container,false);
        tvAbout = (TextView) rootView.findViewById(R.id.tvAbout);
        ivAbout = (ImageView) rootView.findViewById(R.id.ivAbout);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("About Us");
    }
}
