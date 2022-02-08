package csc.l3.p2022.focardi.idreacealy.footapplication;

public class Competition {
    private String nomChampionnat;
    private String nomPays;
    private int idPays;
    private int idChampionnat;
    private String drapeau;



    public Competition(String nomPays, String drapeau, String nomChampionnat, int idPays, int idChampionnat) {
        this.nomPays= nomPays;
        this.drapeau= drapeau;
        this.nomChampionnat= nomChampionnat;
        this.idPays = idPays;
        this.idChampionnat = idChampionnat;
    }

    public int getIdPays() {
        return idPays;
    }

    public int getIdChampionnat() {
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
}
