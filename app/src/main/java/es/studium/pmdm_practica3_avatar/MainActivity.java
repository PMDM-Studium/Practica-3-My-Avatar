package es.studium.pmdm_practica3_avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Interfaz, View.OnClickListener{
    Button btnCrear;
    dlgNombre dialogoNombre;
    EditText txtNombre;
    EditText txtSexo;
    EditText txtEspecie;
    EditText txtProfesion;
    EditText txtVida;
    EditText txtMagia;
    EditText txtFuerza;
    EditText txtVelocidad;
    ImageView imgAvatar;
    ImageView imgProfesion;
    String Nombre, Sexo, Especie, Profesion, Vida, Magia, Fuerza, Velocidad;
    Random aleatorio = new Random(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bloqueamos que la aplicacion se pueda girar.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asignacion a las variables creadas anteriormente.
        btnCrear = findViewById(R.id.btnCrear);
        txtNombre = findViewById(R.id.txtNombre);
        txtSexo = findViewById(R.id.txtSexo);
        txtEspecie = findViewById(R.id.txtEspecie);
        txtProfesion = findViewById(R.id.txtProfesion);
        txtVida = findViewById(R.id.txtVida);
        txtMagia = findViewById(R.id.txtMagia);
        txtFuerza = findViewById(R.id.txtFuerza);
        txtVelocidad = findViewById(R.id.txtVelocidad);
        //Ponemos los listener
        btnCrear.setOnClickListener((View.OnClickListener) this);
        //Indicamos que no se puedan editar los campos de texto
        txtNombre.setEnabled(false);
        txtSexo.setEnabled(false);
        txtEspecie.setEnabled(false);
        txtProfesion.setEnabled(false);
        txtVida.setEnabled(false);
        txtMagia.setEnabled(false);
        txtFuerza.setEnabled(false);
        txtVelocidad.setEnabled(false);
        imgAvatar = findViewById(R.id.imgAvatar);
        imgProfesion = findViewById(R.id.imgProfesion);
        imgAvatar.setImageResource(R.drawable.sinidentificar);
        imgProfesion.setImageResource(R.drawable.sin_definir);
    }
    //Verificamos que boton se ha pulsado
    public void onClick(View view) {
        if(view.equals(btnCrear)){
            crear(btnCrear);
        }
    }
    //Creamos las acciones para el boton crear
    public void crear (View view){
        dialogoNombre = new dlgNombre();
        dialogoNombre.setCancelable(false);//Establece que si pulsamos fuera del dialogo no se quite
        dialogoNombre.show(getSupportFragmentManager(),"Dialogo Nombre");
    }
    @Override
    public void onDlgGuardarListener() {
        Toast.makeText(this, R.string.dlg_guardado, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDlgCancelarListener() {
        Toast.makeText(this, R.string.dlg_cancelado, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDataSetNombre(String nombre) {
        //Toast.makeText(this, "nombre: "+nombre, Toast.LENGTH_SHORT).show();
        Nombre=nombre;
    }
    @Override
    public void onDataSetSexo(String sexo) {
        //Toast.makeText(this, "sexo: "+sexo, Toast.LENGTH_SHORT).show();
        Sexo=sexo;
    }
    @Override
    public void onDataSetEspecie(String especie) {
        //Toast.makeText(this, "especie: "+especie, Toast.LENGTH_SHORT).show();
        Especie=especie;

    }
    @Override
    public void onDataSetProfesion(String profesion) {
        //Toast.makeText(this, "profesion: "+profesion, Toast.LENGTH_SHORT).show();
        Profesion=profesion;
        //Rellenamos los datos
        txtNombre.setText(Nombre);
        txtSexo.setText(Sexo);
        txtEspecie.setText(Especie);
        txtProfesion.setText(Profesion);
        //Damos los valores aleatorios
        txtVida.setText(""+aleatorio.nextInt(100-1));
        txtMagia.setText(""+aleatorio.nextInt(10 - 1));
        txtFuerza.setText(""+aleatorio.nextInt(20-1));
        txtVelocidad.setText(""+aleatorio.nextInt(5 - 1));
        //Comprobamos el sexo y la raza para poner la imagen
        if((Sexo.equals("Hombre"))||Sexo.equals("Men")) {
            if ((Especie.equals("Humano"))||(Especie.equals("Human"))) {
                imgAvatar.setImageResource(R.drawable.humano_hombre);
            } else if ((Especie.equals("Enano"))||(Especie.equals("Dwarf"))) {
                imgAvatar.setImageResource(R.drawable.enano_hombre);
            } else if ((Especie.equals("Elfo"))||(Especie.equals("Elf"))) {
                imgAvatar.setImageResource(R.drawable.elfo_hombre);
            } else if (Especie.equals("Hobbit")) {
                imgAvatar.setImageResource(R.drawable.hobbit_hombre);
            }
        }else if ((Sexo.equals("Mujer"))||Sexo.equals("Women")) {
            if ((Especie.equals("Humano"))||(Especie.equals("Human"))) {
                imgAvatar.setImageResource(R.drawable.humano_mujer);
            } else if ((Especie.equals("Enano"))||(Especie.equals("Dwarf"))) {
                imgAvatar.setImageResource(R.drawable.enano_mujer);
            } else if ((Especie.equals("Elfo"))||(Especie.equals("Elf"))) {
                imgAvatar.setImageResource(R.drawable.elfo_mujer);
            } else if (Especie.equals("Hobbit")) {
                imgAvatar.setImageResource(R.drawable.hobbit_mujer);
            }
        }
        //Comprobamos la profesion eligida  para poner la imagen
        if ((Profesion.equals("Arquero"))||(Profesion.equals("Archer"))){
            imgProfesion.setImageResource(R.drawable.arquero);
        }else if ((profesion.equals("Guerrero"))||(Profesion.equals("Warrior"))){
            imgProfesion.setImageResource(R.drawable.guerrero);
        }else if ((Profesion.equals("Mago"))||(Profesion.equals("Mage"))){
            imgProfesion.setImageResource(R.drawable.mago);
        }else if ((Profesion.equals("Herrero"))||(Profesion.equals("Blacksmith"))){
            imgProfesion.setImageResource(R.drawable.herrero);
        }else if ((Profesion.equals("Minero"))||(Profesion.equals("Miner"))){
            imgProfesion.setImageResource(R.drawable.minero);
        }
    }
}