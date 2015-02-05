package org.manbearpig.mangocoinz;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import static org.manbearpig.mangocoinz.R.layout.item_user;

public class ContactsFragment extends Fragment {
	
	public ContactsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Preparing user interface
        final View v = inflater.inflate(R.layout.fragment_contacts, container, false);
        final ListView listView = (ListView) v.findViewById(R.id.lstContacts);
        //listView.setOnItemClickListener(this);

        return v;
    }

}
