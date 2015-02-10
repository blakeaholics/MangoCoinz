package org.manbearpig.mangocoinz;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static org.manbearpig.mangocoinz.R.layout.item_user;

public class ContactsFragment extends Fragment {
	
	public ContactsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Preparing user interface
        final View v = inflater.inflate(R.layout.fragment_contacts, container, false);
        final ListView listView = (ListView) v.findViewById(R.id.lstContacts);
        //listView.setOnItemClickListener(this);
        listView.setClickable(true);

        final List<ContactItem> listOfContacts = new ArrayList<ContactItem>();
        //Loop contacts into an array here. Currently placeholder.
        listOfContacts.add(new ContactItem("Satoshi Nakamoto", "satoshi", "100000.00 MCZ"));
        listOfContacts.add(new ContactItem("John Appleseed", "japps", "49913.00 MCZ"));
        listOfContacts.add(new ContactItem("Mary Jane", "mj420errday", "420.00 MCZ"));
        listOfContacts.add(new ContactItem("BitTrex", "mangouser03456", "0 MCZ"));
        listOfContacts.add(new ContactItem("UseCryptos", "mangouser9456", "40 MCZ"));
        listOfContacts.add(new ContactItem("blakeaholics", "blakeaholics", "420.00 MCZ"));

        ContactAdapter adapter = new ContactAdapter(getActivity(), listOfContacts);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                System.out.println("Contact clicked!");
                showToast(listOfContacts.get(position).getName());
            }
        });

        listView.setAdapter(adapter);
		
		/*final Dialog dialog = new Dialog(getActivity());
		// Include dialog.xml file
		dialog.setContentView(R.layout.dialog_contact);
		// Set dialog title
		dialog.setTitle("Add a contact");

		 set values for custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.textDialog);
		text.setText("Custom dialog Android example.");
		ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
		image.setImageResource(R.drawable.image0);

		//dialog.show();
		 
		Button declineButton = (Button) dialog.findViewById(R.id.cancelButton);
		// if decline button is clicked, close the custom dialog
		declineButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				dialog.dismiss();
			}
		});		*/
		
        return v;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}
