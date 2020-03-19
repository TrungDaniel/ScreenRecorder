package com.trungdaniel.screenrecorder.screens;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungdaniel.screenrecorder.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {
    private NavController navController;
    final Handler handler = new Handler();

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view) {
        navController = Navigation.findNavController(view);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(R.id.action_splashFragment_to_mainFragment);
            }
        }, 1500);
    }
}
