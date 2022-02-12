package csc.l3.p2022.focardi.idreacealy.footapplication.model;

/**
 * Created by Idricealy MOURTADHOI on 12
 */
public class Competition {
    private String league_name;
    private String country_logo;

    public Competition (String ln, String cl){
        this.league_name = ln;
        this.country_logo = cl;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getCountry_logo() {
        return country_logo;
    }

    public void setCountry_logo(String country_logo) {
        this.country_logo = country_logo;
    }
}
