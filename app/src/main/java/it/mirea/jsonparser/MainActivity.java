package it.mirea.jsonparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    String url = "https://api.rasp.yandex.net/v3.0/schedule/?apikey=18bacc75-c40b-4510-a42e-63efd9720bc8&format=json&station=s9600370&transport_types=plane&event=departure&lang=ru_RU&page=1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = url + "&date=2022-05-23";
        System.out.println(url);
        try {
            razbor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new jsonTask().execute();


    }
    class jsonTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL myurl = new URL(url);
                HttpsURLConnection urlConnection = (HttpsURLConnection) myurl.openConnection();
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line=reader.readLine()) !=null){
                    builder.append(line);


                }
                System.out.println(builder);
                Log.e("Json",builder.toString());
                JSONObject jsonObject = new JSONObject(String.valueOf(builder));
                System.out.println(jsonObject.get("schedule"));



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    public void razbor() throws Exception{
        ObjectMapper om = new ObjectMapper();
        Info info = new Info();
        Info info1 = om.readValue(url, Info.class);
        System.out.println(info1);


    }

}