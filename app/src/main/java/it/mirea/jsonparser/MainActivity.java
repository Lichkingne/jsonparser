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
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private Thread secThread;//нельзя запускать трудоемкий поток, надо его отдельно)
    private Thread theThread;

    private Runnable runnable;
    String key = "18bacc75-c40b-4510-a42e-63efd9720bc8";
    String note = "&format=json&station=s9600370&transport_types=plane&";
    String eventDeparture = "event=departure&lang=ru_RU";
    String evetArrival = "event=arrival&lang=ru_RU";
    String url = "https://api.rasp.yandex.net/v3.0/schedule/?apikey=";
    String days = "&date=";
    String urlForDeparture ="";
    String urlForArrival = "";
    String date = "";
    String Check = "";

    //https://api.rasp.yandex.net/v3.0/schedule/?apikey=18bacc75-c40b-4510-a42e-63efd9720bc8&format=json&station=s9600370&transport_types=plane&event=departure&lang=ru_RU&transfers=true&date=2022-05-27
    //https://api.rasp.yandex.net/v3.0/schedule/?apikey=18bacc75-c40b-4510-a42e-63efd9720bc8&format=json&station=s9600370&transport_types=plane&event=departure&lang=ru_RU&date=2022-04-27
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init();

        init1();

//        url = url + "&date=2022-05-23";
//        System.out.println(url);
//        try {
//            razbor();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new jsonTask().execute();


    }
//    private void init() {
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                setDate();
//
//
//
//            }
//        };
//        secThread = new Thread(runnable);
//        secThread.start();
//    }
    private void init1() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();


            }
        };
        theThread = new Thread(runnable);
        theThread.start();
    }


//    public void setDate() {
//        // получаем текущее время
//        Calendar c = Calendar.getInstance();
//        int month = c.get(Calendar.MONTH);
//        int yaer = c.get(Calendar.YEAR);
//        int day = c.get(Calendar.DATE);
//        int hours = c.get(Calendar.HOUR_OF_DAY);
//        int minutes = c.get(Calendar.MINUTE);
//        int seconds = c.get(Calendar.SECOND);
//
//        if(month<10) {
//            date = yaer + "-" + "0" + month + "-" + day;
//        }else {
//            date = yaer + "-"  + month + "-" + day;
//        }
//        System.out.println(date);
//    }
//    private void dateForm(){
//        urlForDeparture=url+key+note+eventDeparture+days+date;
//        urlForArrival=url+key+note+evetArrival+days+date;
//        System.out.println(urlForDeparture);System.out.println(urlForArrival);
//    }
    private void getWeb(){
        try {
            Calendar c = Calendar.getInstance();
            int month = c.get(Calendar.MONTH);
            int yaer = c.get(Calendar.YEAR);
            int day = c.get(Calendar.DATE);


            if(month<10) {
                date = yaer + "-" + "0" + month + "-" + day;
            }else {
                date = yaer + "-"  + month + "-" + day;
            }
            System.out.println(date);
            urlForDeparture=url+key+note+eventDeparture+days+date;
            urlForArrival=url+key+note+evetArrival+days+date;
            System.out.println(urlForDeparture);System.out.println(urlForArrival);
            //url=urlForDeparture;
            URL myurl = new URL(urlForDeparture);
            HttpsURLConnection urlConnection = (HttpsURLConnection) myurl.openConnection();//открываем поток на сайт
            InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuffer buff = new StringBuffer();
            String line;
            while ((line=reader.readLine()) !=null) {
                  buff.append(line);
              }reader.close();
            System.out.println(buff);
            JSONObject object_JSONObject = new JSONObject(buff.toString());
            JSONArray array_JSONArray = object_JSONObject.getJSONArray("schedule");
            System.out.println(array_JSONArray.length());
            JSONObject object_JSONObject1 = array_JSONArray.getJSONObject(0);
            JSONObject object_JSONObject2 = object_JSONObject1.getJSONObject("thread");
            //JSONObject object_JSONObject3 = object_JSONObject2.getJSONObject("short_title");
            System.out.println(object_JSONObject2.getString("short_title"));




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
//    class jsonTask extends AsyncTask<Void, Void, String>{
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//
//        }
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                URL myurl = new URL(url);
//                HttpsURLConnection urlConnection = (HttpsURLConnection) myurl.openConnection();
//                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
//                BufferedReader reader = new BufferedReader(streamReader);
//                StringBuilder builder = new StringBuilder();
//                String line;
//                while ((line=reader.readLine()) !=null){
//                    builder.append(line);
//
//
//                }
//                System.out.println(builder);
//                Log.e("Json",builder.toString());
//                JSONObject jsonObject = new JSONObject(String.valueOf(builder));
//                System.out.println(jsonObject.get("schedule"));
//
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }


}