package com.example.cip.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        // Toast.makeText(getActivity(),"erstellen!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Preference button = findPreference("delete_Button");
        //Preference button = (Preference)getPreferenceManager().findPreference("delete_Button");
        /*if (button != null) {
            Toast.makeText(getActivity(), "button gefunden!", Toast.LENGTH_SHORT).show();
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //code for what you want it to do
                    Toast.makeText(getActivity(), "datenbank löschen!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }*/
    }



}

