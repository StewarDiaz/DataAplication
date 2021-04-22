package com.JDHCreations.dataaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEnviar;
    private EditText txtId;
    private EditText txtNombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtIdfield);
        txtNombre = findViewById(R.id.txtName);
        btnEnviar = findViewById(R.id.btnSend);
        btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id = txtId.getText().toString();
        int fid = Integer.parseInt(id);
        String nombre = txtNombre.getText().toString();


        MyDBHelper db = new MyDBHelper(this);
        Area area = new Area();
        area.setId(fid);
        area.setName(nombre);
        db.insertArea(db.getWritableDatabase(), area);

        ArrayList<Area> areas = db.selectArea(db.getWritableDatabase());
        int i = 1;
        for(Area areaSelected : areas){
            System.out.println("El area "+ i + " es igual a " + areaSelected.getName() + " y su identificador es: " + areaSelected.getId());
        }
    }
}