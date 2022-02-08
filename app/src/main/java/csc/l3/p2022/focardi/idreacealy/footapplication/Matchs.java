package csc.l3.p2022.focardi.idreacealy.footapplication;

/**
 * Class pour gerer la liste de match
 */
public class Matchs {
    private String nomEquipeDomicile;
    private String logoEquipeDomicile;
    private String nomEquipeExterieur;
    private String logoEquipeExterieur;
    private int scoreEquipeDomicile;
    private int scoreEquipeExterieur;


    public Matchs(String nomEquipeDomicile, String logoEquipeDomicile,
                  String nomEquipeExterieur, String logoEquipeExterieur, int scoreEquipeDomicile,
                  int scoreEquipeExterieur)
    {
        this.nomEquipeDomicile = nomEquipeDomicile;
        this.logoEquipeDomicile = logoEquipeDomicile;
        this.nomEquipeExterieur = nomEquipeExterieur;
        this.logoEquipeExterieur = logoEquipeExterieur;
        this.scoreEquipeDomicile = scoreEquipeDomicile;
        this.scoreEquipeExterieur = scoreEquipeExterieur;
    }


    public String getNomEquipeDomicile() {
        return nomEquipeDomicile;
    }

    public void setNomEquipeDomicile(String nomEquipeDomicile) {
        this.nomEquipeDomicile = nomEquipeDomicile;
    }

    public String getLogoEquipeDomicile() {
        return logoEquipeDomicile;
    }

    public void setLogoEquipeDomicile(String logoEquipeDomicile) {
        this.logoEquipeDomicile = logoEquipeDomicile;
    }

    public String getNomEquipeExterieur() {
        return nomEquipeExterieur;
    }

    public void setNomEquipeExterieur(String nomEquipeExterieur) {
        this.nomEquipeExterieur = nomEquipeExterieur;
    }

    public String getLogoEquipeExterieur() {
        return logoEquipeExterieur;
    }

    public void setLogoEquipeExterieur(String logoEquipeExterieur) {
        this.logoEquipeExterieur = logoEquipeExterieur;
    }

    public int getScoreEquipeDomicile() {
        return scoreEquipeDomicile;
    }

    public void setScoreEquipeDomicile(int scoreEquipeDomicile) {
        this.scoreEquipeDomicile = scoreEquipeDomicile;
    }

    public int getScoreEquipeExterieur() {
        return scoreEquipeExterieur;
    }

    public void setScoreEquipeExterieur(int scoreEquipeExterieur) {
        this.scoreEquipeExterieur = scoreEquipeExterieur;
    }
}
