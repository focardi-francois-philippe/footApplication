package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class pour gerer la liste de match
 */
public class Matchs {
    private String nomEquipeDomicile;
    private String logoEquipeDomicile;

    public String getDebutDuMatch() {
        return debutDuMatch;
    }

    public void setDebutDuMatch(String debutDuMatch) {
        this.debutDuMatch = debutDuMatch;
    }

    public String getTimeMatch() {
        return timeMatch;
    }

    public void setTimeMatch(String timeMatch) {
        this.timeMatch = timeMatch;
    }

    private String nomEquipeExterieur;
    private String logoEquipeExterieur;
    private String scoreEquipeDomicile;
    private String scoreEquipeExterieur;
    private String matchId;
    private String debutDuMatch;
    private String timeMatch;



    public Matchs(String nomEquipeDomicile, String logoEquipeDomicile,
                  String nomEquipeExterieur, String logoEquipeExterieur,
                  String scoreEquipeDomicile, String scoreEquipeExterieur,String timeMatch,String debutDuMatch, String matchId) {
        this.nomEquipeDomicile = nomEquipeDomicile;
        this.logoEquipeDomicile = logoEquipeDomicile;
        this.nomEquipeExterieur = nomEquipeExterieur;
        this.logoEquipeExterieur = logoEquipeExterieur;
        this.scoreEquipeDomicile = scoreEquipeDomicile;
        this.scoreEquipeExterieur = scoreEquipeExterieur;
        this.timeMatch = timeMatch;
        this.debutDuMatch = debutDuMatch;
        this.matchId = matchId;
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

    public void loadMapPreview (ImageView logoDomicile,ImageView logoExterieur) {
        //start a background thread for networking
        new Thread(new Runnable() {
            public void run(){
                try {
                    //download the drawable
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(logoEquipeDomicile).getContent(), "src");
                    final Drawable drawableExterieur = Drawable.createFromStream((InputStream) new URL(logoEquipeExterieur).getContent(), "src");
                    //edit the view in the UI thread
                    logoDomicile.post(new Runnable() {
                        public void run() {
                            logoDomicile.setImageDrawable(drawable);
                        }
                    });
                    logoExterieur.post(new Runnable()
                    {
                        @Override
                        public void run() {
                            logoExterieur.setImageDrawable(drawableExterieur);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

    public String getScoreEquipeDomicile() {
        return scoreEquipeDomicile;
    }

    public void setScoreEquipeDomicile(String scoreEquipeDomicile) {
        this.scoreEquipeDomicile = scoreEquipeDomicile;
    }

    public String getScoreEquipeExterieur() {
        return scoreEquipeExterieur;
    }

    public void setScoreEquipeExterieur(String scoreEquipeExterieur) {
        this.scoreEquipeExterieur = scoreEquipeExterieur;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
