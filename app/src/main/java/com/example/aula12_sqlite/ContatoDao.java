package com.example.aula12_sqlite;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;
@Dao
public interface ContatoDao {//CRUDs
    @Insert
    public void insertContato(Contato contato);
    @Update
    public void updateContato(Contato contato);
    @Delete
    public void deleteContato(Contato contato);
    @Query("DELETE FROM CONTATOS where id_contato = (SELECT MAX(id_contato) FROM CONTATOS)")
    public void deleteUltimoContato();
    @Query("SELECT id_contato, nome_contato, telefone_contato FROM CONTATOS WHERE id_contato = :id")
    public Contato selectContatoById(int id);
    @Query("SELECT id_contato, nome_contato, telefone_contato FROM CONTATOS")
    public List<Contato> selectContatos();
}
