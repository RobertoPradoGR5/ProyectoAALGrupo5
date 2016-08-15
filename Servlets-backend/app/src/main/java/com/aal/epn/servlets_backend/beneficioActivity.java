package com.aal.epn.servlets_backend;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Window;
import android.widget.ListView;
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
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class beneficioActivity extends AppCompatActivity {
    public ListView listaBeneficios;
    public final Context context=this;
    ArrayList<Beneficio> beneficiosList;
    BeneficioAdapter adapter;
    String tipo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios);
        beneficiosList= new ArrayList<Beneficio>();
        listaBeneficios=(ListView) findViewById(R.id.listViewBeneficios);
        Fecha hoy=new Fecha();
        String dia="";
        hoy.Today();
        dia=hoy.getDia();
        tipo=getIntent().getExtras().getSerializable("tipo").toString();
        //Toast.makeText(context, "Tipo..."+tipo, Toast.LENGTH_LONG).show();
        new ServletGetAsyncTask().execute(new Pair<Context, String>(context, dia));
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
                URL url = new URL("http://health-admin.appspot.com/hello?action=beneficiosAlmuerzo&diaNombre="+dia+"&tipo="+tipo);
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
            //Toast.makeText(context, "Cargando beneficios..."+result, Toast.LENGTH_LONG).show();
            try {
                showData(result);
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Error "+e);
                Toast.makeText(context, "Ocurrió algo  | "+e, Toast.LENGTH_SHORT).show();
            }
        }
        private void showData(String jsonStr) throws JSONException {
            JSONArray jsonarray = new JSONArray(jsonStr);

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject object = jsonarray.getJSONObject(i);
                Beneficio beneficio = new Beneficio();
                beneficio.setNombre(object.getString("nombre"));
                beneficio.setDescripcion(object.getString("descripcion"));
                beneficio.setCategoria(object.getString("categoria"));
                beneficio.setUrlImagen(object.getString("urlImagen"));
                //Toast.makeText(context, "Agregando"+beneficio.getNombre(), Toast.LENGTH_SHORT).show();
                beneficiosList.add(beneficio);
            }
            ListView listview = (ListView)findViewById(R.id.listViewBeneficios);
            adapter = new BeneficioAdapter(context, R.layout.grid_item_beneficios, beneficiosList);
            listview.setAdapter(adapter);
        }
    }
}
