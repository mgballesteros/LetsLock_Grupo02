package es.upv.gnd.letslock.bbdd;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Usuarios {

    //Establece en la base de datos el usuario
    static public void setUsuario(Usuario usuario) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(user.getUid()).set(usuario);
    }

    //Obtiene el usuario de la base de datos
    static public void getUsuario(final UsuariosCallback callback) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                //Si consigue leer en Firestore
                if (task.isSuccessful()) {

                    //Si existe el usuario que queremos obtener lo devolvemos mediante un callback(Es asíncrono)
                    if(task.getResult().exists()){

                        String nombre = task.getResult().getString("nombre");
                        boolean permisos = task.getResult().getBoolean("permisos");
                        String Foto = task.getResult().getString("foto");
                        String Tag = task.getResult().getString("tag");
                        String id = task.getResult().getString("id");

                        Usuario usuario= new Usuario(nombre,permisos,Foto,Tag, id);
                        callback.getUsuariosCallback(usuario);

                    //Si no existe devolvemos uno vacío
                    }else{

                        Usuario usuario= new Usuario();
                        callback.getUsuariosCallback(usuario);
                    }
                } else {

                    Log.e("Firestore", "Error al leer", task.getException());
                }
            }
        });
    }
}