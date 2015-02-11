package org.manbearpig.mangocoinz.adapter;

import org.manbearpig.mangocoinz.MainActivity;
import org.manbearpig.mangocoinz.R;
import org.manbearpig.mangocoinz.model.NavDrawerItem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {
	
	private Context context;
	private MainActivity activity; //SWAP CONTEXT WITH ACTIVITY
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	public NavDrawerListAdapter(MainActivity activity, ArrayList<NavDrawerItem> navDrawerItems){
		this.activity = activity;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            // ADD THEME SWITCH
            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            Log.v("NavDrawerThemeNull", String.valueOf(activity.getThemeVar()));
            switch (activity.getThemeVar()) {
                case 0:
                    convertView = mInflater.inflate(R.layout.drawer_list_item, null);
                    break;
                case 1:
                    convertView = mInflater.inflate(R.layout.drawer_list_item_orange, null);
                    break;
            }
        /*} else {
            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            Log.v("NavDrawerTheme", String.valueOf(activity.getThemeVar()));
            switch (activity.getThemeVar()) {
                case 0:
                    convertView = mInflater.inflate(R.layout.drawer_list_item, parent);
                    break;
                case 1:
                    convertView = mInflater.inflate(R.layout.drawer_list_item_orange, parent);
                    break;
            }*/

        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
         
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        
        // displaying count
        // check whether it set visible or not
        if(navDrawerItems.get(position).getCounterVisibility()){
        	txtCount.setText(navDrawerItems.get(position).getCount());
        }else{
        	// hide the counter view
        	txtCount.setVisibility(View.GONE);
        }
        
        return convertView;
	}


}
