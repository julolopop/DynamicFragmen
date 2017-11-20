package com.example.usuario.dynamicfragmen;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


public class AFragment extends Fragment {
    private FragmentAListener mCallBack;
    public static String TAG_FragmentA = "FragmentA";
    private EditText edt_Texto;
    private Button btn_click;
    private SeekBar sb_tamano;


    public interface FragmentAListener {
        void OnFragmentAEvent(String message, int size);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (FragmentAListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "fallo de implementacion");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        edt_Texto = view.findViewById(R.id.edt_Texto);
        btn_click = view.findViewById(R.id.btn_click);
        sb_tamano = view.findViewById(R.id.sb_tamano);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.OnFragmentAEvent(edt_Texto.getText().toString(), sb_tamano.getProgress());
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

}
