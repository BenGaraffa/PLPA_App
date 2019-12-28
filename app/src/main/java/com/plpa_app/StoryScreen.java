package com.plpa_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.*;

public class StoryScreen extends AppCompatActivity {

    private StoryTree story;
    private TextView ValueBox, QuestionBox;
    private Button LeftButton, RightButton, MiddleButton, MenuButton, UndoButton;
    private View Layout;
    private Vibrator haptic;
    private boolean undoable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // Receives the passed values from the previous activity
        Bundle extras = getIntent().getExtras();
        undoable = extras.getBoolean("undoable");

        story = wizardsJourney();

        // Setting up the individual view components
        ValueBox = findViewById(R.id.ValueBox);
        QuestionBox = findViewById(R.id.QuestionBox);
        LeftButton = findViewById(R.id.LeftButton);
        RightButton = findViewById(R.id.RightButton);
        MiddleButton = findViewById(R.id.MiddleButton);
        MenuButton = findViewById(R.id.MenuButton);
        UndoButton = findViewById(R.id.undoButton);
        haptic = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        Layout = findViewById(R.id.StoryLayout);

        update();
    }

    // Setup method for the wizard story
    public StoryTree wizardsJourney() {
        StoryTree s = new StoryTree();

        // Setting up the story tree with the appropriate resource ids.
        s.fillNode(R.string.node1Value, R.string.node1Question,
                R.string.node1Decision1, R.string.node1Decision2);
        s.createLeft(R.string.node2Value, R.string.node2Question,
                R.string.node2Decision1, R.string.node2Decision2);

        s.moveLeft();

        s.createLeft(R.string.node3Value, R.string.node3Question,
                R.string.node3Decision1, R.string.node3Decision2);
        s.createRight(R.string.node6Value);

        s.moveLeft();

        s.createLeft(R.string.node4Value);
        s.createRight(R.string.node5Value, R.string.node5Question,
                R.string.node5Decision1, R.string.node5Decision2);

        s.moveRight();
        s.linkRight(R.string.node6Value);

        s.toRoot(); // Return the view to root after creation.

        return s;
    }

    // Update each component when the node changes.
    public void update() {
        ValueBox.setText(getResources().getString(story.getValue()));
        updateLayout();

        if (story.isLeaf()) {
            oneButton();
            QuestionBox.setText("");
        } else {
            twoButtons();
            QuestionBox.setText(getResources().getString(story.getQuestion()));
            LeftButton.setText(getResources().getString(story.getDecision1()));
            RightButton.setText(getResources().getString(story.getDecision2()));
        }

        if (undoable || story.isRoot())
            UndoButton.setVisibility(View.VISIBLE);
        else
            UndoButton.setVisibility(View.GONE);

        if (story.isRoot())
            UndoButton.setText(getString(R.string.menuButton));
        else
            UndoButton.setText(getString(R.string.undoButton));
    }

    public void leftButtonHandler(View view) {
        System.out.println(story.moveLeft());
        update();
        haptic.vibrate(50);
    }

    public void rightButtonHandler(View view) {
        System.out.println(story.moveRight());
        update();
        haptic.vibrate(50);
    }

    public void middleButtonHandler(View view) {
        story.toRoot();
        update();
        haptic.vibrate(50);
    }

    public void undoButtonHandler(View view) {
        if (story.isRoot()) {
            backToMenu(view);
        } else {
            story.moveBack();
            update();
            haptic.vibrate(50);
        }
    }

    public void backToMenu(View view) {
        finish();
        haptic.vibrate(50);
    }

    public void twoButtons() {
        MiddleButton.setVisibility(View.GONE);
        MenuButton.setVisibility(View.GONE);
        LeftButton.setVisibility(View.VISIBLE);
        RightButton.setVisibility(View.VISIBLE);
    }

    public void oneButton() {
        MiddleButton.setVisibility(View.VISIBLE);
        MenuButton.setVisibility(View.VISIBLE);
        LeftButton.setVisibility(View.GONE);
        RightButton.setVisibility(View.GONE);
    }

    public void updateLayout() {
        if (story.isLeaf())
            Layout.setBackgroundColor(getResources().getColor(R.color.leafNodeBg));
        else if (story.isRoot())
            Layout.setBackgroundColor(getResources().getColor(R.color.startNodeBg));
        else
            Layout.setBackgroundColor(getResources().getColor(R.color.branchNodeBg));
    }

}
