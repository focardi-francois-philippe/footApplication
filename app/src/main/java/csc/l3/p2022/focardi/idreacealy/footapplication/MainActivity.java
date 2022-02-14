package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    ActionAPI actionAPI;
    String key = "84423f0edc1fe46d1e0ffb5a18ba794e98b6b6bbe54f6ab03614f3643489cd98";
    Api api;


    BottomNavigationView bottomNavigationView;
    ListView listView ;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        api = new Api(key,ActionAPI.GET_EVENTS);
        Thread t = new Thread(api);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashSet<Competition> image_details = getListData();
        Log.e("HashSet", String.valueOf(image_details.size()));
        ArrayList<Competition> competitions = new ArrayList<>(image_details);
        Log.e("ArrayList", String.valueOf(competitions.size()));
        Collections.sort(competitions);
        listView.setAdapter(new CustomListAdapterCompetition(MainActivity.this,competitions));
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Competition compet = (Competition) o;
                /*

                Toast.makeText(MainActivity.this, "Selected :" + " " + country, Toast.LENGTH_LONG).show();
           */
                Intent competIntent = new Intent(MainActivity.this,CompetitionActivity.class);
                competIntent.putExtra("CompetitionId",compet.getIdChampionnat());
                startActivity(competIntent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private HashSet<Competition> getListData() {

        /*
        HashSet<Competition> lstCompetitionDay = new HashSet<Competition>();
        try {
            JSONArray myCompetition = api.getJsonArray();
            int longueurCompetition =myCompetition.length();
            for(int i=0; i<longueurCompetition; i++){
                JSONObject competJSON = myCompetition.getJSONObject(i);
                Competition cpt = new Competition(
                        competJSON.get("country_name").toString(),
                        competJSON.get("country_logo").toString(),
                        competJSON.get("league_name").toString(),
                        competJSON.get("league_id").toString()
                );
                lstCompetitionDay.add(cpt);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstCompetitionDay;*/
        return api.getLstCompetitionDay();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.onglet_tous_les_matchs)
        {
            setContentView(R.layout.activity_main);
            return true;
        }
        else
        {
            Intent chercherEquipe = new Intent(MainActivity.this,CompetitionActivity.class);
            startActivity(chercherEquipe);
            return true;
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getJSON(ActionAPI a){
        api = new Api(key,a);
        Thread t = new Thread(api);
        t.start();
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
        api = new Api(key, a,id);
        Thread t = new Thread(api);
        t.start();
        return api.getsJson();
    }
}
