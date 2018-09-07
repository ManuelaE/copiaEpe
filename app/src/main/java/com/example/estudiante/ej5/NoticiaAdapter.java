package com.example.estudiante.ej5;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticiaAdapter extends BaseAdapter {

    ArrayList<Noticias> noticias;
    Activity activity;


    public NoticiaAdapter(Activity activity){
        this.activity = activity;
        noticias = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //Generar un renglón por objeto
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View renglon = inflater.inflate(R.layout.renglon, null, false);
        TextView itemTitulo = renglon.findViewById(R.id.item_titulo);
        TextView itemFecha = renglon.findViewById(R.id.item_fecha);
        TextView itemDescripcion = renglon.findViewById(R.id.item_descripcion);
        Button itemLlamar = renglon.findViewById(R.id.item_llamar);
        Button itemEliminar = renglon.findViewById(R.id.item_eliminar);

        itemTitulo.setText(noticias.get(position).getTitulo());
        itemFecha.setText(noticias.get(position).getFecha());
        itemDescripcion.setText(noticias.get(position).getDescripcion());
        itemEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText( activity, "POS" + position, Toast.LENGTH_SHORT).show();
                 noticias.remove(position);
                 notifyDataSetChanged();

                // Intent intent = new Intent(activity, NoticiaView.class);
                // activity.startActivity(intent);
            }
        });


        itemLlamar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String telefono = noticias.get(position).getDescripcion();
                final int Request_phone_call = 1;
                Intent llamar = new Intent(Intent.ACTION_CALL);
                llamar.setData(Uri.parse("tel: "+telefono));
                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},Request_phone_call);
                    }else {
                        activity.startActivity(llamar);
                    }
                }

            }
        });

        return renglon;
    }

    public void agregarNoticias(Noticias noticia){
        noticias.add(noticia);
        notifyDataSetChanged();
    }
}
