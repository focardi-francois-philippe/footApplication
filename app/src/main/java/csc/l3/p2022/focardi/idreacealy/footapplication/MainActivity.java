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
     * Classe d'énumération pour le type d'action sur l'URL de l'API
     */
    ActionAPI actionAPI;

    /**
     * TODO A METTRE EN ENV
     */
    String key = "84423f0edc1fe46d1e0ffb5a18ba794e98b6b6bbe54f6ab03614f3643489cd98";

    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_idrice);

        // Permettre le non crash au moment du lacement de l'application
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /**
         * String qui va contenir tout les pays non parsé
         */
        String jsonGET_COUNTRIES = getJSON(actionAPI.GET_COUNTRIES);

        /**
         * String qui va contenir les compétitions non parsé
         */
        String jsonGET_COMPETITION = getJSON(actionAPI.GET_COMPETITION);

        /**
         * Liste qui va contenir l'ensemble de nos pays
         */
        List<Country> lstCountry = new ArrayList<Country>();


        List<Country> lstCompetition = new ArrayList<Country>();

        /**
         * Remplissage de liste des pays
         */
        try {
            JSONArray myCountries = new JSONArray(jsonGET_COUNTRIES);

            for(int i =0; i < myCountries.length(); i++){
                JSONObject countryJSON = myCountries.getJSONObject(i);
                Country c = new Country(countryJSON.get("country_id").toString(),countryJSON.get("country_name").toString(),countryJSON.get("country_logo").toString());
                lstCountry.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * Remplissage de liste des compétitions
         */
        try {
            JSONArray myCompetition = new JSONArray(jsonGET_COMPETITION);


            for(int i=0; i<myCompetition.length(); i++){
                JSONObject competJSON = myCompetition.getJSONObject(i);
                // Competition cpt = new Compet(...............
                //lstCompetition.add(cpt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String getJSON(ActionAPI a){
        api = new Api(key, a);
        api.run();
        return api.getsJson();
    }

}