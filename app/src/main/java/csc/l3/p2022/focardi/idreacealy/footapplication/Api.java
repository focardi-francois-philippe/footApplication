package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.app.Activity;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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
     * Activité sur laquelle l'API va intéragir
     */
    private Activity act;

    private String sJson;

    private TextView tv;
    private JSONObject jo;

    /**
     *
     * @param ka Clé de l'API
     * @param a Type d'action de notre requête

     */

    public Api(String ka, ActionAPI a){
        this.keyAPI = ka;
        this.action = a;
        //this.act = act;
        //this.tv = tv;
        this.urlApi = String.format("https://apiv3.apifootball.com/?action=%s&APIkey=%s",a.getAction(),ka);
    }

    @Override
    public void run() {
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
    }

    public JSONObject getJo() {
        return jo;
    }

    private void setJo(JSONObject jo) {
        this.jo = jo;
    }

    public void setsJson(String sJson) {
        this.sJson = sJson;
    }

    public String getsJson() {
        return sJson;
    }

}
