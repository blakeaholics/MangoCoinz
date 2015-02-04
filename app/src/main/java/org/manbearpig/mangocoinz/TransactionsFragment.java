package org.manbearpig.mangocoinz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TransactionsFragment extends Fragment {
	
	public TransactionsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ArrayAdapter<String> adapter;
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);

        return rootView;
    }
}
