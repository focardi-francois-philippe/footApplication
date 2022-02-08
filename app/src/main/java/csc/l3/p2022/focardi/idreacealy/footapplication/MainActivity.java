package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        List<Competition> image_details = getListData();
        listView.setAdapter(new CustomListAdapterCompetition(MainActivity.this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Competition compet = (Competition) o;
                /*

                Toast.makeText(MainActivity.this, "Selected :" + " " + country, Toast.LENGTH_LONG).show();
           */
                Intent competIntent = new Intent(MainActivity.this,CompetitionActivity.class);
                competIntent.putExtra("PaysId",compet.getIdPays());
                competIntent.putExtra("CompetitionId",compet.getIdChampionnat());
                startActivity(competIntent);
            }
        });
    }
    private List<Competition> getListData() {
        List<Competition> list = new ArrayList<Competition>();
        Competition vietnam = new Competition("Allemagne", "Test","Bundesliga",1,2);
        Competition usa = new Competition("France", "aaa", "Ligue 2",1,2);
        Competition russia = new Competition("Russie", "ru", "Premiere ligue",1,2);

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
