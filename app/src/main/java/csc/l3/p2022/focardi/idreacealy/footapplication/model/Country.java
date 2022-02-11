package csc.l3.p2022.focardi.idreacealy.footapplication.model;

/**
 * Created by Idricealy MOURTADHOI on 06
 */
public class Country {
    /**
     * Identifiant du pays
     */
    private String country_id;

    /**
     * Nom du pays
     */
    private String country_name;

    /**
     * Logo du pays
     */
    private String country_logo;

    /**
     *
     * @param cid id du pays
     * @param cn nom du pays
     * @param cl url de l'adresse logo
     */
    public Country(String cid, String cn, String cl){
        this.country_id = cid;
        this.country_name = cn;
        this.country_logo = cl;
    }

    public String getCountry_id(){
        return country_id;
    }

    public String getCountry_name(){
        return country_name;
    }

    public String getCountry_logo(){
        return country_logo;
    }

}

