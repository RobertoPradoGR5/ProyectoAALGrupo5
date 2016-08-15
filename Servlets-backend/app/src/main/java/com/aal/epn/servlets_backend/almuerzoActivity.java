package com.aal.epn.servlets_backend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import it.sephiroth.android.library.picasso.Picasso;


public class almuerzoActivity extends AppCompatActivity {
    public ListView listaAlmuerzos;
    public ImageView iv;
    public final Context context=this;
    public String url;
    public Button btnBeneficios;
    public String tipo="";
    public TextView tipoTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almuerzo);
        Fecha hoy=new Fecha();
        String dia="";
        hoy.Today();
        dia=hoy.getDia();
        Toast.makeText(context, "Hoy es: "+dia, Toast.LENGTH_SHORT).show();
        tipo = getIntent().getExtras().getSerializable("tipo").toString();
        tipoTxt=(TextView)findViewById(R.id.txtTipo);
        Toast.makeText(context, tipo+"", Toast.LENGTH_LONG).show();
        listaAlmuerzos=(ListView)findViewById(R.id.listViewAlmuerzos);
        iv=(ImageView)findViewById(R.id.iv);
        tipoTxt.setText(tipo+"");
        new ServletGetAsyncTask().execute(new Pair<Context, String>(context, dia));

        btnBeneficios=(Button)findViewById(R.id.btnBeneficios);
        btnBeneficios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrir=new Intent(almuerzoActivity.this,beneficioActivity.class);
                abrir.putExtra("tipo",tipo);
                startActivity(abrir);
            }
        });
    }

    class ServletGetAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private Context context;
        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            context = params[0].first;
            String dia = params[0].second;
            try {
                dia = URLEncoder.encode(dia, "UTF-8");
            }
            catch (UnsupportedEncodingException e){

            }
            try {
                // Set up the request
                URL url = new URL("http://health-admin.appspot.com/hello?action=almuerzo&diaNombre="+dia+"&tipo="+tipo);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                //connection.setDoInput(true);
                //connection.setDoOutput(true);
                connection.connect();
                // Read response
                int responseCode = connection.getResponseCode();
                StringBuilder response = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    connection.disconnect();
                    return response.toString();

                }
                connection.disconnect();
                return "Error: " + responseCode + " " + connection.getResponseMessage();

            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(context, "Cargando almuerzo...", Toast.LENGTH_LONG).show();
            System.out.println(">>>>>RESULT"+result);
            try {
                showData(result);
                //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Error "+e);
                Toast.makeText(context, "Ocurrió algo  | "+e, Toast.LENGTH_SHORT).show();
            }
        }
        private void showData(String jsonStr) throws JSONException {
            JSONArray jsonarray = new JSONArray("["+jsonStr+"]");
            ListAdapter adapter = new JSONArrayAdapter(context,jsonarray,R.layout.grid_item_almuerzo,new String[] {"principal", "secundario","postre","bebida","precio"},new int[] { R.id.item1, R.id.item2 ,R.id.item3,R.id.item4,R.id.item5});
            listaAlmuerzos.setAdapter(adapter);
            for (int i = 0; i < jsonarray.length(); ++i) {
                JSONObject rec = jsonarray.getJSONObject(i);
                url = rec.getString("urlImagen");
            }
            Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .resize(350, 230).into(iv);
        }
    }
}

