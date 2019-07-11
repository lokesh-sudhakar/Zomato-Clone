package com.example.zomatoapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class GoldFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View goldFragmentLayout=inflater.inflate(R.layout.gold_fragment,container,false);
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
        return goldFragmentLayout;
    }
}
