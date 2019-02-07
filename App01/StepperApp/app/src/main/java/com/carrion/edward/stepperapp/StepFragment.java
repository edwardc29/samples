package com.carrion.edward.stepperapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StepFragment extends Fragment {

    public static final String STEP_KEY = "step";

    public static StepFragment newInstance(int step) {
        StepFragment stepFragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(STEP_KEY, step);
        stepFragment.setArguments(args);
        return stepFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        TextView stepTextView = view.findViewById(R.id.tv_step);

        Bundle args = getArguments();

        if (args != null && args.containsKey(STEP_KEY)) {
            stepTextView.setText(String.valueOf(args.getInt(STEP_KEY)));
        }

        return view;
    }
}
