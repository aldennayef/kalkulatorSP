package com.example.kalkulatorsp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.kalkulatorsp.database.entitas.history;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM history")
    List<history> getAll();

    @Query("INSERT INTO history (angka1,angka2,operator,hasil) VALUES(:angka1,:angka2,:operator,:hasil)")
    void insertAll(String angka1, String angka2,String operator, String hasil);

    @Query("SELECT * FROM history WHERE `no`=:no")
    history get(int no);

    @Delete
    void delete(history history);
}
