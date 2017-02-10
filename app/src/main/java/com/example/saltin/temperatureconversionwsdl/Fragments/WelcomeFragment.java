package com.example.saltin.temperatureconversionwsdl.Fragments;

/**
 * Created by saltin on 08.02.2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saltin.temperatureconversionwsdl.R;

public class WelcomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        return rootView;

    }
}
