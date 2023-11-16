package com.example.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResult;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResult = findViewById(R.id.textResult);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // parametro: nome do arquivo (constatnte) e modo  ( somente o aplicativo acessa =0)
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                // validar nome se é igual a vazio
                if(editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o campo nome", Toast.LENGTH_SHORT).show();
                } else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome); // nome ficou salvo na chave "nome"
                    editor.commit();

                    textResult.setText("Olá, " + nome);
                }
            }
        });

        // recuperar os dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //  validar se temos o nome em preferencias
        if (preferences.contains("nome")){
            String nome = preferences.getString("nome", "usuário não definido");
            textResult.setText("Olá, " + nome);
        } else {
            textResult.setText("Olá, usuário não definido");
        }
    }
}
