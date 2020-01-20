package io.RaguRamanTB.homelesseradicator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.models.GridItem;

public class GridAdapter extends BaseAdapter {

    Context c;
    ArrayList<GridItem> menuItems;

    public GridAdapter(Context c, ArrayList<GridItem> menuItems) {
        this.c = c;
        this.menuItems = menuItems;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.item_gridlayout,parent,false);
        }

        final GridItem menu = (GridItem) this.getItem(position);
        ImageView imageView = convertView.findViewById(R.id.grid_picture);
        TextView textView = convertView.findViewById(R.id.grid_text);

        textView.setText(menu.getGridText());
        imageView.setImageResource(menu.getGridPicture());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, menu.getGridText(),Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
