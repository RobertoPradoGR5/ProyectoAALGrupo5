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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by RobertoPrado on 08/08/2016.
 */

public class BeneficioAdapter extends ArrayAdapter<Beneficio> {
    ArrayList<Beneficio> beneficioList;
    LayoutInflater vi;
    int Resource;

    ViewHolder holder;
    public BeneficioAdapter(Context context, int resource, ArrayList<Beneficio> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        beneficioList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.item1);
            holder.nombre = (TextView) v.findViewById(R.id.item2);
            holder.categoria = (TextView) v.findViewById(R.id.item3);
            holder.descripcion = (TextView) v.findViewById(R.id.item4);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(super.getContext()).load(beneficioList.get(position).getUrlImagen()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(250, 170).into(holder.imageview);
        //new DownloadImageTask(holder.imageview).execute(beneficioList.get(position).getUrlImagen());
        holder.nombre.setText(beneficioList.get(position).getNombre());
        holder.categoria.setText(beneficioList.get(position).getCategoria());
        holder.descripcion.setText(beneficioList.get(position).getDescripcion());
        return v;

    }
    static class ViewHolder {
        public ImageView imageview;
        public TextView nombre;
        public TextView categoria;
        public TextView descripcion;
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
