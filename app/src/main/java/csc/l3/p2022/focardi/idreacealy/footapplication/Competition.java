package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class Competition implements Comparable<Competition>{
    private String idChampionnat;
    private String nomChampionnat;
    private String nomPays;
    private String drapeau;




    public void setIdChampionnat(String idChampionnat) {
        this.idChampionnat = idChampionnat;
    }

    public Competition(String nomPays, String drapeau, String nomChampionnat, String idChampionnat) {
        this.nomPays= nomPays;
        this.drapeau= drapeau;
        this.nomChampionnat= nomChampionnat;
        this.idChampionnat = idChampionnat;
    }



    public int compareTo(Competition c) {
        return this.getNomPays().compareTo(c.getNomPays());
    }
    public String getIdChampionnat() {
        return idChampionnat;
    }
    public String getNomChampionnat() {
        return nomChampionnat;
    }

    public void setNomChampionnat(String nomChampionnat) {
        this.nomChampionnat = nomChampionnat;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getDrapeau() {
        return drapeau;
    }

    public void setDrapeau(String drapeau) {
        this.drapeau = drapeau;
    }

    @Override
    public String toString() {
        return this.nomPays +" " + this.nomChampionnat;
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if(o instanceof Competition && o != null)
        {
            Competition c = (Competition) o;
            res = this.idChampionnat.equals(c.idChampionnat);
        }
        return res;
    }
    public void loadMapPreview (ImageView logoCompet) {
        //start a background thread for networking
        new Thread(new Runnable() {
            public void run() {
                try {
                    //download the drawable
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(drapeau).getContent(), "src");
                    //edit the view in the UI thread
                    logoCompet.post(new Runnable() {
                        public void run() {
                            logoCompet.setImageDrawable(drawable);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int hashCode() {
        return (int) idChampionnat.hashCode() *
                nomChampionnat.hashCode() *
                nomPays.hashCode();
    }
}
