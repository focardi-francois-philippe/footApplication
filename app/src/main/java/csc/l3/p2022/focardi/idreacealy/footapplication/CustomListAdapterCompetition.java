package csc.l3.p2022.focardi.idreacealy.footapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapterCompetition extends BaseAdapter {
    private List<Competition> listCompetitions;
    private LayoutInflater layoutInflater;
    private Context context;


    public CustomListAdapterCompetition(Context aContext, List<Competition> listData) {
        this.context = aContext;
        this.listCompetitions = listData;
        this.layoutInflater = LayoutInflater.from(aContext);
    }

    static class ViewHolder {
        ImageView flagView;
        TextView countryNameView;
        TextView championnatName;
    }
    @Override
    public int getCount() {
        return listCompetitions.size();
    }

    @Override
    public Object getItem(int position) {
        return listCompetitions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_competition_layout, null);
            holder = new ViewHolder();
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.championnatName = (TextView) convertView.findViewById(R.id.textView_championnat);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Competition competition = this.listCompetitions.get(position);
        holder.countryNameView.setText(competition.getNomPays());
        holder.championnatName.setText(competition.getNomChampionnat());

        competition.loadMapPreview(holder.flagView);
        //int imageId = this.getMipmapResIdByName(country.getFlagName());

        //holder.flagView.setImageResource(imageId);

        return convertView;
    }

}
