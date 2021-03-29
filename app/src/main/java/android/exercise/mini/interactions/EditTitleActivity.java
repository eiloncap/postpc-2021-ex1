package android.exercise.mini.interactions;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EditTitleActivity extends AppCompatActivity {

    private static final String inputString = "Page title here";
    private boolean isEditing = false;

    FloatingActionButton fabStartEdit, fabEditDone;
    TextView textViewTitle;
    EditText editTextTitle;

    // TODO:
    //  you can add fields to this class. those fields will be accessibly inside any method
    //  (like `onCreate()` and `onBackPressed()` methods)
    // for any field, make sure to set it's initial value. You CAN'T write a custom constructor
    // for example, you can add this field:
    // `private boolean isEditing = false;`
    // in onCreate() set `this.isEditing` to `true` once the user starts editing, set to `false` once done editing
    // in onBackPressed() check `if(this.isEditing)` to understand what to do

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
      /*
      TODO:
      1. animate out the "start edit" FAB
      2. animate in the "done edit" FAB
      3. hide the static title (text-view)
      4. show the editable title (edit-text)
      5. make sure the editable title's text is the same as the static one
      6. optional (HARD!) make the keyboard to open with the edit-text focused,
          so the user can start typing without the need another click on the edit-text

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */

            // animate buttons
            switchFabs(fabEditDone, fabStartEdit);

            // update texts
            textViewTitle.setVisibility(View.GONE);
            editTextTitle.setVisibility(View.VISIBLE);
            editTextTitle.setEnabled(true);
            isEditing = true;

        });

        // handle clicks on "done edit"
        fabEditDone.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "done edit" FAB
      2. animate in the "start edit" FAB
      3. take the text from the user's input in the edit-text and put it inside the static text-view
      4. show the static title (text-view)
      5. hide the editable title (edit-text)
      6. make sure that the keyboard is closed

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
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
    /*
    TODO:
    if user is now editing, tap on BACK will revert the edit. do the following:
    1. hide the edit-text
    2. show the static text-view with previous text (discard user's input)
    3. animate out the "done-edit" FAB
    4. animate in the "start-edit" FAB

    else, the user isn't editing. continue normal BACK tap behavior to exit the screen.
    call `super.onBackPressed()`

    notice:
    to work with views, you will need to find them first.
    to find views call `findViewById()` in a same way like in `onCreate()`
     */
        if (isEditing) {
            fabEditDone.callOnClick();
            editTextTitle.setText(inputString);
            textViewTitle.setText(inputString);
        } else {
            fabStartEdit.setAlpha(1f);
            fabEditDone.setAlpha(1f);
            super.onBackPressed();
        }
    }

}