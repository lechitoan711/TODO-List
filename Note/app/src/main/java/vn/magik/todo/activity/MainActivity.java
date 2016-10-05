package vn.magik.todo.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import vn.magik.todo.R;
import vn.magik.todo.adapter.AdapterHome;
import vn.magik.todo.bean.entity.Note;
import vn.magik.todo.sqlite.NoteSQLite;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Note> listData;
    private AdapterHome adapterHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.home_list_view);


        listData = NoteSQLite.getListNote(this);
        Log.e("list", listData.toString());
        adapterHome = new AdapterHome(this, listData);
        listView.setAdapter(adapterHome);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Note note = listData.get(position);
                showDialog(note);
            }

        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_new){
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.d("test: ", "onResume");
        if (adapterHome !=null){
            Log.d("test: 1", "onResume");
            listData = NoteSQLite.getListNote(this);
            adapterHome.updateList(listData);
        }
    }
    private void showDialog(final Note note){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);

        TextView title = (TextView) dialog.findViewById(R.id.view_title);
        title.setText(note.getTitle());

        TextView status = (TextView) dialog.findViewById(R.id.view_status);
        status.setText("Status: " + note.getStatus());

        TextView time = (TextView) dialog.findViewById(R.id.view_time);
        time.setText("Time: " + note.getTime());

        TextView text = (TextView) dialog.findViewById(R.id.view_text);
        text.setText(note.getText());

        Button btnEdit = (Button) dialog.findViewById(R.id.view_btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddNoteActivity.class);
                intent.putExtra("note", note);
                intent.putExtra("update", true);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button btnRemove = (Button) dialog.findViewById(R.id.view_btn_remove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteSQLite.removeNote(getBaseContext(), note.getId());
                listData = NoteSQLite.getListNote(getBaseContext());
                adapterHome.updateList(listData);
                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
