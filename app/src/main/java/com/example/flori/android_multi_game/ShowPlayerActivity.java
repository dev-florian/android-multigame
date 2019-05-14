package com.example.flori.android_multi_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.flori.android_multi_game.adapter.PlayerAdapter;
import com.example.flori.android_multi_game.model.Player;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;

public class ShowPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_player_activity);
        RecyclerView recyclerView = findViewById(R.id.show_players);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlayerAdapter(getAllPlayers()));
    }

    private ArrayList<Player> getAllPlayers() {
        Realm mRealmInstance = Realm.getDefaultInstance();
        RealmQuery<Player> query = mRealmInstance.where(Player.class);
        return new ArrayList<>(query.findAll());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
