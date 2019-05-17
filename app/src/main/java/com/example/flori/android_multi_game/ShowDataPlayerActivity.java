package com.example.flori.android_multi_game;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flori.android_multi_game.adapter.PlayerAdapter;
import com.example.flori.android_multi_game.manager.PlayerManager;
import com.example.flori.android_multi_game.model.Player;
import com.example.flori.android_multi_game.utils.GameUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ShowDataPlayerActivity extends AppCompatActivity {

    private ImageView player_image;

    private TextView profil_name;
    private TextView profil_firstname;
    private TextView profil_age;
    private TextView profil_localisation;
    private TextView profil_ipac_score;
    private TextView profil_swipe_score;
    private TextView profil_drag_score;
    private TextView profil_fasttap_score;
    private ImageView profil_image;

    private Button profil_delete;
    private Button profil_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data_player_activity);

        profil_name = findViewById(R.id.profil_name);
        profil_firstname = findViewById(R.id.profil_firstname);
        profil_age = findViewById(R.id.profil_age);
        profil_localisation = findViewById(R.id.profil_localisation);
        profil_ipac_score = findViewById(R.id.profil_ipac_score);
        profil_swipe_score = findViewById(R.id.profil_swipe_score);
        profil_drag_score = findViewById(R.id.profil_drag_score);
        profil_fasttap_score = findViewById(R.id.profil_fasttap_score);
        profil_image = findViewById(R.id.profil_image);
        profil_delete = findViewById(R.id.profil_delete);
        profil_update = findViewById(R.id.profil_update);

        Player player = PlayerManager.getInstance().getPlayer();
        final String name = player.getName();
        String firstName = player.getFirstName();
        Integer age = player.getAge();
        String localisation = player.getLocalisation();
        int ipacScore = player.getScoreIpac();
        int swipeScore = player.getScoreSwipe();
        int fastTapScore = player.getScoreFasttap();
        int dragScore = player.getScoreDragndrop();

        Picasso.get().load(player.getPicture()).into(profil_image);

        profil_name.setText(name);
        profil_firstname.setText(firstName);
        profil_age.setText("Age : " + age.toString());
        profil_localisation.setText("Localisation : " + localisation);
        profil_ipac_score.setText("Ipac Score : " + ipacScore);
        profil_drag_score.setText("Drag Score : " + dragScore);
        profil_swipe_score.setText("Swipe Score : " + swipeScore);
        profil_fasttap_score.setText("FastTap Score : " + fastTapScore);


        profil_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm mRealmInstance = Realm.getDefaultInstance();
                mRealmInstance.beginTransaction();

                try {
                    RealmResults<Player> player = mRealmInstance.where(Player.class)
                            .equalTo("name", name)
                            .findAll();
                    player.deleteAllFromRealm();
                    mRealmInstance.commitTransaction();
                } catch (Exception ignored) {

                }

                GameUtils.launchView(ShowDataPlayerActivity.this, ShowPlayerActivity.class, true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
