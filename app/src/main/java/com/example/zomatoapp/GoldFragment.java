package com.example.zomatoapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class GoldFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View goldFragmentLayout=inflater.inflate(R.layout.gold_fragment,container,false);
        final TextView faqOne=goldFragmentLayout.findViewById(R.id.faq_one);
        final TextView ansOne=goldFragmentLayout.findViewById(R.id.ans_one);
        final View cardViewForStarter=goldFragmentLayout.findViewById(R.id.cardViewForStarter);
        final View cardViewForAnnual=goldFragmentLayout.findViewById(R.id.cardViewForAnnual);
        final RadioButton radioButtonForStarter=goldFragmentLayout.findViewById(R.id.radioButtonForStarter);
        final RadioButton radioButtonForAnnual=goldFragmentLayout.findViewById(R.id.radioButtonForAnnual);
        cardViewForStarter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                radioButtonForStarter.setChecked(true);
                radioButtonForAnnual.setChecked(false);
                cardViewForStarter.setBackgroundColor(getResources().getColor(R.color.gold_options_background_selected));
                cardViewForAnnual.setBackgroundColor(getResources().getColor(R.color.gold_options_background_not_selected));
            }
        });
        cardViewForAnnual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButtonForAnnual.setChecked(true);
                radioButtonForStarter.setChecked(false);
                cardViewForStarter.setBackgroundColor(getResources().getColor(R.color.gold_options_background_not_selected));
                cardViewForAnnual.setBackgroundColor(getResources().getColor(R.color.gold_options_background_selected));
            }
        });
        faqOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                if(ansOne.getVisibility()==View.VISIBLE){
                    faqOne.startAnimation(animZoomOut);
                    faqOne.startAnimation(animZoomIn);
                    ansOne.setVisibility(View.GONE);
                    faqOne.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_down_grey_24dp,0);
                }
                else {
                    faqOne.startAnimation(animZoomOut);
                    ansOne.startAnimation(animZoomOut);
                    faqOne.startAnimation(animZoomIn);
                    ansOne.startAnimation(animZoomIn);
                    ansOne.setVisibility(View.VISIBLE);
                    faqOne.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_up_green_24dp,0);
                }
            }
        });
        return goldFragmentLayout;
    }
}
