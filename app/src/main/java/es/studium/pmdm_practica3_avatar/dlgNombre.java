package es.studium.pmdm_practica3_avatar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class dlgNombre extends DialogFragment {
    Interfaz mListener;
    EditText nombre;
    dlgSexo dlgSexo;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
//Construir el dialogo
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlgnombre, null));

        View MyView = inflater.inflate(R.layout.dlgnombre,null);
        nombre=MyView.findViewById(R.id.lblNombre);

        builder.setView(MyView)
                .setTitle(R.string.dlg_crear)
                //Establecemos que hara el boton Guardar
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            mListener.onDataSetNombre(nombre.getText().toString());
                        }catch (Exception error){
                            Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        mListener.onDlgGuardarListener();
                        dlgSexo = new dlgSexo();
                        dlgSexo.setCancelable(false);//Establece que si pulsamos fuera del dialogo no se quite
                        dlgSexo.show(getFragmentManager(),"Dialogo Sexo");
                    }
                })
                //Establecemos que hara el boton cancelar
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cerrar el dlalogo simplemente
                        dialog.dismiss();
                        mListener.onDlgCancelarListener();
                    }
                });
// Crear Objeto y vevolverlo
        return builder.create();
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener=(Interfaz) context;
        }
        catch (ClassCastException error){
            throw new ClassCastException(context.toString()+" debe implementar InterfazNombre");
        }
    }
}