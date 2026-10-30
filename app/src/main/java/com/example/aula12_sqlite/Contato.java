package com.example.aula12_sqlite;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CONTATOS")
public class Contato {
    @ColumnInfo(name = "id_contato")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nome_contato")
    private String nome;
    @ColumnInfo(name = "telefone_contato")
    private String telefone;

    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
