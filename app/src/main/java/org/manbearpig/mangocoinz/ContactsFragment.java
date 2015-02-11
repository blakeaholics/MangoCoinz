package org.manbearpig.mangocoinz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static org.manbearpig.mangocoinz.R.layout.item_user;

public class ContactsFragment extends Fragment {

    private ContactAdapter adapter;
    private List<ContactItem> listContacts;

    public ContactsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Preparing user interface
        final View v = inflater.inflate(R.layout.fragment_contacts, container, false);
        final ListView listView = (ListView) v.findViewById(R.id.lstContacts);
        listView.setClickable(true);

        final List<ContactItem> listOfContacts = new ArrayList<ContactItem>();

        //Loop contacts into an array here. Currently placeholder.

        listOfContacts.add(new ContactItem("Satoshi Nakamoto", "satoshi", "100000.00 MCZ"));
        listOfContacts.add(new ContactItem("John Appleseed", "japps", "49913.00 MCZ"));
        listOfContacts.add(new ContactItem("Mary Jane", "mj420errday", "420.00 MCZ"));
        listOfContacts.add(new ContactItem("BitTrex", "mangouser03456", "0.00 MCZ"));
        listOfContacts.add(new ContactItem("UseCryptos", "mangouser9456", "40.00 MCZ"));
        listOfContacts.add(new ContactItem("blakeaholics", "blakeaholics", "420.00 MCZ"));

        this.listContacts = listOfContacts;
        ContactAdapter adapter = new ContactAdapter(getActivity(), listOfContacts);
        this.adapter = adapter;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                System.out.println("Contact clicked!");

                Button bEdit = (Button)view.findViewById(R.id.btnEdit);
                if (bEdit.getVisibility() == View.GONE) {
                    bEdit.setVisibility(View.VISIBLE);
                } else {
                    bEdit.setVisibility(View.GONE);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                LayoutInflater inflater2 = getActivity().getLayoutInflater();
                View contactscontent = inflater2.inflate(R.layout.dialog_contact, null);

                final EditText txtName = (EditText) contactscontent.findViewById(R.id.txtAName);
                final EditText txtUser = (EditText) contactscontent.findViewById(R.id.txtAUser);

                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setView(contactscontent);
                builder2.setTitle("Edit Contact");



                // Add the buttons
                builder2.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        ContactsFragment myFragment = (ContactsFragment)getFragmentManager().findFragmentByTag("Contacts");
                        ContactAdapter adapter = myFragment.getAdapter();
                        List<ContactItem> listContacts = myFragment.getContactsList();

                        if (!txtName.getText().toString().equals("") && !txtUser.getText().toString().equals("")){
                            //listContacts.set(new ContactItem(txtName.getText().toString(), txtUser.getText().toString(), "0.00 MCZ"));
                            //adapter.notifyDataSetChanged();
                            showToast("TODO: Edit a contact");
                        } else {
                            showToast("Editing contact failed...");
                        }
                        dialog.dismiss();
                    }
                });

                builder2.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog contactdialog = builder2.create();
                contactdialog.show();
                return true;
            }
        });

        listView.setAdapter(adapter);
		
        return v;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public ContactAdapter getAdapter() {
        return this.adapter;
    }

    public List<ContactItem> getContactsList() {
        return listContacts;
    }

}
