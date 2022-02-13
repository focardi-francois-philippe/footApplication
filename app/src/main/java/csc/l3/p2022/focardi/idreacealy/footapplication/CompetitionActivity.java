package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompetitionActivity extends AppCompatActivity {

    Api api;
    ListView listView ;
    String key = "84423f0edc1fe46d1e0ffb5a18ba794e98b6b6bbe54f6ab03614f3643489cd98";
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competition_layout);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String identifiantChampionnat = intent.getStringExtra("CompetitionId");
        listView = (ListView)findViewById(R.id.listViewMatchs);

        api = new Api(key,ActionAPI.GET_EVENTS_LEAGUE,identifiantChampionnat);
        Thread t = new Thread(api);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Matchs> matchDetails = getMatchsData();
        listView.setAdapter(new CustomListAdapterMatchs(CompetitionActivity.this, matchDetails));
    }
    private List<Matchs> getMatchsData() {
        List<Matchs> list = new ArrayList<Matchs>();
        try {
            JSONArray Matchs = api.getJsonArray();
            int longueurMatchs =Matchs.length();
            for(int i=0; i<longueurMatchs; i++){
                JSONObject matchsJSONObject = Matchs.getJSONObject(i);
                Matchs matchs = new Matchs(
                        matchsJSONObject.get("match_hometeam_name").toString(),
                        matchsJSONObject.get("team_home_badge").toString(),
                        matchsJSONObject.get("match_awayteam_name").toString(),
                        matchsJSONObject.get("team_away_badge").toString(),
                        matchsJSONObject.get("match_hometeam_score").toString(),
                        matchsJSONObject.get("match_awayteam_score").toString(),
                        matchsJSONObject.get("match_status").toString(),
                        matchsJSONObject.get("match_time").toString(),
                        matchsJSONObject.get("match_id").toString()
                );
                Log.e("Match_Timex",matchs.getTimeMatch());
                list.add(matchs);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }
}
