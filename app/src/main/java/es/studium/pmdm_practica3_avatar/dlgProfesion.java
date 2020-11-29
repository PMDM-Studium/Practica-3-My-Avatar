package es.studium.pmdm_practica3_avatar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class dlgProfesion extends DialogFragment {
    Interfaz mListener;
    Spinner spinnerProfecion;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        //Construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlgprofesion, null));

        View MyView = inflater.inflate(R.layout.dlgprofesion,null);

        spinnerProfecion = MyView.findViewById(R.id.spinnerProfecion);

        String[] types = getResources().getStringArray(R.array.despegableProfesion);
        // Creamos el array para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MyView.getContext(),
                R.array.despegableProfesion, android.R.layout.simple_spinner_item);
        // Especificamos el dineño del spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // aplicamos el diseño al spinner
        spinnerProfecion.setAdapter(adapter);

        builder.setView(MyView)
                .setTitle(R.string.dlg_crear)
                //Establecemos que hara el boton Guardar
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            mListener.onDataSetProfesion(spinnerProfecion.getSelectedItem().toString());
                        }catch (Exception error){
                            Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
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
