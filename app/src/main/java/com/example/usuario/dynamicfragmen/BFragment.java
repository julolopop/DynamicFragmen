package com.example.usuario.dynamicfragmen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BFragment extends Fragment {

    private TextView txv_Texto;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        if (bundle != null) {
            txv_Texto.setText(bundle.getString("message"));
            txv_Texto.setTextSize(bundle.getInt("size"));

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        txv_Texto = view.findViewById(R.id.txv_texto);
        return view;
    }

/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", txv_Texto.getText().toString());
        outState.putFloat("size", txv_Texto.getTextSize() / getResources().getDisplayMetrics().scaledDensity);
    }*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            txv_Texto.setText(savedInstanceState.getString("message"));
            txv_Texto.setTextSize(savedInstanceState.getFloat("size"));
        }
    }


    /**
     * ATENCIÓN para que el estado dinamico de un fragment sea permanente ante un cambio
     * de configuracion usar setRetainInstance(true);
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * patron Factory que es una simplificacion del patrón builder
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle){
        BFragment fragmentb = new BFragment();
        if(bundle != null){
            fragmentb.setArguments(bundle);
        }
        return fragmentb;
    }
}
