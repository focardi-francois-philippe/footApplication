package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CompetitionActivity extends AppCompatActivity {

    ListView listView ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competition_layout);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int identifiantPays = intent.getIntExtra("PaysId",-5);
        int identifiantChampionnat = intent.getIntExtra("CompetitionId",-6);
        listView = (ListView)findViewById(R.id.listViewMatchs);
        List<Matchs> matchDetails = getMatchsData();
        listView.setAdapter(new CustomListAdapterMatchs(CompetitionActivity.this, matchDetails));
    }
    private List<Matchs> getMatchsData() {
        List<Matchs> list = new ArrayList<Matchs>();
        Matchs vietnam = new Matchs("Francois", "Test","Ajaccio","Test","0","0","-1");
        Matchs usa = new Matchs("Aaaa", "Test","Bastia","Test","0","1","-2");
        Matchs russia = new Matchs("BBBBBB", "Test","Ajaccio","Test","0","0","3");

        list.add(vietnam);
        list.add(usa);
        list.add(russia);
        list.add(vietnam);
        list.add(usa);
        list.add(russia);
        list.add(vietnam);
        list.add(usa);
        list.add(russia);


        return list;
    }
}
