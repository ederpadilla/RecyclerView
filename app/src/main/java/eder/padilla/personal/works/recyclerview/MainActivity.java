package eder.padilla.personal.works.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainActivity extends ActionBarActivity {

    private RecyclerView recView;
    private Button btnInsertar;
    private Button btnEliminar;
    private Button btnMover;
    private EditText etDireccion;
    private EditText etNombre;
    private EditText etHora;
    private EditText etTipo;
     int f = -1;

    private ArrayList<Visita> datos;
    AdaptadorTitulares adaptador;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);
        recView.setItemAnimator(new SlideInLeftAnimator());
        recView.getItemAnimator().setAddDuration(800);
        recView.getItemAnimator().setRemoveDuration(800);
        etDireccion=(EditText)findViewById(R.id.direccion);
        etNombre=(EditText)findViewById(R.id.nombre);
        etHora=(EditText)findViewById(R.id.hora);
        etTipo=(EditText)findViewById(R.id.tipo);
        datos = new ArrayList<Visita>();
        for(int i=0; i<24; i++)
            datos.add(new Visita("" , "","",0 ));

        adaptador = new AdaptadorTitulares(datos,this.getApplicationContext());
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //recView.setLayoutManager(new GridLayoutManager(this,3));

        recView.addItemDecoration(
                new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        recView.setItemAnimator(new DefaultItemAnimator());

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recView.getChildAdapterPosition(v));
                Visita visita = datos.get(recView.getChildAdapterPosition(v));
                if(visita.getTipo().equals("programada")){
                    Intent intent = new Intent(MainActivity.this,
                            ActividadYeah.class);intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                }
            }
        });





        btnInsertar = (Button)findViewById(R.id.BtnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    f = Integer.parseInt(etHora.getText().toString());
                Visita titular =  new Visita(etNombre.getText().toString(),etDireccion.getText().toString(),
                        etTipo.getText().toString().toLowerCase().trim().replaceAll("\\s+",""),f);


                datos.set(f, titular);
                adaptador.notifyDataSetChanged();
                recView.getItemAnimator().setChangeDuration(3000);
            }
        });

        btnEliminar = (Button)findViewById(R.id.BtnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.remove(0);
                adaptador.notifyItemRemoved(0);
            }
        });

        btnMover = (Button)findViewById(R.id.BtnMover);

        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Visita aux = datos.get(1);
                datos.set(1,datos.get(2));
                datos.set(2,aux);

                adaptador.notifyItemMoved(1, 2);
            }
        });
    }






}







