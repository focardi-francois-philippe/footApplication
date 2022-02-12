package csc.l3.p2022.focardi.idreacealy.footapplication.model;

/**
 * Created by Idricealy MOURTADHOI on 12
 */
public class Event {

    private String match_id;
    private String hometeam;
    private String awayteam;

    public Event(String mid, String ht, String at){
        this.match_id = mid;
        this.hometeam = ht;
        this.awayteam = at;
    }

    public String getMatch_id() {
        return match_id;
    }

    public String getAwayteam() {
        return awayteam;
    }

    public String getHometeam() {
        return hometeam;
    }
}
