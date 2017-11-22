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
            fragmentTransaction.add(android.R.id.content, fragmenta,AFragment.TAG_FragmentA);
            //fragmentTransaction.replace(android.R.id.content, fragmenta,AFragment.TAG_FragmentA);

            fragmentTransaction.commit();
        }

    }

    @Override
    public void OnFragmentAEvent(String message, int size) {
        //fragmentb = new BFragment();
        Bundle bundle = new Bundle();

        bundle.putString("message",message);
        bundle.putInt("size",size);
        /**
         * con el metodo setArgument se pasa los argumentos /informacion  que necesita el fragment
         */
        //fragmentb.setArguments(bundle);

         //se deve utilizar el patron refoactory donde la creacion del objeto y el paso de argumentos se
        //ejecute consecutivamente.
        fragmentb = BFragment.newInstance(bundle);

        //A continuacion se empieza la transaccion de FragmentA a FragmentB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,fragmentb);
        //antes de realizar el commit hay que guardar la transacion;
        fragmentTransaction.addToBackStack(null);//sirve para no tener un back estra elimina su back
        fragmentTransaction.commit();
    }
}
