package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText ettasks;
    Button btnAdd;
    Button btnclear;
    Button btndel;
    ListView lvViewTask;
    ArrayAdapter adapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ettasks = findViewById(R.id.etTasks);
        btnAdd = findViewById(R.id.buttonAdd);
        btnclear = findViewById(R.id.buttonClear);
        lvViewTask = findViewById(R.id.ListViewTasks);
        btndel = findViewById(R.id.buttonDelete);
        spinner = findViewById(R.id.spinner);

        final ArrayList<String> todoList = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,todoList);
        lvViewTask.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        btndel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        ettasks.setText("");
                        ettasks.setHint("Type in a new task here");
                        break;

                    case 1:
                        btnAdd.setEnabled(false);
                        btndel.setEnabled(true);
                        ettasks.setText("");
                        ettasks.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ettasks.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Tasks", Toast.LENGTH_LONG).show();

                }else{
                String todo = ettasks.getText().toString();
                todoList.add(todo);
                adapter.notifyDataSetChanged();}
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!todoList.isEmpty()) {

                    if (ettasks.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Enter index", Toast.LENGTH_LONG).show();

                    }else {
                        int pos = Integer.parseInt(ettasks.getText().toString());
                        if (pos >= 0 && pos < todoList.size()) {
                            todoList.remove(pos);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                        Toast.makeText(MainActivity.this, "You don't have any tasks to delete", Toast.LENGTH_LONG).show();

                    }

                }

        });





    }
}
