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
        final TextView textViewStarter=goldFragmentLayout.findViewById(R.id.textViewForStarter);
        final TextView textViewAnnual=goldFragmentLayout.findViewById(R.id.textViewForAnnual);
        final TextView starterUnlocks=goldFragmentLayout.findViewById(R.id.textViewForStarterUnlocks);
        final TextView annualUnlocks=goldFragmentLayout.findViewById(R.id.textViewForAnnualUnlocks);
        final TextView faqOne=goldFragmentLayout.findViewById(R.id.faq_one);
        final TextView ansOne=goldFragmentLayout.findViewById(R.id.ans_one);
        final TextView faqTwo=goldFragmentLayout.findViewById(R.id.faq_two);
        final TextView ansTwo=goldFragmentLayout.findViewById(R.id.ans_two);
        final TextView faqThree=goldFragmentLayout.findViewById(R.id.faq_three);
        final TextView ansThree=goldFragmentLayout.findViewById(R.id.ans_three);
        final TextView faqFour=goldFragmentLayout.findViewById(R.id.faq_four);
        final TextView ansFour=goldFragmentLayout.findViewById(R.id.ans_four);
        final TextView faqFive=goldFragmentLayout.findViewById(R.id.faq_five);
        final TextView ansFive=goldFragmentLayout.findViewById(R.id.ans_five);
        final TextView faqSix=goldFragmentLayout.findViewById(R.id.faq_six);
        final TextView ansSix=goldFragmentLayout.findViewById(R.id.ans_six);
        final View cardViewForStarter=goldFragmentLayout.findViewById(R.id.cardViewForStarter);
        final View cardViewForAnnual=goldFragmentLayout.findViewById(R.id.cardViewForAnnual);
        final RadioButton radioButtonForStarter=goldFragmentLayout.findViewById(R.id.radioButtonForStarter);
        final RadioButton radioButtonForAnnual=goldFragmentLayout.findViewById(R.id.radioButtonForAnnual);
        cardViewForStarter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                textViewStarter.setTextColor(getResources().getColor(R.color.gold));
                textViewAnnual.setTextColor(getResources().getColor(R.color.light_grey));
                starterUnlocks.setTextColor(getResources().getColor(R.color.gold));
                annualUnlocks.setTextColor(getResources().getColor(R.color.light_grey));
                cardViewForStarter.startAnimation(animZoomOut);
                cardViewForStarter.startAnimation(animZoomIn);
                radioButtonForStarter.setChecked(true);
                radioButtonForAnnual.setChecked(false);
                cardViewForStarter.setBackgroundColor(getResources().getColor(R.color.gold_options_background_selected));
                cardViewForAnnual.setBackgroundColor(getResources().getColor(R.color.gold_options_background_not_selected));
            }
        });
        cardViewForAnnual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                textViewAnnual.setTextColor(getResources().getColor(R.color.gold));
                textViewStarter.setTextColor(getResources().getColor(R.color.light_grey));
                annualUnlocks.setTextColor(getResources().getColor(R.color.gold));
                starterUnlocks.setTextColor(getResources().getColor(R.color.light_grey));
                cardViewForAnnual.startAnimation(animZoomOut);
                cardViewForAnnual.startAnimation(animZoomIn);
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
        faqTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                if(ansTwo.getVisibility()==View.VISIBLE){
                    faqTwo.startAnimation(animZoomOut);
                    faqTwo.startAnimation(animZoomIn);
                    ansTwo.setVisibility(View.GONE);
                    faqTwo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_down_grey_24dp,0);
                }
                else {
                    faqTwo.startAnimation(animZoomOut);
                    ansTwo.startAnimation(animZoomOut);
                    faqTwo.startAnimation(animZoomIn);
                    ansTwo.startAnimation(animZoomIn);
                    ansTwo.setVisibility(View.VISIBLE);
                    faqTwo.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_up_green_24dp,0);
                }
            }
        });
        faqThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                if(ansThree.getVisibility()==View.VISIBLE){
                    faqThree.startAnimation(animZoomOut);
                    faqThree.startAnimation(animZoomIn);
                    ansThree.setVisibility(View.GONE);
                    faqThree.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_down_grey_24dp,0);
                }
                else {
                    faqThree.startAnimation(animZoomOut);
                    ansThree.startAnimation(animZoomOut);
                    faqThree.startAnimation(animZoomIn);
                    ansThree.startAnimation(animZoomIn);
                    ansThree.setVisibility(View.VISIBLE);
                    faqThree.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_up_green_24dp,0);
                }
            }
        });
        faqFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                if(ansFive.getVisibility()==View.VISIBLE){
                    faqFive.startAnimation(animZoomOut);
                    faqFive.startAnimation(animZoomIn);
                    ansFive.setVisibility(View.GONE);
                    faqFive.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_down_grey_24dp,0);
                }
                else {
                    faqFive.startAnimation(animZoomOut);
                    ansFive.startAnimation(animZoomOut);
                    faqFive.startAnimation(animZoomIn);
                    ansFive.startAnimation(animZoomIn);
                    ansFive.setVisibility(View.VISIBLE);
                    faqFive.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_up_green_24dp,0);
                }
            }
        });
        faqFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                if(ansFour.getVisibility()==View.VISIBLE){
                    faqFour.startAnimation(animZoomOut);
                    faqFour.startAnimation(animZoomIn);
                    ansFour.setVisibility(View.GONE);
                    faqFour.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_down_grey_24dp,0);
                }
                else {
                    faqFour.startAnimation(animZoomOut);
                    ansFour.startAnimation(animZoomOut);
                    faqFour.startAnimation(animZoomIn);
                    ansFour.startAnimation(animZoomIn);
                    ansFour.setVisibility(View.VISIBLE);
                    faqFour.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_up_green_24dp,0);
                }
            }
        });
        faqSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animZoomOut= AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
                Animation animZoomIn=AnimationUtils.loadAnimation(getContext(),R.anim.zoom_in);
                if(ansSix.getVisibility()==View.VISIBLE){
                    faqSix.startAnimation(animZoomOut);
                    faqSix.startAnimation(animZoomIn);
                    ansSix.setVisibility(View.GONE);
                    faqSix.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_down_grey_24dp,0);
                }
                else {
                    faqSix.startAnimation(animZoomOut);
                    ansSix.startAnimation(animZoomOut);
                    faqSix.startAnimation(animZoomIn);
                    ansSix.startAnimation(animZoomIn);
                    ansSix.setVisibility(View.VISIBLE);
                    faqSix.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_up_green_24dp,0);
                }
            }
        });
        return goldFragmentLayout;
    }
}
