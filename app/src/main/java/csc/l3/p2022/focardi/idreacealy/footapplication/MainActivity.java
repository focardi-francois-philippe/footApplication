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
import java.util.HashSet;
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
        String jsonGET_COMPETITION = getJSON(actionAPI.GET_EVENTS);

        /**
         * String qui va contenir les compétitions non parsé
         */
        String jsonGET_EVENTS_LEAGUE = getJSON(actionAPI.GET_EVENTS_LEAGUE, "302");

        /**
         * Liste qui va contenir l'ensemble de nos pays
         */
        List<Country> lstCountry = new ArrayList<Country>();

        /**
         * Liste qui va contenir l'ensemble des compétitions
         */
        HashSet<Competition> lstCompetitionDay = new HashSet<Competition>();

        /**
         * Liste qui va contenir l'ensemble des matchs du jour
         */
        List<Event> lstEventsLeague = new ArrayList<Event>();

        try {
            JSONArray myCountries = new JSONArray(jsonGET_COUNTRIES);
            JSONArray myCompetition = new JSONArray(jsonGET_COMPETITION);
            JSONArray myEventsLeague = new JSONArray(jsonGET_EVENTS_LEAGUE);

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
                lstCompetitionDay.add(cpt);
            }

            /**
             * Remplissage de liste des events compet
             */
            for(int i=0; i<myEventsLeague.length(); i++){
                JSONObject eventsLeagueJSON = myEventsLeague.getJSONObject(i);
                Event event = new Event(eventsLeagueJSON.get("match_id").toString(),eventsLeagueJSON.get("match_hometeam_name").toString(),eventsLeagueJSON.get("match_awayteam_name").toString());
                lstEventsLeague.add(event);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        myBtnCountries.setOnClickListener(v -> {
            myTvCountry.setText(lstCountry.get(0).getCountry_name());
        });

        myBtnCompetitions.setOnClickListener( v-> {
            Competition[] comp = lstCompetitionDay.toArray(new Competition[lstCompetitionDay.size()]);
            myTvCountry.setText(comp[0].getLeague_name()+" "+lstCompetitionDay.size());
        });

        myBtnEvent.setOnClickListener(v -> {
            myTvCountry.setText(String.format("id: %s, home : %s, away : %s ", lstEventsLeague.get(0).getMatch_id(), lstEventsLeague.get(0).getHometeam(),lstEventsLeague.get(0).getAwayteam()));
        });

    }

    /**
     * Récpérer le JSON non parsé selon l'action sur l'URL de l'API
     * @param a action sur l'url
     * @return données JSON non parsé
     */
    private String getJSON(ActionAPI a){
        api = new Api(key, a, MainActivity.this);
        api.run();
        return api.getsJson();
    }

    /**
     * Récupérer le JSON des évenements du jour
     * non parsé selon l'action et l'ID de la league.
     * @param a action sur l'url
     * @param id id league
     * @return données json des matchs du jour non parsé
     */
    private String getJSON(ActionAPI a, String id){
        api = new Api(key, a, MainActivity.this, id);
        api.run();
        return api.getsJson();
    }

}