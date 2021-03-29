package android.exercise.mini.interactions;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EditTitleActivity extends AppCompatActivity {

    private static final String inputString = "Page title here";
    private boolean isEditing = false;

    FloatingActionButton fabStartEdit, fabEditDone;
    TextView textViewTitle;
    EditText editTextTitle;

    private void switchFabs(FloatingActionButton toView, FloatingActionButton toUnview) {
        // view toUnview button (rotation animation)
        toUnview.animate()
                .alpha(0f)
                .rotation(360f)
                .setDuration(400L)
                .withEndAction(() -> {
                    toUnview.setVisibility(View.GONE);
                    toUnview.setRotation(0f);
                })
                .start();

        // view toView button (scale animation)
        toView.setScaleX(0.5f);
        toView.setScaleY(0.5f);
        toView.setVisibility(View.VISIBLE);
        toView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400L)
                .start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_title);

        // find all views
        fabStartEdit = findViewById(R.id.fab_start_edit);
        fabEditDone = findViewById(R.id.fab_edit_done);
        textViewTitle = findViewById(R.id.textViewPageTitle);
        editTextTitle = findViewById(R.id.editTextPageTitle);

        // setup - start from static title with "edit" button
        fabStartEdit.setVisibility(View.VISIBLE);
        fabEditDone.setVisibility(View.GONE);
        textViewTitle.setText(inputString);
        textViewTitle.setVisibility(View.VISIBLE);
        editTextTitle.setText(inputString);
        editTextTitle.setVisibility(View.GONE);


        // handle clicks on "start edit"
        fabStartEdit.setOnClickListener(v -> {

            // animate buttons
            switchFabs(fabEditDone, fabStartEdit);

            // update texts
            textViewTitle.setVisibility(View.GONE);
            editTextTitle.setVisibility(View.VISIBLE);
            editTextTitle.setEnabled(true);
            isEditing = true;

            // show keyBoard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        });

        // handle clicks on "done edit"
        fabEditDone.setOnClickListener(v -> {
            // animate buttons
            switchFabs(fabStartEdit, fabEditDone);

            // update texts
            textViewTitle.setText(editTextTitle.getText());
            textViewTitle.setVisibility(View.VISIBLE);
            editTextTitle.setVisibility(View.GONE);
            editTextTitle.setEnabled(false);
            isEditing = false;
        });
    }

    @Override
    public void onBackPressed() {
        // BACK button was clicked

        if (isEditing) {
            editTextTitle.setText(inputString);
            fabEditDone.callOnClick();
        } else {
            fabStartEdit.setAlpha(1f);
            fabEditDone.setAlpha(1f);
            super.onBackPressed();
        }
    }

}