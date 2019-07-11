package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GoldFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View goldFragmentLayout=inflater.inflate(R.layout.gold_fragment,container,false);
        View cardViewForStarter=goldFragmentLayout.findViewById(R.id.cardViewForStarter);
        View cardViewForAnnual=goldFragmentLayout.findViewById(R.id.cardViewForAnnual);
        return goldFragmentLayout;
    }
}
