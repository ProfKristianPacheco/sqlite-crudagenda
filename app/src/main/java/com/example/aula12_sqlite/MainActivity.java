package com.example.aula12_sqlite;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;


import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText nome;
    private TextInputEditText telefone;
    private Button salvar;
    private Button excluir;
    private AppDatabase db;
    private ListView listViewContatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editTextNome); //INICIALIZANDO MEUS COMPONENTES DE TELA
        telefone = findViewById(R.id.editTextTelefone);
        salvar = findViewById(R.id.buttonSalvar);
        listViewContatos = findViewById(R.id.listViewContatos);
        excluir = findViewById(R.id.buttonExcluir);

        //ABRIR CONEXÃO COM O BANCO DE DADOS
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "AULA11.db").build();
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarContato();
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluirContato();
            }
        });
        carregarListView();
    }

    private void excluirContato() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.requestContatoDao().deleteUltimoContato();
                carregarListView();
            }
        }).start();
    }

    private void salvarContato() {
        String nomeContato = nome.getText().toString();
        String telefoneContato = telefone.getText().toString();
        if(nomeContato.isBlank() || telefoneContato.isBlank()){
            Toast.makeText(this, "Preencha nome e telefone", Toast.LENGTH_LONG).show();
            return;
        }
        Contato contato = new Contato(nomeContato, telefoneContato);

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.requestContatoDao().insertContato(contato);
                carregarListView();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nome.setText("");
                        telefone.setText("");
                    }
                });
            }
        }).start();

    }

    private void carregarListView(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Contato> contatos = db.requestContatoDao().selectContatos();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Contato> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, contatos);
                        listViewContatos.setAdapter(adapter);
                    }
                });

            }
        }).start();

    }
}