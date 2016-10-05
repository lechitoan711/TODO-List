package vn.magik.todo.activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import vn.magik.todo.R;
import vn.magik.todo.bean.customize.TimePickerFragment;
import vn.magik.todo.bean.entity.Note;
import vn.magik.todo.sqlite.NoteSQLite;

public class AddNoteActivity extends AppCompatActivity {
    private Spinner spinnerStatus, spinnerLevel;
    private Button btnSetTime;
    private EditText inputTitle, inputText;
    private boolean edit;
    private int id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        spinnerStatus = (Spinner) this.findViewById(R.id.add_input_status);
        ArrayAdapter<CharSequence> adapterStt = ArrayAdapter.createFromResource(this, R.array.item_status, android.R.layout.simple_spinner_item);
        adapterStt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapterStt);

        spinnerLevel = (Spinner) this.findViewById(R.id.add_input_level);
        ArrayAdapter<CharSequence> adapterLv = ArrayAdapter.createFromResource(this, R.array.item_level, android.R.layout.simple_spinner_item);
        adapterLv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapterLv);

        btnSetTime = (Button) this.findViewById(R.id.btn_set_time);
        inputTitle = (EditText) this.findViewById(R.id.add_input_title);
        inputText = (EditText) this.findViewById(R.id.add_input_text);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("note")!=null){
            Note note =(Note) intent.getSerializableExtra("note");
            inputTitle.setText(note.getTitle());
            inputText.setText(note.getText());
            btnSetTime.setText(note.getTime());
            edit = intent.getBooleanExtra("update", false);
            id = note.getId();
//            spinnerStatus.setSelection();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_add_note, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save){
            saveAdd();
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.home){
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
    public void setTime(View view){
        DialogFragment newFragment = new TimePickerFragment(btnSetTime);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void saveAdd(){
        String title = inputTitle.getText().toString();
        String text = inputText.getText().toString();
        String time = btnSetTime.getText().toString();

        String level = spinnerLevel.getSelectedItem().toString();
        String status = spinnerStatus.getSelectedItem().toString();

        Note note = new Note(title, time, text, level, status);
        if (edit){
            note.setId(id);
            NoteSQLite.updateNote(this, note);
            edit=false;
            id=-1;
        } else {
            NoteSQLite.addNote(this, note);
        }


    }
}
