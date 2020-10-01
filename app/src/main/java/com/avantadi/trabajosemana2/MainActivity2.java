package com.avantadi.trabajosemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tvNombre;
    TextView tvFechaNacimiento;
    TextView tvTelefono;
    TextView tvEmail;
    TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Views para los datos enviados
        tvNombre = findViewById(R.id.tvNombre);
        tvFechaNacimiento = findViewById(R.id.tvFechaNacimiento);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        //parametros enviados en el Intent
        Bundle parametros = getIntent().getExtras();
        tvNombre.setText(parametros.getString(getResources().getString(R.string.pNombre)));
        tvFechaNacimiento.setText(parametros.getString(getResources().getString(R.string.pFechaNacimiento)));
        tvTelefono.setText(parametros.getString(getResources().getString(R.string.pTelefono)));
        tvEmail.setText(parametros.getString(getResources().getString(R.string.pEmail)));
        tvDescripcion.setText(parametros.getString(getResources().getString(R.string.pDescripcion)));

    }

    public void volverAEditarDatos(View vista){
        //creación de Intent con los parámetros de los datos
        Intent intento = new Intent(this,MainActivity.class);
        intento.putExtra(getResources().getString(R.string.pNombre),tvNombre.getText().toString());
        intento.putExtra(getResources().getString(R.string.pTelefono),tvTelefono.getText().toString());
        intento.putExtra(getResources().getString(R.string.pEmail),tvEmail.getText().toString());
        intento.putExtra(getResources().getString(R.string.pDescripcion),tvDescripcion.getText().toString());
        intento.putExtra(getResources().getString(R.string.pFechaNacimiento),tvFechaNacimiento.getText().toString());
        startActivity(intento);
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //si se pulsa el icono retroceder se equipara con pulsar el botón de volver a editar
        if(keyCode == KeyEvent.KEYCODE_BACK){
            volverAEditarDatos(findViewById(R.id.btn_editar));
        }
        return super.onKeyDown(keyCode, event);
    }
}