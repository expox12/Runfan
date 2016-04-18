package mycompany.runfan.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mycompany.runfan.R;
import mycompany.runfan.item_object;

/**
 * Created by macmini02 on 18.04.16.
 */
public class NavigationAdapter extends BaseAdapter {
    public Context activity;
    ArrayList<item_object> arrayItems;

    public NavigationAdapter(Context activity, ArrayList<item_object> arrayItems) {
        super();
        this.activity = activity;
        this.arrayItems = arrayItems;
    }

    public static class Fila {
        TextView titulo;
        ImageView icono;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fila view;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            view = new Fila();

            item_object item_object = arrayItems.get(position);
            convertView = inflater.inflate(R.layout.simple_list_item_1, null);

            view.titulo = (TextView) convertView.findViewById(R.id.title_item);
            view.titulo.setText(item_object.getTitulo());

            view.icono = (ImageView) convertView.findViewById(R.id.icono);
            view.icono.setImageResource(item_object.getIcono());

            convertView.setTag(view);
        } else {
            view = (Fila) convertView.getTag();
        }
        return convertView;
    }


}
