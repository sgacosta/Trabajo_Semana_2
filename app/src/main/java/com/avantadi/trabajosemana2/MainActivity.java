package com.avantadi.trabajosemana2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;


    private TextView et_fecha_nacimiento;
    private TextView ti_main_nombre;
    private TextView ti_main_telefono;
    private TextView ti_main_email;
    private TextView ti_main_descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views para los datos
        et_fecha_nacimiento = (TextView) findViewById(R.id.et_fecha_nacimiento);
        ti_main_nombre = (TextView) findViewById(R.id.ti_main_nombre);
        ti_main_telefono = (TextView) findViewById(R.id.ti_main_telefono);
        ti_main_email = (TextView) findViewById(R.id.ti_main_email);
        ti_main_descripcion = (TextView) findViewById(R.id.ti_main_descripcion);

        Intent intentoRecibido = getIntent();
        Bundle parametros = intentoRecibido.getExtras();
        //si existen parámetros de Intent, se dotas los Views correspondientes
        if (intentoRecibido.hasExtra(getResources().getString(R.string.pNombre)))
            ti_main_nombre.setText(parametros.getString(getResources().getString(R.string.pNombre)));
        if (intentoRecibido.hasExtra(getResources().getString(R.string.pFechaNacimiento)))
            et_fecha_nacimiento.setText(parametros.getString(getResources().getString(R.string.pFechaNacimiento)));
        if (intentoRecibido.hasExtra(getResources().getString(R.string.pTelefono)))
            ti_main_telefono.setText(parametros.getString(getResources().getString(R.string.pTelefono)));
        if (intentoRecibido.hasExtra(getResources().getString(R.string.pEmail)))
            ti_main_email.setText(parametros.getString(getResources().getString(R.string.pEmail)));
        if (intentoRecibido.hasExtra(getResources().getString(R.string.pDescripcion)))
            ti_main_descripcion.setText(parametros.getString(getResources().getString(R.string.pDescripcion)));

        //la fecha inical que aparecerá en el calendario: si se ha enviado el parámetro del Intent se emplea, y si no se usa la del hoy
        calendar = Calendar.getInstance();
        if (et_fecha_nacimiento.getText().toString() == "") {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            //array con la fecha
            String[] diaMesAño = arrayFecha(et_fecha_nacimiento.getText().toString());
            if (diaMesAño.length == 3) {
                year = Integer.parseInt(diaMesAño[2]);
                month = Integer.parseInt(diaMesAño[1]);
                day = Integer.parseInt(diaMesAño[0]);

            } else {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

            }
        }


    }

    public void mostrarConfirmarDatos(View vista) {
        //creación Intent para pasar a Activity de verificación de datos
        Intent intento = new Intent(this, MainActivity2.class);
        intento.putExtra(getResources().getString(R.string.pNombre), ti_main_nombre.getText().toString());
        intento.putExtra(getResources().getString(R.string.pTelefono), ti_main_telefono.getText().toString());
        intento.putExtra(getResources().getString(R.string.pEmail), ti_main_email.getText().toString());
        intento.putExtra(getResources().getString(R.string.pDescripcion), ti_main_descripcion.getText().toString());
        intento.putExtra(getResources().getString(R.string.pFechaNacimiento), et_fecha_nacimiento.getText().toString());
        startActivity(intento);
        //se destruye esta Activity para conservar memoria
        finish();

    }

    //llamada al pulsar la Fecha de Nacimiento
    public void abrirCalendario(View view) {
        //el índice es cualquiera, para luego establecer que se trata del calendario
        showDialog(666);
    }

    //se dota el View de la fecha de nacimiento con la fecha
    private void mostarfecha(int year, int month, int day) {
        String final_date = completarConCero(day) + "/" + completarConCero(month) + "/" + Integer.toString(year);
        et_fecha_nacimiento.setText(final_date);
    }

    //para añadir un 0 si el núemro sólo tiene 1 dígito
    private String completarConCero(int n) {
        String resultado;
        if(n <= 9){
            resultado = "0" + Integer.toString(n);
        } else {
            resultado = Integer.toString(n);
        }
        return resultado;
    }
    //para obtener un array a partir de un Satring con una fecha en formato DD/MM/AAAA
    private String[] arrayFecha(String fechaSeparadaPorBarras) {
        String[] resultado = fechaSeparadaPorBarras.split("/");
        return resultado;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 666) {
            DatePickerDialog datepickerdialog = new DatePickerDialog(this, myDateListener, year, month, day);
            datepickerdialog.getDatePicker().setCalendarViewShown(false);
            return datepickerdialog;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        //cuando se establece la fecha en el calendario ...
        @Override
        public void onDateSet(DatePicker arg0, int year, int month_less, int day) {
            mostarfecha(year, month_less + 1, day);
        }
    };



}