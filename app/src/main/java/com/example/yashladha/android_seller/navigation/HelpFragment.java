package com.example.yashladha.android_seller.navigation;

import android.app.Fragment;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yashladha.android_seller.R;

import org.w3c.dom.Text;

public class HelpFragment extends android.support.v4.app.Fragment {

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.nav_help,container,false);
        tv1 = (TextView) rootView.findViewById(R.id.tvTitle);
        tv2 = (TextView) rootView.findViewById(R.id.tvIntro);
        tv3 = (TextView) rootView.findViewById(R.id.tvIntroContent);
        tv4 = (TextView) rootView.findViewById(R.id.tvLogin);
        tv5 = (TextView) rootView.findViewById(R.id.tvLoginContent);
        tv6 = (TextView) rootView.findViewById(R.id.tvChoice);
        tv7 = (TextView) rootView.findViewById(R.id.tvChoiceContent);
        tv8 = (TextView) rootView.findViewById(R.id.tvTrial);
        tv9 = (TextView) rootView.findViewById(R.id.tvTrialContent);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("User Guide");
    }
}
