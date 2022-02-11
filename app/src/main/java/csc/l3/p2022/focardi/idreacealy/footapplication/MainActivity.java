package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import csc.l3.p2022.focardi.idreacealy.footapplication.databinding.ActivityMainBinding;
import csc.l3.p2022.focardi.idreacealy.footapplication.model.Country;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * TextView pour permettre l'affichage du JSON non parsé ( pour l'instant)
     */
    TextView myTvCountry;

    /**
     * Classe d'énumération pour le type d'action sur l'URL de l'API
     */
    ActionAPI actionAPI;

    /**
     * Afficher les pays
     */
    Button myBtnCountries;

    /**
     * Afficher les ligues
     */
    Button myBtnCompetitions;

    /**
     * TODO A METTRE EN ENV
     */
    String key = "84423f0edc1fe46d1e0ffb5a18ba794e98b6b6bbe54f6ab03614f3643489cd98";

    /**
     * Actvité principal de l'application
     */
    Activity act = MainActivity.this;

    ImageView myImgCountry;

    Api api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_idrice);

        // Permettre le non crash au moment du lacement de l'application
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myTvCountry = (TextView) findViewById(R.id.tv01);
        myBtnCountries = (Button) findViewById(R.id.btnCountries);
        myBtnCompetitions = (Button) findViewById(R.id.btnCompetitions);

        String jsonResponse = getJSON(actionAPI.GET_COUNTRIES);
        List<Country> lstCountry = new ArrayList<Country>();

        try {
            JSONArray myCountries = new JSONArray(jsonResponse);
            JSONObject jb = myCountries.getJSONObject(0);

            for(int i =0; i < myCountries.length(); i++){
                JSONObject coutryJSON = myCountries.getJSONObject(i);
                Country c = new Country(coutryJSON.get("country_id").toString(),coutryJSON.get("country_name").toString(),coutryJSON.get("country_logo").toString());
                lstCountry.add(c);
            }

            //myTvApi.setText(jb.getString("country_id"));
                /*myCountries.keys().forEachRemaining(key ->{
                    Object value = null;
                    try {
                        value = myCountries.get(key);
                        System.out.println("Key = "+key+" value = "+value);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });*/
        } catch (JSONException e) {
            e.printStackTrace();
        }


        myBtnCountries.setOnClickListener(v -> {
            myTvCountry.setText(lstCountry.get(0).getCountry_name());
        });

        myBtnCompetitions.setOnClickListener( v-> {
            myTvCountry.setText(getJSON(actionAPI.GET_COMPETITION));
        });

    }

    private String getJSON(ActionAPI a){
        api = new Api(key, a);
        api.run();
        return api.getsJson();
    }

}