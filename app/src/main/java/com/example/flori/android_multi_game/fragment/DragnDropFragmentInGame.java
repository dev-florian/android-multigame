package com.example.flori.android_multi_game.fragment;

import android.annotation.TargetApi;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.example.flori.android_multi_game.EndGameActivity;
import com.example.flori.android_multi_game.MainActivity;
import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.utils.TouchListener;
import com.example.flori.android_multi_game.utils.GameUtils;
import java.util.Objects;
import java.util.Random;

public class DragnDropFragmentInGame extends Fragment implements View.OnDragListener {
    private int scoreTotal = 0;
    private TextView score;
    private View round;
    private View rectangletop1;
    private View rectangletop2;
    private View rectanglemiddle1;
    private View rectanglemiddle3;
    private View rectanglebottom1;
    private View rectanglebottom2;
    private View currentView;
    int widthContent;
    int heightContent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) Objects.requireNonNull(getActivity())).viewPager.setPagingEnabled(false);
        final View view = inflater.inflate(R.layout.fragment_drag_n_drop, container, false);
        score = view.findViewById(R.id.fragment_fasttap_score);
        round = view.findViewById(R.id.roundToDrag);

        TouchListener touchListener = new TouchListener();
        round.setOnTouchListener(touchListener);


        rectangletop1 = view.findViewById(R.id.rectangle_top1);
        rectangletop2 = view.findViewById(R.id.rectangle_top2);
        rectanglemiddle1 = view.findViewById(R.id.rectangle_middle1);
        rectanglemiddle3 = view.findViewById(R.id.rectangle_middle3);
        rectanglebottom1 = view.findViewById(R.id.rectangle_bottom1);
        rectanglebottom2 = view.findViewById(R.id.rectangle_bottom2);

        rectangletop1.setOnDragListener(this);
        rectangletop2.setOnDragListener(this);
        rectanglemiddle1.setOnDragListener(this);
        rectanglemiddle3.setOnDragListener(this);
        rectanglebottom1.setOnDragListener(this);
        rectanglebottom2.setOnDragListener(this);


        generate();
        startTimer(view);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                int containerWidth = container != null ? container.getWidth() : 0;
                int containerHeight = container != null ? container.getHeight() : 0;
                int viewHeightTop = rectangletop1.getHeight();
                int viewHeightBottom = rectanglebottom1.getHeight();
                int viewWidthleft = rectanglemiddle1.getWidth();
                int viewWidthtright = rectanglemiddle3.getWidth();
                int viewHeightround = round.getHeight();

                widthContent = containerWidth - viewWidthleft - viewWidthtright - viewHeightround;
                heightContent = containerHeight - viewHeightTop - viewHeightBottom - viewHeightround;

            }
        });

        return view;
    }

    public void startTimer(View view) {

        final TextView timer = view.findViewById(R.id.fragment_fasttap_time);

        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Temps restant : " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                Intent intent = new Intent(getActivity(), EndGameActivity.class);
                intent.putExtra("SCORE", scoreTotal);
                GameUtils.launchView((AppCompatActivity) Objects.requireNonNull(getActivity()), intent, false);
                if (DragnDropFragmentInGame.this.getFragmentManager() != null) {
                    DragnDropFragmentInGame.this.getFragmentManager().popBackStack();
                }
                ((MainActivity) getActivity()).viewPager.setPagingEnabled(true);
            }
        }.start();
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onDrag(View v, DragEvent event) {
        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:

                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // applies a blue color tint to the View to indicate that it can accept the data
//                    v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();
                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:


                // Applies a YELLOW or any color tint to the View. Return true; the return value is ignored.
                v.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;


            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;


            case DragEvent.ACTION_DRAG_EXITED:

                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.
                v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                v.getBackground().clearColorFilter();

                // Invalidate the view to force a redraw in the new tint
                v.invalidate();

//                ViewGroup owner = (ViewGroup) vw.getParent();
//                owner.removeView(vw); //remove the dragged view
                //caste the view into LinearLayout as our drag acceptable layout is LinearLayout

                //round.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                return true;
            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                // Gets the text data from the item.

                // Displays a message containing the dragged data.
//                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                // Turns off any color tints
                v.getBackground().clearColorFilter();


//                ViewGroup owner = (ViewGroup) vw.getParent();
//                owner.removeView(vw); //remove the dragged view
                //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                round.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
                round.getSolidColor();

                if (v == rectangletop1 && currentView == rectangletop1) {
                    addScore();
                    generate();
                    newRoundPosition(round);
                } else if (v == rectangletop2 && currentView == rectangletop2) {
                    addScore();
                    generate();
                    newRoundPosition(round);
                } else if (v == rectanglemiddle1 && currentView == rectanglemiddle1) {
                    addScore();
                    generate();
                    newRoundPosition(round);
                } else if (v == rectanglemiddle3 && currentView == rectanglemiddle3) {
                    addScore();
                    generate();
                    newRoundPosition(round);
                } else if (v == rectanglebottom1 && currentView == rectanglebottom1) {
                    addScore();
                    generate();
                    newRoundPosition(round);
                } else if (v == rectanglebottom2 && currentView == rectanglebottom2) {
                    addScore();
                    generate();
                    newRoundPosition(round);
                }

                // Invalidates the view to force a redraw
                v.invalidate();
                // Returns true. DragEvent.getResult() will return true.
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();

                round.setVisibility(View.VISIBLE);
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    public void addScore() {
        scoreTotal = scoreTotal + 1;
        score.setText("Score : " + scoreTotal);
    }

    public void generate() {
        int number = getRandomNumber(0, 5);

        if (number == 0) {
            currentView = rectangletop1;
            round.getBackground().setTint(currentView.getResources().getColor(R.color.colorGray));
        }
        if (number == 1) {
            currentView = rectangletop2;
            round.getBackground().setTint(currentView.getResources().getColor(R.color.colorBrown));
        }
        if (number == 2) {
            currentView = rectanglemiddle1;
            round.getBackground().setTint(currentView.getResources().getColor(R.color.colorBlue));
        }
        if (number == 3) {
            currentView = rectanglemiddle3;
            round.getBackground().setTint(currentView.getResources().getColor(R.color.colorGreen));
        }
        if (number == 4) {
            currentView = rectanglebottom1;
            round.getBackground().setTint(currentView.getResources().getColor(R.color.colorViolet));
        }
        if (number == 5) {
            currentView = rectanglebottom2;
            round.getBackground().setTint(currentView.getResources().getColor(R.color.colorRed));
        }
    }

    public int getRandomNumber(int first, int second) {
        Random random = new Random();
        return random.nextInt(second - first + 1) + first;
    }

    public void newRoundPosition(View view) {
        int X = getRandomNumber(-(widthContent / 2), widthContent / 2);
        int Y = getRandomNumber(-(heightContent / 2), heightContent / 2);
        view.setTranslationX(X);
        view.setTranslationY(Y);

    }


}