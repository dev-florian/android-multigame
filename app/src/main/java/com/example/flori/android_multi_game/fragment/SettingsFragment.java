package com.example.flori.android_multi_game.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.adapter.PlayerAdapter;
import com.example.flori.android_multi_game.adapter.PlayerScoreAdapter;
import com.example.flori.android_multi_game.manager.PlayerManager;
import com.example.flori.android_multi_game.model.Player;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;

public class SettingsFragment extends Fragment {
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_settings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new PlayerScoreAdapter(getAllScores(), getAllGames()));

        return view;
    }

    private ArrayList<Integer> getAllScores() {
        ArrayList<Integer> scores = new ArrayList<Integer>();

        Player player = PlayerManager.getInstance().getPlayer();
        scores.add(player.getScoreDragndrop());
        scores.add(player.getScoreSwipe());
        scores.add(player.getScoreFasttap());
        scores.add(player.getScoreIpac());
        return scores;
    }

    private ArrayList<String> getAllGames() {
        ArrayList<String> games = new ArrayList<String>();
        games.add("Drag & Drop");
        games.add("Swipe");
        games.add("Fast Tap");
        games.add("Ipac");
        return games;
    }

}