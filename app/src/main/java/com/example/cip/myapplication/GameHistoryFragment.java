package com.example.cip.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class GameHistoryFragment extends DialogFragment {
    private TextView Title, Content, ID;




    public GameHistoryFragment() {
    }


    public static GameHistoryFragment newInstance(String gameTitle, String gameContent, int gameId) {
        GameHistoryFragment fragment = new GameHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("gameTitle",gameTitle);
        bundle.putString("gameContent",gameContent);
        bundle.putInt("gameId",gameId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_history, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Title = (TextView) view.findViewById(R.id.textView5);
        Content = (TextView) view.findViewById(R.id.textView6);
        ID = (TextView) view.findViewById(R.id.textView7);
        Title.setText(getArguments().getString("gameTitle", "Title"));
        Content.setText(getArguments().getString("gameContent", "Content"));
        ID.setText(String.valueOf(getArguments().getInt("gameId", 0)));
        getDialog().setTitle("Info");
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
