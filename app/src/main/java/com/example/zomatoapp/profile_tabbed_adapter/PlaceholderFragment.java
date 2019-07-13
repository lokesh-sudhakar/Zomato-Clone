package com.example.zomatoapp.profile_tabbed_adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.zomatoapp.R;
import com.example.zomatoapp.ui.order_tab_adapter.PageViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    int index;

    public static PlaceholderFragment newInstance(int index) {
        index = index;
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
//        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tabbed_, container, false);
        ImageView sectionImage = root.findViewById(R.id.section_Image_view);
        switch(index){
            case 1:{
                sectionImage.setImageResource(R.drawable.profile_deadline);
                break;
            }
            case 2:{
                sectionImage.setImageResource(R.drawable.profile_review);
                break;
            }
            case 3:{
                sectionImage.setImageResource(R.drawable.profile_photos);
                break;
            }
            case 4:{
                sectionImage.setImageResource(R.drawable.profile_been_there);
                break;
            }

        }

        return root;
    }
}