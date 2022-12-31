package com.example.kalkulatorsp.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class history {
    @PrimaryKey
    public int no;

    public String angka1;

    public String angka2;

    public String operator;

    public String hasil;
}
