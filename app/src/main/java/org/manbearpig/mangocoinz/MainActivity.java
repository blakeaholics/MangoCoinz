package org.manbearpig.mangocoinz;

import org.manbearpig.mangocoinz.adapter.*;
import org.manbearpig.mangocoinz.model.*;
import org.manbearpig.mangocoinz.util.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList < NavDrawerItem > navDrawerItems;
    private NavDrawerListAdapter adapter;
    private int curPos = 0;
    private int intTheme = 1; //0 Green, 1 Orange
    private String PREFS_NAME = "MangoCoinz_beta_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        intTheme = settings.getInt("intTheme", 0);
        setTheme(getActivityTheme());

        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList < NavDrawerItem > ();

        // TO DO: Proper header
        //View head = View.inflate(this.getApplicationContext(), R.layout.drawer_header, null);
        //mDrawerList.addHeaderView(head, R.layout.drawer_header, false);

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem("Overview", navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "+4"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "3+"));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(this,
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }

        /*

        Countdown timer shit.

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Log.d("Time zone","="+tz.getDisplayName());

         */

        loadSettings();

    }

    private void loadSettings() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        int theme = settings.getInt("intTheme", 0);
        intTheme = theme;
        Log.d("Settings", "Setting: intTheme = " + theme);
        setTheme(getActivityTheme());

        /*Switch swMining = new Switch(this);
        swMining = (Switch) findViewById(R.id.swMining);
        boolean mining = settings.getBoolean("swMining", true);
        Log.d("Settings", "Setting: swMining = " + mining);
        swMining.setChecked(mining);

        CheckBox chkSync = new CheckBox(this);
        chkSync = (CheckBox) findViewById(R.id.chkSync);
        boolean sync = settings.getBoolean("chkSync", true);
        Log.d("Settings", "Setting: chkSync = " + sync);
        chkSync.setChecked(sync);*/



        Log.d("Settings", "Settings loaded!");
    }

    private void loadTheme(int i) {
        if (intTheme == 0) { //Green
            chooseTheme(0);
        } else if (intTheme == 1) { //Orange
            chooseTheme(1);
        }
        intTheme = i;
    }

    /**
     * Theme selector: Green or Orange.
     **/

    public void chooseTheme(int i) {
        switch (i) {
            case 0:
                ThemeUtil.changeToTheme(this, ThemeUtil.THEME_DEFAULT); //GREEN
                break;
            case 1:
                ThemeUtil.changeToTheme(this, ThemeUtil.THEME_ORANGE);
                break;
        }
        intTheme = i;
    }

    public int getThemeVar() {
        return intTheme;
    }

    public void setThemeVar(int i) {
        intTheme = i;
    }

    public int getButtonTheme()
    {
        int themeLayout = 0;
        switch (intTheme) {
            case 0:
                themeLayout = R.drawable.btn_green;
                break;
            case 1:
                themeLayout = R.drawable.btn_orange;
                break;
        }
        return themeLayout;
    }

    public int getActivityTheme()
    {
        int themeLayout = 0;
        switch (intTheme) {
            case 0:
                themeLayout = R.style.MyTheme;
                break;
            case 1:
                themeLayout = R.style.MyThemeOrange;
                break;
        }
        return themeLayout;
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView <? > parent, View view, int position, long id) {
            // display view for selected nav drawer item
            displayView(position);
            curPos = position;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        switch (curPos) {
            case 0:
                menu.getItem(0).setVisible(true); //Sync
                menu.getItem(1).setVisible(false); //Send
                menu.getItem(2).setVisible(false); //Refresh
                menu.getItem(3).setVisible(false); //Add
                menu.getItem(4).setVisible(false); //Generate
                return true;
            case 1:
                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(true);
                menu.getItem(2).setVisible(false);
                menu.getItem(3).setVisible(false);
                menu.getItem(4).setVisible(false);
                return true;
            case 2:
                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(false);
                menu.getItem(3).setVisible(false);
                menu.getItem(4).setVisible(true);
                return true;
            case 3:
                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(true);
                menu.getItem(3).setVisible(false);
                menu.getItem(4).setVisible(false);
                return true;
            case 4:
                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(false);
                menu.getItem(3).setVisible(true);
                menu.getItem(4).setVisible(false);
                return true;
            case 5:
                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(true);
                menu.getItem(3).setVisible(false);
                menu.getItem(4).setVisible(false);
                return true;
            default:
                menu.getItem(0).setVisible(false);
                menu.getItem(1).setVisible(false);
                menu.getItem(2).setVisible(false);
                menu.getItem(3).setVisible(false);
                menu.getItem(4).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click



        switch (item.getItemId()) {

            /**
             * SYNC
             */

            case R.id.action_sync:
                Toast.makeText(this, getString(R.string.toast_sync),
                        Toast.LENGTH_LONG).show();

                return true;

            /**
             * SETTINGS
             */

            case R.id.action_settings:


                AlertDialog.Builder settingsbuilder = new AlertDialog.Builder(this);
                LayoutInflater settingsinflater = getLayoutInflater();
                settingsbuilder.setView(settingsinflater.inflate(R.layout.fragment_settings, null));

                // Add the buttons
                settingsbuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        saveSettings();
                        loadTheme(intTheme);
                        dialog.dismiss();
                    }
                });


                settingsbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog settingsdialog = settingsbuilder.create();
                // Include dialog.xml file
                //dialog.setContentView(R.layout.fragment_settings);


                // Set dialog title
                settingsdialog.setTitle("Settings");
                settingsdialog.show();

                // Theme spinner shit

                final Spinner spinner = (Spinner) settingsdialog.findViewById(R.id.spn_Theme);
                if (spinner != null) {
                    spinner.setSelection(intTheme);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3) {
                            intTheme = (int) arg3;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            intTheme = Integer.getInteger(spinner.getSelectedItem().toString());
                        }
                    });
                }

                return true;

            /**
             * ADD CONTACT
             */

            case R.id.action_add:

                LayoutInflater inflater2 = getLayoutInflater();
                View contactscontent = inflater2.inflate(R.layout.dialog_contact, null);

                final EditText txtName = (EditText) contactscontent.findViewById(R.id.txtLUser);
                final EditText txtUser = (EditText) contactscontent.findViewById(R.id.txtLPass);

                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setView(contactscontent);
                builder2.setTitle("Add Contact");



                // Add the buttons
                builder2.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        ContactsFragment myFragment = (ContactsFragment)getFragmentManager().findFragmentByTag("Contacts");
                        ContactAdapter adapter = myFragment.getAdapter();
                        List<ContactItem> listContacts = myFragment.getContactsList();

                        if (!txtName.getText().toString().equals("") && !txtUser.getText().toString().equals("")){
                            listContacts.add(new ContactItem(txtName.getText().toString(), txtUser.getText().toString(), "0.00 MCZ"));
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplication(), "Adding contact failed...", Toast.LENGTH_LONG).show();
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

            /**
             * QUIT
             */

            case R.id.action_quit:
                Toast.makeText(this, getString(R.string.toast_quit),
                        Toast.LENGTH_SHORT).show();
                saveSettings();
                finish(); // close the activity
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveSettings() {
        /**
         * Theme selection
         */

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("intTheme", intTheme);
       // editor.putBoolean("swMining", ((Switch)(findViewById(R.id.swMining))).isChecked());
       // editor.putBoolean("chkSync", ((CheckBox)(findViewById(R.id.chkSync))).isChecked());

        editor.commit();
        Log.d("Settings", "Setting: intTheme = " + intTheme);
        //Log.d("Settings", "Setting: swMining = " + (((Switch)(findViewById(R.id.swMining))).isChecked()));
        //Log.d("Settings", "Setting: chkSync = " +  (((CheckBox)(findViewById(R.id.chkSync))).isChecked()));
        Log.d("Settings", "Settings saved!");
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null; String strFragName = "";
        switch (position) {
            case 0:
                fragment = new OverviewFragment();
                strFragName = "Overview";
                break;
            case 1:
                fragment = new SendFragment();
                strFragName = "Send";
                break;
            case 2:
                fragment = new ReceiveFragment();
                strFragName = "Receive";
                break;
            case 3:
                fragment = new TransactionsFragment();
                strFragName = "Transactions";
                break;
            case 4:
                fragment = new ContactsFragment();
                strFragName = "Contacts";
                break;
            case 5:
                fragment = new NewsFragment();
                strFragName = "News";
                break;
            case 6:
                fragment = new LoginFragment();
                strFragName = "Login";
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment, strFragName).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }



    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}