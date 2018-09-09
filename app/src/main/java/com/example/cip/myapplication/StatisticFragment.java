package com.example.cip.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StatisticFragment extends DialogFragment implements View.OnClickListener {
    private TextView game_Id, p1Name, p1average, p1counter100, p1counter160, p1counter180, p1highestthrow,
            p2Name, p2average, p2counter100, p2counter160, p2counter180, p2highestthrow;
    private final int WINNER_COLOR = Color.rgb(255, 255, 0);
    private View rootView;
    private String gameId;

    public StatisticFragment() {
    }

    public static StatisticFragment newInstance(String gameTitle, String gameContent, int gameId) {
        StatisticFragment fragment = new StatisticFragment();
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
        setFragmentViews(view);
        String Title = getArguments().getString("gameTitle", "Title");
        String Content = getArguments().getString("gameContent", "Content");
        gameId = String.valueOf(getArguments().getInt("gameId", 0));
        String[] splitedContent = Content.split("/");
        setFragmentContent(splitedContent);
        getDialog().setTitle(Title);
    }

    private void setFragmentViews(View view){
        game_Id= (TextView) view.findViewById(R.id.fragment_gameId);
        p1Name = (TextView) view.findViewById(R.id.fragment_player1Name);
        p1average = (TextView) view.findViewById(R.id.fragment_average1);
        p1counter100 = (TextView) view.findViewById(R.id.fragment_counter1001);
        p1counter160 = (TextView) view.findViewById(R.id.fragment_counter1601);
        p1counter180 = (TextView) view.findViewById(R.id.fragment_counter1801);
        p1highestthrow = (TextView) view.findViewById(R.id.fragment_highestthrow1);
        p2Name = (TextView) view.findViewById(R.id.fragment_player2Name);
        p2average = (TextView) view.findViewById(R.id.fragment_average2);
        p2counter100 = (TextView) view.findViewById(R.id.fragment_counter1002);
        p2counter160 = (TextView) view.findViewById(R.id.fragment_counter1602);
        p2counter180 = (TextView) view.findViewById(R.id.fragment_counter1802);
        p2highestthrow = (TextView) view.findViewById(R.id.fragment_highestthrow2);
        rootView =  view.getRootView();
        view.findViewById(R.id.fragment_button_delete).setOnClickListener(this);
        view.findViewById(R.id.fragment_button_share).setOnClickListener(this);
    }

    private void setFragmentContent(String[] content){
        game_Id.setText("GameID: " + gameId);
        p1Name.setText(content[0]);
        p1counter100.setText(content[1]);
        p1counter160.setText(content[2]);
        p1counter180.setText(content[3]);
        p1average.setText(content[4]);
        p1highestthrow.setText(content[5]);
        p2Name.setText(content[6]);
        p2counter100.setText(content[7]);
        p2counter160.setText(content[8]);
        p2counter180.setText(content[9]);
        p2average.setText(content[10]);
        p2highestthrow.setText(content[11]);
        if(Integer.parseInt(content[12]) == 0){
            p1Name.setBackgroundColor(WINNER_COLOR);
        }else{
            p2Name.setBackgroundColor(WINNER_COLOR);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fragment_button_delete){
            DartDbHandler db = new DartDbHandler(getContext());
            GameHistory selectNote = db.getNote(Integer.parseInt(gameId));
            db.deleteNote(selectNote);
            ((Statistic) getActivity()).dataChanged();
        }else if (id == R.id.fragment_button_share){
            ShareScreenshot shareScreenshot = new ShareScreenshot();
            startActivity(Intent.createChooser(shareScreenshot.getShareIntent(rootView,this.getContext()), shareScreenshot.getShareMesage()));
        }
    }
}
