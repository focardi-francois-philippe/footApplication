package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.app.Activity;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDate dateEvent;

    private Activity a;

    /**
     *
     * @param ka Clé de l'API
     * @param a Type d'action de notre requête
     */
    public Api(String ka, ActionAPI a, Activity act){
        this.keyAPI = ka;
        this.action = a;
        this.dateEvent = LocalDate.now();
        this.a = act;

        if(a == ActionAPI.GET_EVENTS){
            this.urlApi = String.format("https://apiv3.apifootball.com/?action=%s&from=%s&to=%s&APIkey=%s",a.getAction(),dateEvent.toString(),dateEvent.toString(),ka);
        }else{
            this.urlApi = String.format("https://apiv3.apifootball.com/?action=%s&APIkey=%s",a.getAction(),ka);
        }

    }

    @Override
    public void run() {
        a.runOnUiThread(()->{
            try {
                URL url = new URL(urlApi);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine;

                while((inputLine = in.readLine()) !=  null){
                    setsJson(inputLine);
                }

                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setsJson(String sJson) {
        this.sJson = sJson;
    }

    public String getsJson() {
        return sJson;
    }

}
