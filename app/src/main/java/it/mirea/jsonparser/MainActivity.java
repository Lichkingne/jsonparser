package it.mirea.jsonparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Exchanger;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    public BottomNavigationView bottomNavigationView;
    static ArrayList<AirportFlightsArrival> departuresList = new ArrayList<>();
    public String timearr;
    private Thread secThread;//нельзя запускать трудоемкий поток, надо его отдельно)
    private ArrayList<String> tablo = new ArrayList<>();
    private ArrayList<String> tabloArr = new ArrayList<>();
    private Thread theThread;
    public ArrayList<AirportFlights> pol = new ArrayList<>();
    public ArrayList<AirportFlightsArrival> polArr = new ArrayList<>();
    private Runnable runnable;
    String key = "18bacc75-c40b-4510-a42e-63efd9720bc8";
    String note = "&format=json&station=s9600370&transport_types=plane&";
    String eventDeparture = "event=departure&lang=ru_RU";
    String eventArrival = "event=arrival&lang=ru_RU";
    String url = "https://api.rasp.yandex.net/v3.0/schedule/?apikey=";
    String days = "&date=";
    String urlForDeparture ="";
    String urlForArrival = "";
    String date = "";
    String Check = "";
    int counterArr;
    //https://api.rasp.yandex.net/v3.0/schedule/?apikey=18bacc75-c40b-4510-a42e-63efd9720bc8&format=json&station=s9600370&transport_types=plane&event=departure&lang=ru_RU&transfers=true&date=2022-05-27
    //https://api.rasp.yandex.net/v3.0/schedule/?apikey=18bacc75-c40b-4510-a42e-63efd9720bc8&format=json&station=s9600370&transport_types=plane&event=departure&lang=ru_RU&date=2022-04-27
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TextView []tv = new TextView[4];
//                tv[0] = (TextView) findViewById(R.id.city);
//                tv[1] = (TextView) findViewById(R.id.idplane);
//                tv[2] = (TextView) findViewById(R.id.timeflight);
//                tv[3] = (TextView) findViewById(R.id.status);

        init();
        init1();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String K = "";

        for (int i = 0 ; i<counterArr;i++)
        {
            AirportFlightsArrival buf = new AirportFlightsArrival("", "", "", "");
            departuresList.add(buf);
            timearr = polArr.get(i).cityArr;
            departuresList.get(i).setCity(timearr);
            System.out.println(departuresList.get(i).cityArr);
            timearr = polArr.get(i).timeArr;
            departuresList.get(i).setTime(timearr);
            timearr = polArr.get(i).planeidArr;
            departuresList.get(i).setPlaneid(timearr);
            timearr = polArr.get(i).statusArr;
            departuresList.get(i).setStatus(timearr);
       }

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navBottomMenu);
        //replaceFragment(new DeparturesFragment());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.departures:
                    replaceFragment(new DeparturesFragment());
                    break;
                case R.id.arrivals:
                    Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.transfers:
                    Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent2);
                    break;
            }
            return true;
        });




        }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameFragment, fragment);
        ft.commit();
    }
    private void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWebArr();

              //  fk.s00etText("f");
//                TextView []tv = new TextView[4];
//                tv[0] = (TextView) findViewById(R.id.city);
//                tv[1] = (TextView) findViewById(R.id.idplane);
//                tv[2] = (TextView) findViewById(R.id.timeflight);
//                tv[3] = (TextView) findViewById(R.id.status);
//                tv[0].setText("fff");



            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
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
    private void getWebArr(){
        try {
            Calendar c = Calendar.getInstance();
            int month = c.get(Calendar.MONTH) + 1;
            int yaer = c.get(Calendar.YEAR);
            int day = c.get(Calendar.DATE);
            int hour = c.get(Calendar.HOUR);
            int minute = c.get(Calendar.MINUTE);
            if (month < 10) {
                date = yaer + "-" + "0" + month + "-" + day;
            } else {
                date = yaer + "-" + month + "-" + day;
            }
            System.out.println(date);
            urlForArrival = url + key + note + eventArrival + days + date;
            //TextView city = findViewById(R.id.city);

            System.out.println(urlForArrival);
            //url=urlForDeparture;
            URL myurl = new URL(urlForArrival);
            HttpsURLConnection urlConnection = (HttpsURLConnection) myurl.openConnection();//открываем поток на сайт
            InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuffer buff = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buff.append(line);
            }
            reader.close();
            System.out.println(buff);
            String txt = buff.toString();
            JSONObject object_JSONObject = new JSONObject(buff.toString());
            JSONArray array_JSONArray = object_JSONObject.getJSONArray("schedule");
            int counter = array_JSONArray.length();
            counterArr = counter;
            for (int i = 0; i < counter; i++) {
                tabloArr.add(txt);
                AirportFlightsArrival buf = new AirportFlightsArrival("", "", "", "");
                polArr.add(buf);
                JSONObject object_JSONObject1 = array_JSONArray.getJSONObject(i);
                JSONObject object_JSONObject2 = object_JSONObject1.getJSONObject("thread");
                polArr.get(i).setCity(object_JSONObject2.getString("short_title"));
                polArr.get(i).setPlaneid(object_JSONObject2.getString("number"));
                String str = object_JSONObject1.getString("arrival");
                String[] wert = str.split("T");
                String p = wert[1];
                String[] op = p.split("\\+");
                polArr.get(i).setTime(op[0]);
                String u = op[0];
                String[] y = u.split(":");
                String s = y[0];
                String m = y[1];
                int t = Integer.parseInt(s);
                int r = Integer.parseInt(m);
                if (t <= hour & r <= minute) {polArr.get(i).setStatus("прилетел");
                } else {polArr.get(i).setStatus("прилетит по расписанию");
                }
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getWeb(){
        try {
            Calendar c = Calendar.getInstance();
            int month = c.get(Calendar.MONTH)+1;
            int yaer = c.get(Calendar.YEAR);
            int day = c.get(Calendar.DATE);
            int hour = c.get(Calendar.HOUR);
            int minute = c.get(Calendar.MINUTE);


            if(month<10) {
                date = yaer + "-" + "0" + month + "-" + day;
            }else {
                date = yaer + "-"  + month + "-" + day;
            }
            System.out.println(date);
            urlForDeparture=url+key+note+eventDeparture+days+date;

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
            String  txt = buff.toString();
            JSONObject object_JSONObject = new JSONObject(buff.toString());
            JSONArray array_JSONArray = object_JSONObject.getJSONArray("schedule");
            int counter = array_JSONArray.length();

            for(int i = 0; i< counter;i++){
                tablo.add(txt);
                AirportFlights buf = new AirportFlights("", "", "", "");
                pol.add(buf);
                JSONObject object_JSONObject1 = array_JSONArray.getJSONObject(i);
                JSONObject object_JSONObject2 = object_JSONObject1.getJSONObject("thread");
                pol.get(i).setCity(object_JSONObject2.getString("short_title"));
                pol.get(i).setPlaneid(object_JSONObject2.getString("number"));
                String str = object_JSONObject1.getString("departure");

                String [] wert = str.split("T");
                String p = wert[1];
                String[] op = p.split("\\+");

                pol.get(i).setTime(op[0]);
                String u = op[0];
                String[] y = u.split(":");
                String s = y[0];
                String m = y[1];
                int t = Integer.parseInt(s);
                int r = Integer.parseInt(m);
                if(t <= hour & r <= minute){
                    pol.get(i).setStatus("Вылетел");
                }else {
                    pol.get(i).setStatus("вылет по расписанию");
                }


                //System.out.println(pol.get(i));
                            }
//            System.out.println(array_JSONArray.length());
//            JSONObject object_JSONObject1 = array_JSONArray.getJSONObject(0);
//            JSONObject object_JSONObject2 = object_JSONObject1.getJSONObject("thread");
//            //JSONObject object_JSONObject3 = object_JSONObject2.getJSONObject("short_title");
//            System.out.println(object_JSONObject2.getString("short_title"));
//            System.out.println(object_JSONObject2.getString("number"));
//            System.out.println(object_JSONObject1.getString("departure"));




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}