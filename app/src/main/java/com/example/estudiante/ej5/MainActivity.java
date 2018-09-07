package com.example.estudiante.ej5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ListView lvNoticias;
    NoticiaAdapter customAdapter;
    EditText etNombre;
    EditText etCelular;
    Button btnGenerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.et_nombre);
        etCelular = findViewById(R.id.et_celular);
        btnGenerar = findViewById(R.id.btn_generar);

        lvNoticias = findViewById(R.id.lv_noticias);
        customAdapter = new NoticiaAdapter(this);
        lvNoticias.setAdapter(customAdapter);

       // Noticias noticia1 = new Noticias("Hoy es diseño", "Va a haber un cambio en el logo", "30/08/2018");
       // Noticias noticia2 = new Noticias("Hoy es diseñox2", "Va a haber un cambio en el logox2", "31/08/2018");

        // customAdapter.agregarNoticias(noticia1);
        // customAdapter.agregarNoticias(noticia2);

        lvNoticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Noticias noticiaClic = (Noticias) customAdapter.getItem(position);
                Toast.makeText(MainActivity.this, noticiaClic.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Traernos la fecha actual
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                month++;
                int day = c.get(Calendar.DAY_OF_MONTH);
                String fecha = day+"/"+month+"/"+year;

                String nombre = etNombre.getText().toString();
                String celular = etCelular.getText().toString();
                Noticias newNoticia = new Noticias(nombre, celular, fecha);
                customAdapter.agregarNoticias(newNoticia);
            }
        });
    }
}
