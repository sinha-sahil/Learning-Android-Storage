package com.example.learnStorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText keyInput;
    EditText valueInput;

    TextView savedData;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        keyInput = findViewById(R.id.keyInput);
        valueInput = findViewById(R.id.valueInput);
        savedData = findViewById(R.id.savedData);

        sp = getSharedPreferences("CAppData", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.FileStorage){
            startFilesStorage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData(View view){
        SharedPreferences.Editor client = sp.edit();
        String key = keyInput.getText().toString();
        String value =  valueInput.getText().toString();
        Toast.makeText(this, key + " : " + value, Toast.LENGTH_LONG).show();

        client.putString(key, value);
        client.commit();
    }

    public void fetchData(View view){
        String savedValue = sp.getString(keyInput.getText().toString(), "Not Found");
        valueInput.setText(savedValue);

        Map<String, ?> allValues = sp.getAll();

        String savedDataText = "";

        for(Map.Entry<String, ?> itemData : allValues.entrySet()){
            savedDataText += itemData.getKey() + ":: " + itemData.getValue() + "\n";
        }

        Toast.makeText(this, savedDataText, Toast.LENGTH_LONG).show();
        savedData.setText(savedDataText);

    }

    public void startFilesStorage(){
        Intent si = new Intent(this, FilesStorage.class);
        startActivity(si);
    }

}
