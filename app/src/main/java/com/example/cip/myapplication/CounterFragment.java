package com.example.cip.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CounterFragment extends DialogFragment implements View.OnClickListener{

    private EditText p1Name, p2Name;
    private Button btn;
    private String p1Name_txt, p2Name_txt;

    public CounterFragment() {
    }

    public static CounterFragment newInstance(String p1Name, String p2Name) {
        CounterFragment fragment = new CounterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("p1Name", p1Name);
        bundle.putString("p2Name", p2Name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        p1Name = (EditText) view.findViewById(R.id.frg_counter_p1);
        p2Name = (EditText) view.findViewById(R.id.frg_counter_p2);
        Button btn = (Button) view.findViewById(R.id.frg_counter_btn);
        Bundle bundle = getArguments();
        p1Name.setText(bundle.getString("p1Name"));
        p2Name.setText(bundle.getString("p2Name"));
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        p1Name_txt = p1Name.getText().toString();
        p2Name_txt = p2Name.getText().toString();
        ((PointsCounter) getActivity()).setPlayerNames(p1Name_txt,p2Name_txt);
        getFragmentManager().beginTransaction().remove(CounterFragment.this).commit();
    }
}
