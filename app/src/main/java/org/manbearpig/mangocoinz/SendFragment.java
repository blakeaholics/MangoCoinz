package org.manbearpig.mangocoinz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SendFragment extends Fragment {
	
	public SendFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_send_coinz, container, false);
        setHasOptionsMenu(true);
         
        return rootView;
    }
}
