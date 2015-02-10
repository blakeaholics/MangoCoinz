package org.manbearpig.mangocoinz;

import org.manbearpig.mangocoinz.adapter.*;
import org.manbearpig.mangocoinz.model.*;
import org.manbearpig.mangocoinz.util.ThemeUtil;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.qustom.dialog.QustomDialogBuilder;

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

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
    private int curPos = 0;
    private int intTheme;
    private String strTheme = "0"; //Green, Orange

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
        
		
		
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

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
	}

    private void loadTheme(int i) {
        switch (i) {
            case 0:
                setTheme(R.style.MyTheme); //GREEN
                break;
            case 1:
                setTheme(R.style.MyThemeOrange);
                break;
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
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
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
            case R.id.action_sync:
                Toast.makeText(this, getString(R.string.toast_sync),
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.action_settings:
                //displayView(6);
                final Dialog dialog = new Dialog(this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.fragment_settings);
                // Set dialog title
                dialog.setTitle("Settings - MangoCoinz");
                dialog.show();

                Button discardButton = (Button) dialog.findViewById(R.id.btnDiscard);
                // if decline button is clicked, close the custom dialog
                discardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                Button saveButton = (Button) dialog.findViewById(R.id.btnSave);
                // if decline button is clicked, close the custom dialog
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        saveSettings();
                        dialog.dismiss();
                    }
                });

                // Theme spinner shit

                Spinner spinner = (Spinner) dialog.findViewById(R.id.spn_Theme);
                //if (spinner != null) {
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3) {
                            strTheme = String.valueOf(arg3);
                            Toast.makeText(getApplicationContext(), strTheme, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                //}

                return true;
                // ** snippet**
            case R.id.action_add:
                final Dialog dialog2 = new Dialog(this);
                // Include dialog.xml file
                dialog2.setContentView(R.layout.dialog_contact);
                // Set dialog title
                dialog2.setTitle("Add Contact");
                dialog2.show();

                Button declineButton = (Button) dialog2.findViewById(R.id.cancelButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog2.dismiss();
                    }
                });
                return true;

            case R.id.action_quit:
                Toast.makeText(this, getString(R.string.toast_quit),
                        Toast.LENGTH_SHORT).show();
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
        if (strTheme.equals("0")) { //Green
            chooseTheme(0);
        } else if (strTheme.equals("1")) { //Orange
            chooseTheme(1);
        }
    }

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
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
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new OverviewFragment();
			break;
		case 1:
			fragment = new SendFragment();
			break;
		case 2:
			fragment = new ReceiveFragment();
			break;
		case 3:
			fragment = new TransactionsFragment();
			break;
		case 4:
			fragment = new ContactsFragment();
			break;
		case 5:
			fragment = new NewsFragment();
			break;
        case 6:
            fragment = new SettingsFragment();
            break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

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
