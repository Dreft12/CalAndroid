package com.dreft.calculadora;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Locale;

public class Principal extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv1;
    private Spinner spop;
    private Resources resources;

    public Principal() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        et1 =(EditText) findViewById(R.id.txtNum1);
        et2 =(EditText) findViewById(R.id.txtNum2);
        tv1 = (TextView) findViewById(R.id.lblRes);
        spop = (Spinner) findViewById(R.id.cmbOpcion);
        resources = this.getResources();
    }

    @SuppressLint("SetTextI18n")
    public void calcular(View view) {
        if (et1.getText().toString().isEmpty() && et2.getText().toString().isEmpty()) {
            double num1 = Double.parseDouble(et1.getText().toString());
            double num2 = Double.parseDouble(et2.getText().toString());
            double res = 0;
            if(spop.getSelectedItemPosition() != 0) {
                switch (spop.getSelectedItemPosition()) {
                    case 1: {
                        res = Metodos.sumar(num1, num2);
                        break;
                    }
                    case 2: {
                        res = Metodos.restar(num1, num2);
                        break;
                    }
                    case 3: {
                        res = Metodos.multiplicar(num1, num2);
                        break;
                    }
                    case 4: {
                        if(num2 != 0){
                            res = Metodos.dividir(num1, num2);
                        }else{
                            Toast.makeText(this,resources.getText(R.string.errornumcero), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
                tv1.setText("" + String.format(Locale.ENGLISH, "%.2f", res));
            }else{
                Toast.makeText(this,resources.getText(R.string.errorfaltaop), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,resources.getText(R.string.errorfaltanum), Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar(View view){
        et1.setText("");
        et2.setText("");
        spop.setSelection(0);
        tv1.setText(resources.getText(R.string.resultado));
        et1.requestFocus();
    }
}
