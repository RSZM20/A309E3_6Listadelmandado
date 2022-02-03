package com.example.a309e3_6listadelmandado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    

    // Referencias a ListView y al adaptador

    private ListView mListado;
    private ArrayAdapter<String> arrayAdapter;

    // b1- Referencia al TextView

    private TextView mTexto;

    // Referencia al array

    private ArrayList<String> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se instancian las vistas

        mListado= findViewById(R.id.listado);

        //b2

        mTexto = findViewById(R.id.texto);
        Typeface face = Typeface.createFromAsset(getAssets(), "fuentes/Hello_Giorgina.ttf");
        mTexto.setTypeface(face);

        // Cargamos los datos al Array

        cargarDatos();

        // Creamos el adaptador

        arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productos);

        // Se asocia el adaptador al ListView

        mListado.setAdapter(arrayAdapter);

        // b4 Se crea y se registra un objeto Listener

        mListado.setOnItemClickListener(new ProcesaElementoSeleccionado());

        //Ponemos el icono en el ActionBar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher1);

    }

    private void cargarDatos() {

        // Se instancia el arreglo

        productos= new ArrayList<>();

        // Se carga con datos

        productos.add("Manzanas");
        productos.add("Jamón");
        productos.add("Atún");
        productos.add("Azúcar");
        productos.add("Gelatina");
        productos.add("Huevos");
        productos.add("Chocolate");
        productos.add("Leche");
        productos.add("Tomate");
        productos.add("Papa");
        productos.add("Yogurth");
        productos.add("Fideos");
        productos.add("Galletas");
        productos.add("Helado");
        productos.add("Carne");
        productos.add("Frijoles");
        productos.add("Arroz");
        productos.add("Zanahoria");
        productos.add("Flan");
        productos.add("Mermelada");
        productos.add("Naranjas"); //realizando cambios,  saludos RSZM20 -jasx22

    }

    // Se crea una clase que implementa a OnItemClickListener

    public class ProcesaElementoSeleccionado implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mTexto.setText(productos.get(position));
            //mTexto.setText(mListado.getItemAtPosition(position).toString());
        }
    }

    //C1 Inflado del menú

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // C2- Manejo de las opciones del menú


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.op1:
                arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, productos);
                mListado.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                break;

            case R.id.op2:
                arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, productos);
                mListado.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                break;

            case R.id.op3:
                arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, productos);
                mListado.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                break;

            case R.id.op4:
                arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, productos);
                mListado.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                break;

            case R.id.op5:
                arrayAdapter= new ArrayAdapter<>(this, R.layout.mi_layout, productos);
                mListado.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                break;

            case R.id.salir:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Confirmación");
                dialog.setMessage("Confirme si realmente desea salir");
                dialog.setCancelable(false);
                dialog.setIcon(R.drawable.ic_salir);
                dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                break;

        }

        mListado.setAdapter(arrayAdapter);
        return super.onOptionsItemSelected(item);
    }

    // d1 Se procesan los elementos que han sido seleccionados

    public void procesarSeleccionados(View view) {
        SparseBooleanArray seal = mListado.getCheckedItemPositions();
        if (seal==null){
            Toast.makeText(this, "Este LayOut No permite selección", Toast.LENGTH_SHORT).show();
        }
        else {

        // Se inicializa la cadena de texto

        StringBuffer cad = new StringBuffer("");
        for (int i = 0; i<seal.size(); i++){
            if (seal.valueAt(i)){
                cad.append(mListado.getItemAtPosition(seal.keyAt(i)));
                cad.append(",");
            }
        }
        mTexto.setText(cad);
        }
    }

}
