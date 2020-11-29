package es.studium.pmdm_practica3_avatar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class dlgSexo extends DialogFragment {
    Interfaz mListener;
    RadioButton rdBHombre;
    RadioButton rdBMujer;
    dlgEspecie dlgEspecie;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlgsexo, null));

        View MyView = inflater.inflate(R.layout.dlgsexo, null);
        rdBHombre = MyView.findViewById(R.id.rdHombre);
        rdBMujer = MyView.findViewById(R.id.rdMujer);

        builder.setView(MyView)
                .setTitle(R.string.dlg_crear)
                //Establecemos que hara el boton Guardar
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            if (rdBHombre.isChecked()) {
                                mListener.onDataSetSexo(rdBHombre.getText().toString());
                            } else {
                                mListener.onDataSetSexo(rdBMujer.getText().toString());
                            }
                        } catch (Exception error) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        mListener.onDlgGuardarListener();
                        dlgEspecie = new dlgEspecie();
                        dlgEspecie.setCancelable(false);//Establece que si pulsamos fuera del dialogo no se quite
                        dlgEspecie.show(getFragmentManager(), "Dialogo especie");
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
            mListener = (Interfaz) context;
        } catch (ClassCastException error) {
            throw new ClassCastException(context.toString() + " debe implementar InterfazNombre");
        }
    }
}