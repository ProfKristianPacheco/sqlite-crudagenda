package com.example.aula12_sqlite;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //TODOS OS MÉTODOS QUE RETORNAM ACESSO ÀS DAO'S
    public abstract ContatoDao requestContatoDao();
}
