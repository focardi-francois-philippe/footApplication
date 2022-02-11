package csc.l3.p2022.focardi.idreacealy.footapplication;

/**
 * Created by Idricealy MOURTADHOI on 06
 * Classe qui va permettre de faire des reqûetes à l'Api football
 * https://apifootball.com/documentation
 */
public enum ActionAPI {
    GET_COUNTRIES("get_countries"),
    GET_COMPETITION("get_leagues");

    /**
     * Type de requête qui correspond au paramètre de l'URL de l'API
     */
    private String actionName;

    private ActionAPI(String ac) {
        this.actionName = ac;
    }

    public String getAction(){
        return actionName;
    }
}
