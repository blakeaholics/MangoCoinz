package org.manbearpig.mangocoinz.util;

import android.app.Activity;
import android.content.Intent;

import org.manbearpig.mangocoinz.R;

/**
 * Created by Blake on 07/02/2015.
 */
public class ThemeUtil {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_ORANGE = 1;
    public final static int THEME_BLUE = 2;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.MyTheme);
                break;
            case THEME_ORANGE:
                activity.setTheme(R.style.MyThemeOrange);
                break;
        }
    }
}
