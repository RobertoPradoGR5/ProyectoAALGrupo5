package com.aal.epn.servlets_backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by RobertoPrado on 09/08/2016.
 */
public class AlmuezoAdapter extends ArrayAdapter<Almuerzo> {
    ArrayList<Almuerzo> almuerzosList;
    LayoutInflater vi;
    int Resource;

    ViewHolder holder;
    public AlmuezoAdapter(Context context, int resource, ArrayList<Almuerzo> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        almuerzosList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.item0);
            holder.tipo= (TextView) v.findViewById(R.id.item1);
            holder.principal= (TextView) v.findViewById(R.id.item2);
            holder.secundario = (TextView) v.findViewById(R.id.item3);
            holder.bebida = (TextView) v.findViewById(R.id.item4);
            holder.postre= (TextView) v.findViewById(R.id.item5);
            holder.precio = (TextView) v.findViewById(R.id.item6);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(super.getContext()).load(almuerzosList.get(position).getUrlImagen()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(200, 130).into(holder.imageview);
        //new DownloadImageTask(holder.imageview).execute(beneficioList.get(position).getUrlImagen());
        holder.tipo.setText(almuerzosList.get(position).getTipo());
        holder.principal.setText(almuerzosList.get(position).getPricipal());
        holder.secundario.setText(almuerzosList.get(position).getSecundario());
        holder.bebida.setText(almuerzosList.get(position).getBebida());
        holder.postre.setText(almuerzosList.get(position).getPostre());
        holder.precio.setText(almuerzosList.get(position).getPrecio().toString());
        return v;

    }
    static class ViewHolder {
        public ImageView imageview;
        public TextView principal;
        public TextView secundario;
        public TextView bebida;
        public TextView postre;
        public TextView precio;
        public TextView tipo;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
