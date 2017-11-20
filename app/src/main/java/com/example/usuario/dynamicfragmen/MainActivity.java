package com.example.usuario.dynamicfragmen;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements AFragment.FragmentAListener {

    private Fragment fragmenta;
    private Fragment fragmentb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        fragmenta = fragmentManager.findFragmentByTag(AFragment.TAG_FragmentA);
        if(fragmenta==null) {
            fragmenta = new AFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content, fragmenta);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void OnFragmentAEvent(String message, int size) {
        fragmentb = new BFragment();
        Bundle bundle = new Bundle();

        bundle.putString("message",message);
        bundle.putInt("size",size);
        /**
         * con el metodo setArgument se pasa los argumentos /informacion  que necesita el fragment
         */
        fragmentb.setArguments(bundle);
        //A continuacion se empieza la transaccion de FragmentA a FragmentB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,fragmentb);
        fragmentTransaction.commit();
    }
}
