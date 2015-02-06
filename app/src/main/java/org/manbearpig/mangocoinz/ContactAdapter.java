package org.manbearpig.mangocoinz;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter implements OnClickListener {
    private Context context;

    private List<ContactItem> listContactItem;

    public ContactAdapter(Context context, List<ContactItem> listContactItem) {
        this.context = context;
        this.listContactItem = listContactItem;
    }

    public int getCount() {
        return listContactItem.size();
    }

    public Object getItem(int position) {
        return listContactItem.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ContactItem entry = listContactItem.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_contact, null);
        }
        TextView tvContact = (TextView) convertView.findViewById(R.id.tvContact);
        tvContact.setText(entry.getName());

        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvMobile);
        tvPhone.setText(entry.getUser());

        TextView tvMail = (TextView) convertView.findViewById(R.id.tvMail);
        tvMail.setText(entry.getTotal());

        // Set the onClick Listener on this button
        Button btnRemove = (Button) convertView.findViewById(R.id.btnEdit);
        btnRemove.setFocusableInTouchMode(false);
        btnRemove.setFocusable(false);
        btnRemove.setOnClickListener(this);
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item
        // that was clicked.
        btnRemove.setTag(entry);

        // btnRemove.setId(position);
        

        return convertView;
    }

    @Override
    public void onClick(View view) {
        ContactItem entry = (ContactItem) view.getTag();
        listContactItem.remove(entry);
        // listContactItem.remove(view.getId());
        notifyDataSetChanged();

    }

    private void showDialog(ContactItem entry) {
        // Create and show your dialog
        // Depending on the Dialogs button clicks delete it or do nothing
    }

}