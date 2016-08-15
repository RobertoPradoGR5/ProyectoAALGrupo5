package com.aal.epn.servlets_backend;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
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
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class almuerzosActivity extends AppCompatActivity {
    TextView diaText;
    public ListView listaAlmuerzos;
    public final Context context=this;
    ArrayList<Almuerzo> almuerzosList;
    AlmuezoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almuerzos);

        almuerzosList=new ArrayList<>();
        listaAlmuerzos=(ListView)findViewById(R.id.listViewAlmuerzosDia);
        diaText=(TextView)findViewById(R.id.txtDia);
        String dia = getIntent().getExtras().getSerializable("dia").toString();
        diaText.setText(dia+"");
        //Toast.makeText(context, "Dia "+dia, Toast.LENGTH_SHORT).show();
        new ServletGetAsyncTask().execute(new Pair<Context, String>(context, dia));

    }
    class ServletGetAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private Context context;
        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            context = params[0].first;
            String dia = params[0].second;
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>DIA: "+dia);
            try {
                dia = URLEncoder.encode(dia, "UTF-8");
            }
            catch (UnsupportedEncodingException e){

            }
            try {
                // Set up the request
                URL url = new URL("http://health-admin.appspot.com/hello?action=almuerzosDia&diaNombre="+dia);
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
            //Toast.makeText(context, "Cargando almuerzo..."+result, Toast.LENGTH_LONG).show();
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
                Almuerzo almuerzo = new Almuerzo();
                almuerzo.setPricipal(object.getString("principal"));
                almuerzo.setSecundario(object.getString("secundario"));
                almuerzo.setBebida(object.getString("bebida"));
                almuerzo.setPostre(object.getString("postre"));
                almuerzo.setPrecio(Float.parseFloat(object.getString("precio")));
                almuerzo.setUrlImagen(object.getString("urlImagen"));
                almuerzo.setTipo(object.getString("tipo"));

                //Toast.makeText(context, "Agregando"+beneficio.getNombre(), Toast.LENGTH_SHORT).show();
                almuerzosList.add(almuerzo);
            }
            ListView listview = (ListView)findViewById(R.id.listViewAlmuerzosDia);
            adapter = new AlmuezoAdapter(context, R.layout.grid_item_almuerzos,almuerzosList);
            listview.setAdapter(adapter);
        }
    }
}
