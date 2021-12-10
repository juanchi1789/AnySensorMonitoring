package com.example.anysensormonitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Med extends AppCompatActivity implements ItemListener{

    private ArrayList<example_item> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private ImageButton buttonInsert;
    private ImageButton buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;
    private EditText nombre_med;
    private EditText descrip_med;
    private Button button_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);

        load_data();

        //createExampleList();
        buildRecyclerView();

        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);
        nombre_med = findViewById(R.id.Nombre_med);
        descrip_med = findViewById(R.id.Descrip_med);
        button_save = findViewById(R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_data();

            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);

            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });

    }

    private void save_data(){

        SharedPreferences SharedPreferences = getSharedPreferences("SharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = SharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("Task list",json);
        editor.apply();

    }

    private void load_data(){
        SharedPreferences SharedPreferences = getSharedPreferences("SharedPreferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = SharedPreferences.getString("Task list",null);
        Type type = new TypeToken<ArrayList<example_item>>() {}.getType();
        mExampleList = gson.fromJson(json,type);

        if(mExampleList == null){
            mExampleList = new ArrayList<>();
        }
    }

    public void insertItem(int position) {

        String nombre = nombre_med.getText().toString();
        String descrip = descrip_med.getText().toString();

        mExampleList.add(position, new example_item(R.drawable.ic_medicine,  "Id: "+ position +" "+ nombre  , descrip));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        // mExampleList.add(new example_item(R.drawable.ic_medicine, "Medicamento 1", "Descripcion 1"));
        // mExampleList.add(new example_item(R.drawable.ic_medicine, "Medicamento 2", "Descripcion 2"));
        // mExampleList.add(new example_item(R.drawable.ic_medicine, "Medicamento 3", "Descripcion 3"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int posicion) {
        Toast.makeText(this,"Medicamento seleccionado",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Med.this, visual.class);
        startActivity(intent);
    }

    public void onDeleteClick(int posicion) {
        removeItem(posicion);
    }

}
