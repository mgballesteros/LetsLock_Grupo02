package es.upv.gnd.letslock.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.upv.gnd.letslock.R;

public class InicioFragment extends Fragment {

    View vista;
    ImageView imageView;
    boolean image1Displaying = true;
    Toast toast;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_inicio, container, false);


        imageView = vista.findViewById(R.id.candado);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       if (image1Displaying){
                           imageView.setImageResource(R.drawable.tick_app);

                           toast.makeText(getActivity(), "Puerta abierta", Toast.LENGTH_SHORT).show();
                           image1Displaying = false;
                       }else{
                           imageView.setImageResource(R.drawable.candado_app);
                           toast.makeText(getActivity(), "Puerta cerrada", Toast.LENGTH_SHORT).show();
                           image1Displaying = true;
                       }

                   }
               },2000);

                return false;
            }

        });
        return vista;
    }
}