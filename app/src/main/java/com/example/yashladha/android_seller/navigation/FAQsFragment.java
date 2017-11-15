package com.example.yashladha.android_seller.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

public class FAQsFragment extends android.support.v4.app.Fragment {

    TextView tvQues1, tvAns1, tvQues2, tvAns2, tvQues3, tvAns3, tvQues4, tvAns4;
    ImageView ivFaq;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView = inflater.inflate(R.layout.frag_nav_faqs, container, false);

        tvQues1 = (TextView) rootView.findViewById(R.id.tvQues1);
        tvAns1 = (TextView) rootView.findViewById(R.id.tvAns1);
        tvQues2 = (TextView) rootView.findViewById(R.id.tvQues2);
        tvAns2 = (TextView) rootView.findViewById(R.id.tvAns2);
        tvQues3 = (TextView) rootView.findViewById(R.id.tvQues3);
        tvAns3 = (TextView) rootView.findViewById(R.id.tvAns3);
        tvQues4 = (TextView) rootView.findViewById(R.id.tvQues4);
        tvAns4 = (TextView) rootView.findViewById(R.id.tvAns4);

        ivFaq = (ImageView) rootView.findViewById(R.id.ivFaq);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("FAQs");
    }
}
