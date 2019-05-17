package com.example.flori.android_multi_game.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flori.android_multi_game.MainActivity;
import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.manager.PlayerManager;
import com.example.flori.android_multi_game.model.Player;
import com.example.flori.android_multi_game.utils.GameUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PlayerScoreAdapter extends RecyclerView.Adapter<PlayerScoreAdapter.ViewHolder> {


    private ArrayList<String> games;
    private ArrayList<Integer> scores;
    private String playerName;

    public PlayerScoreAdapter(ArrayList<Integer> scores, ArrayList<String> games, String name) {
        this.scores = scores;
        this.games = games;
        this.playerName = name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.fragment_settings_row, viewGroup, false);
        return new PlayerScoreAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final String game = games.get(position);
        final int score = scores.get(position);
        final String playerName = this.playerName;

        viewHolder.gameName.setText(game);
        viewHolder.scoreGame.setText(String.valueOf(score));
        viewHolder.playerName.setText(playerName);

    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView scoreGame;
        private TextView gameName;
        private TextView playerName;
        private LinearLayout scoreRow;

        ViewHolder(View itemView) {

            super(itemView);
            gameName = itemView.findViewById(R.id.settings_game_name);
            playerName = itemView.findViewById(R.id.show_player_name);
            scoreGame = itemView.findViewById(R.id.settings_score);
            scoreRow = itemView.findViewById(R.id.show_score);

        }
    }
}