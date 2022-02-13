package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class  CustomListAdapterMatchs extends BaseAdapter {

    private List<Matchs> listsMatchs;
    private LayoutInflater layoutInflater;
    private Context context;


    public CustomListAdapterMatchs(Context aContext, List<Matchs> listData) {
        this.context = aContext;
        this.listsMatchs = listData;
        this.layoutInflater = LayoutInflater.from(aContext);
    }

    static class ViewHolder {
        ImageView logoEquipeDomicile;
        TextView nomEquipeDomicile;
        TextView scoreEquipeDomicile;
        ImageView logoEquipeExterieur;
        TextView nomEquipeExterieur;
        TextView scoreEquipeExterieur;
    }
    @Override
    public int getCount() {
        return listsMatchs.size();
    }

    @Override
    public Object getItem(int position) {
        return listsMatchs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_matchs_layout, null);
            holder = new ViewHolder();
            holder.logoEquipeDomicile = (ImageView) convertView.findViewById(R.id.logo_equipe_domicile);
            holder.nomEquipeDomicile = (TextView) convertView.findViewById(R.id.nom_equipe_domicile);
            holder.scoreEquipeDomicile = (TextView) convertView.findViewById(R.id.score_equipe_domicile);
            holder.logoEquipeExterieur = (ImageView) convertView.findViewById(R.id.logo_equipe_exterieur);
            holder.nomEquipeExterieur = (TextView) convertView.findViewById(R.id.nom_equipe_exterieur);
            holder.scoreEquipeExterieur = (TextView) convertView.findViewById(R.id.score_equipe_exterieur);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Matchs matchs = this.listsMatchs.get(position);

        holder.nomEquipeDomicile.setText(matchs.getNomEquipeDomicile());

        holder.scoreEquipeDomicile.setText(String.valueOf(matchs.getScoreEquipeDomicile()));

        holder.nomEquipeExterieur.setText(matchs.getNomEquipeExterieur());
        holder.scoreEquipeExterieur.setText(String.valueOf(matchs.getScoreEquipeExterieur()));

        matchs.loadMapPreview(holder.logoEquipeDomicile, holder.logoEquipeExterieur);

        //int imageId = this.getMipmapResIdByName(country.getFlagName());



        return convertView;
    }

}
