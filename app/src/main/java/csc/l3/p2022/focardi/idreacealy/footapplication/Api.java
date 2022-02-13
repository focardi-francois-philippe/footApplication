package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Idricealy MOURTADHOI on 06
 */
public class Api implements Runnable{

    /**
     * Attribut correspondant à la clé de l'API,
     * TODO à mettre en variable d'environnement !!!!
     */
    private String keyAPI;

    /**
     * Attribut correpsond à l'action sur l'URL de l'API
     */
    private ActionAPI action;

    /**
     * Url qui correspond à la requête faite sur l'API
     */
    private String urlApi;

    /**
     * Attribut qui nous permettra de récupérer les données JSON non parsé sous forme de string
     */
    private String sJson;
    /**
     * Date correspondant au events pour une date donnée
     */
    private SimpleDateFormat dateEvent;

    private JSONArray jsonArray;

    /**
     *
     * @param ka Clé de l'API
     * @param a Type d'action de notre requête
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Api(String ka, ActionAPI a){
        this.keyAPI = ka;
        this.action = a;
        this.dateEvent = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateEvent.format(new Date());

        if(a == ActionAPI.GET_EVENTS){
            this.urlApi = String.format("https://apiv3.apifootball.com/?action=%s&from=%s&to=%s&APIkey=%s",a.getAction(),dateStr,dateStr,ka);
        }else{
            this.urlApi = String.format("https://apiv3.apifootball.com/?action=%s&APIkey=%s",a.getAction(),ka);
        }

    }

    public Api(String ka, ActionAPI a,String idLeague){
        this.keyAPI = ka;
        this.action = a;
        this.dateEvent = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateEvent.format(new Date());
        this.urlApi = String.format("https://apiv3.apifootball.com/?action=%s&from=%s&to=%s&league_id=%s&APIkey=%s",a.getAction(),dateStr,dateStr,idLeague,ka);
        System.out.println(urlApi);
    }

    @Override
    public void run() {
        try {
            URL url = new URL(urlApi);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                setsJson(inputLine);
            }
            try {
                jsonArray = new JSONArray(sJson);
                Log.e("JsonArray", String.valueOf(jsonArray));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setsJson(String sJson) {
        this.sJson = sJson;
    }

    public String getsJson() {
        return sJson;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }
}