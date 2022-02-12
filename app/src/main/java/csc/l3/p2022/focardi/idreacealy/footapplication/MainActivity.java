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
import csc.l3.p2022.focardi.idreacealy.footapplication.model.Competition;
import csc.l3.p2022.focardi.idreacealy.footapplication.model.Country;
import csc.l3.p2022.focardi.idreacealy.footapplication.model.Event;

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
     * Classe d'énumération pour le type d'action sur l'URL de l'API
     */
    ActionAPI actionAPI;

    /**
     * TODO A METTRE EN ENV
     */
    String key = "84423f0edc1fe46d1e0ffb5a18ba794e98b6b6bbe54f6ab03614f3643489cd98";

    Api api;

    Button myBtnCountries;
    Button myBtnCompetitions;
    Button myBtnEvent;
    TextView myTvCountry;

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
        myBtnEvent = (Button) findViewById(R.id.btnEvent);

        /**
         * String qui va contenir tout les pays non parsé
         */
        String jsonGET_COUNTRIES = getJSON(actionAPI.GET_COUNTRIES);

        /**
         * String qui va contenir les compétitions non parsé
         */
        String jsonGET_COMPETITION = getJSON(actionAPI.GET_COMPETITION);

        /**
         * String qui va contenir les compétitions non parsé
         */
        String jsonGET_EVENTS = getJSON(actionAPI.GET_EVENTS);

        /**
         * Liste qui va contenir l'ensemble de nos pays
         */
        List<Country> lstCountry = new ArrayList<Country>();

        /**
         * Liste qui va contenir l'ensemble des compétitions
         */
        List<Competition> lstCompetition = new ArrayList<Competition>();

        /**
         * Liste qui va contenir l'ensemble des matchs du jour
         */
        List<Event> lstEvents = new ArrayList<Event>();


        try {
            JSONArray myCountries;
            myCountries = new JSONArray(jsonGET_COUNTRIES);
            JSONArray myCompetition = new JSONArray(jsonGET_COMPETITION);
            JSONArray myEvents = new JSONArray(jsonGET_EVENTS);

            /**
             * Remplissage de liste des pays
             */
            for(int i =0; i < myCountries.length(); i++){
                JSONObject countryJSON = myCountries.getJSONObject(i);
                Country c = new Country(countryJSON.get("country_id").toString(),countryJSON.get("country_name").toString(),countryJSON.get("country_logo").toString());
                lstCountry.add(c);
            }

            /**
             * Remplissage de liste des compétitions
            */
            for(int i=0; i<myCompetition.length(); i++){
                JSONObject competJSON = myCompetition.getJSONObject(i);
                Competition cpt = new Competition(competJSON.get("league_name").toString(),competJSON.get("country_logo").toString());
                lstCompetition.add(cpt);
            }

            /**
             * Remplissage de ma liste des matchs du jour
            */
             for(int i=0; i<myEvents.length(); i++){
                 JSONObject eventJSON = myEvents.getJSONObject(i);
                 Event e = new Event(eventJSON.get("match_id").toString(), eventJSON.get("match_hometeam_name").toString(), eventJSON.get("match_awayteam_name").toString());
                 lstEvents.add(e);
             }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        myBtnCountries.setOnClickListener(v -> {
            myTvCountry.setText(lstCountry.get(0).getCountry_name());
        });

        myBtnCompetitions.setOnClickListener( v-> {
            myTvCountry.setText(lstCompetition.get(0).getLeague_name());
        });

        myBtnEvent.setOnClickListener(v-> {
            myTvCountry.setText(String.format("id: %s, home : %s, ", lstEvents.get(0).getMatch_id(), lstEvents.get(0).getAwayteam(),lstEvents.get(0).getHometeam()));
        });

    }

    private String getJSON(ActionAPI a){
        api = new Api(key, a, MainActivity.this);
        api.run();
        return api.getsJson();
    }

}