package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textNumeros;
    private double numeroActual = 0;
    private String operadorActual = "";
    private boolean operadorPresionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNumeros = findViewById(R.id.TextNumeros);

        // Configura los botones numéricos
        configureNumericButtons();

        // Configura los botones de operadores
        configureOperatorButtons();

        // Botón igual
        Button buttonIgual = findViewById(R.id.igual);
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion();
            }
        });
    }

    private void configureNumericButtons() {
        int[] numeros = {R.id.uno, R.id.dos, R.id.tres, R.id.cuatro, R.id.cinco, R.id.seis, R.id.siete, R.id.ocho, R.id.nueve, R.id.cero};

        for (int id : numeros) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (operadorPresionado) {
                        textNumeros.setText("");
                        operadorPresionado = false;
                    }
                    String buttonText = ((Button) v).getText().toString();
                    textNumeros.append(buttonText);
                }
            });
        }
    }

    private void configureOperatorButtons() {
        int[] operadores = {R.id.suma, R.id.resta, R.id.multiplicacion, R.id.division};

        for (int id : operadores) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!operadorPresionado) {
                        if (!operadorActual.isEmpty()) {
                            realizarOperacion();
                        }
                        operadorActual = ((Button) v).getText().toString();
                        numeroActual = Double.parseDouble(textNumeros.getText().toString());
                        operadorPresionado = true;
                    }
                }
            });
        }
    }

    private void realizarOperacion() {
        if (!operadorActual.isEmpty()) {
            double numeroIngresado = Double.parseDouble(textNumeros.getText().toString());
            switch (operadorActual) {
                case "+":
                    numeroActual += numeroIngresado;
                    break;
                case "-":
                    numeroActual -= numeroIngresado;
                    break;
                case "x":
                    numeroActual *= numeroIngresado;
                    break;
                case "÷":
                    numeroActual /= numeroIngresado;
                    break;
            }
            textNumeros.setText(String.valueOf(numeroActual));
            operadorActual = "";
        }
    }
}
