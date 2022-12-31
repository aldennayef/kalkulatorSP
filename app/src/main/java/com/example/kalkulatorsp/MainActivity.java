package com.example.kalkulatorsp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kalkulatorsp.adapter.HistoryAdapter;
import com.example.kalkulatorsp.database.AppDatabase;
import com.example.kalkulatorsp.database.entitas.history;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtAngka1,edtAngka2;
    Spinner spnOperator;
    TextView txtAngka1,txtAngka2,txtOperator,txtHasil;
    Button btnHitung;
    private RecyclerView recyclerView;
    private AppDatabase database;
    private HistoryAdapter historyAdapter;
    private List<history> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);

        initComponent();
        database = AppDatabase.getInstance(getApplicationContext());

        list.clear();
        list.addAll(database.historyDao().getAll());
        historyAdapter = new HistoryAdapter(getApplicationContext(),list);
        historyAdapter.setDialog(new HistoryAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                history history = list.get(position);
                                database.historyDao().delete(history);
                                onStart();  //untuk mengclearkan data
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(historyAdapter);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int angka1 = (int) Integer.parseInt(edtAngka1.getText().toString());
                int angka2 = (int) Integer.parseInt(edtAngka2.getText().toString());
                String operator = ((Operator)spnOperator.getSelectedItem()).getOperator();
                if(operator == "tambah"){
                    int hasil = (int) angka1+angka2;
                    //txtAngka1.setText(edtAngka1.getText());
                    //txtOperator.setText("+");
                    //txtAngka2.setText(edtAngka2.getText());
                    txtHasil.setText(""+hasil);
                    database.historyDao().insertAll(edtAngka1.getText().toString(),edtAngka2.getText().toString(),((Operator)spnOperator.getSelectedItem()).getOperator(),txtHasil.getText().toString());
                }
                else if(operator == "kurang"){
                    int hasil = (int) angka1-angka2;
                    //txtAngka1.setText(edtAngka1.getText());
                    //txtOperator.setText("+");
                    //txtAngka2.setText(edtAngka2.getText());
                    txtHasil.setText(""+hasil);
                    database.historyDao().insertAll(edtAngka1.getText().toString(),edtAngka2.getText().toString(),((Operator)spnOperator.getSelectedItem()).getOperator(),txtHasil.getText().toString());
                }
                else if(operator == "kali"){
                    int hasil = (int) angka1*angka2;
                    //txtAngka1.setText(edtAngka1.getText());
                    //txtOperator.setText("+");
                    //txtAngka2.setText(edtAngka2.getText());
                    txtHasil.setText(""+hasil);
                    database.historyDao().insertAll(edtAngka1.getText().toString(),edtAngka2.getText().toString(),((Operator)spnOperator.getSelectedItem()).getOperator(),txtHasil.getText().toString());
                }
                else if(operator == "bagi"){
                    int hasil = (int) angka1/angka2;
                    //txtAngka1.setText(edtAngka1.getText());
                    //txtOperator.setText("+");
                    //txtAngka2.setText(edtAngka2.getText());
                    txtHasil.setText(""+hasil);
                    database.historyDao().insertAll(edtAngka1.getText().toString(),edtAngka2.getText().toString(),((Operator)spnOperator.getSelectedItem()).getOperator(),txtHasil.getText().toString());
                }

                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });


    }

    /*public void Hitunghasil(View view) {
        //double angka1 = Double.parseDouble(edtAngka1.getText().toString());
        //double angka2 = Double.parseDouble(edtAngka2.getText().toString());
        int angka1 = (int) Integer.parseInt(edtAngka1.getText().toString());
        int angka2 = (int) Integer.parseInt(edtAngka2.getText().toString());
        String operator = ((Operator)spnOperator.getSelectedItem()).getOperator();
        if(operator == "tambah"){
            int hasil = (int) angka1+angka2;
            txtAngka1.setText(edtAngka1.getText());
            txtOperator.setText("+");
            txtAngka2.setText(edtAngka2.getText());
            txtHasil.setText(""+hasil);
        }
        else if(operator == "kurang"){
            int hasil = (int) angka1-angka2;
            txtAngka1.setText(edtAngka1.getText());
            txtOperator.setText("-");
            txtAngka2.setText(edtAngka2.getText());
            txtHasil.setText(""+hasil);
        }
        else if(operator == "kali"){
            int hasil = (int) angka1*angka2;
            txtAngka1.setText(edtAngka1.getText());
            txtOperator.setText("*");
            txtAngka2.setText(edtAngka2.getText());
            txtHasil.setText(""+hasil);
        }
        else if(operator == "bagi"){
            int hasil = (int) angka1/angka2;
            txtAngka1.setText(edtAngka1.getText());
            txtOperator.setText("/");
            txtAngka2.setText(edtAngka2.getText());
            txtHasil.setText(""+hasil);
        }

    }*/

    private void initComponent() {
        edtAngka1 = findViewById(R.id.edtangka1);
        edtAngka2 = findViewById(R.id.edtangka2);
        spnOperator = findViewById(R.id.spnoperator);
        txtHasil = findViewById(R.id.txt_hasil);
        /*txtAngka1 = findViewById(R.id.txt_angka1);
        txtAngka2 = findViewById(R.id.txt_angka2);
        txtOperator = findViewById(R.id.txt_operator);
        txtHasil = findViewById(R.id.txt_hasil);*/
        btnHitung = findViewById(R.id.btnhitung);

        ArrayList<Operator> listoperator = new ArrayList<>();
        listoperator.add(new Operator("tambah"));
        listoperator.add(new Operator("kurang"));
        listoperator.add(new Operator("kali"));
        listoperator.add(new Operator("bagi"));

        ArrayAdapter<Operator> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listoperator);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spnOperator.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.historyDao().getAll());
        historyAdapter.notifyDataSetChanged();
    }
}