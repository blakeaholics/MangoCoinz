package org.manbearpig.mangocoinz;

/**
 * Created by Blake on 03/02/2015.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    public LoginFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        int layout = ((MainActivity)getActivity()).getButtonTheme();

        int sdk = android.os.Build.VERSION.SDK_INT;
        Button btn = (Button) rootView.findViewById(R.id.btnLogin);
        Log.d("ButtonTheme", "Login button theme: " + String.valueOf(layout));

        // TODO:

        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            btn.setBackgroundDrawable( getResources().getDrawable(layout) );
        } else {
            btn.setBackground( getResources().getDrawable(layout));

        }

        TextView textView =(TextView) rootView.findViewById(R.id.txtSignUp);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.mangocoinz.com'> SIGN UP </a>";
        textView.setText(Html.fromHtml(text));


        return rootView;
    }
}
