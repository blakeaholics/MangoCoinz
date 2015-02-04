package org.manbearpig.mangocoinz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReceiveFragment extends Fragment {
	
	public ReceiveFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_receive_coinz, container, false);
        setHasOptionsMenu(false);
        return rootView;
    }
}
