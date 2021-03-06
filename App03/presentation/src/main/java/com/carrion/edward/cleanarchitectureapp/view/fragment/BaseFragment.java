package com.carrion.edward.cleanarchitectureapp.view.fragment;

import android.support.v4.app.Fragment;

import com.carrion.edward.cleanarchitectureapp.di.HasComponent;

public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
