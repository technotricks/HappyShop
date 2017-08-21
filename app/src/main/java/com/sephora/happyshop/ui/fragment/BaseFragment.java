package com.sephora.happyshop.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by simtech on 21/8/2017.
 */

public class BaseFragment extends Fragment {


    public Context context;
    public Intent intent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    public void baseInitialization(){


        context = getActivity().getBaseContext();
    }
}
