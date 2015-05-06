package com.xxample.anish.sqlapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText username, password, showuser;
    Button adduser;
    AnishDataBaseAdapter anishhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        showuser = (EditText) findViewById(R.id.editText3);

        anishhelper = new AnishDataBaseAdapter(this);


    }

    public void addUser(View view) {
        //Toast.makeText(getBaseContext(),"Button Clicked",Toast.LENGTH_LONG).show();

        String user = username.getText().toString();
        String pass = password.getText().toString();

        long id = anishhelper.insertData(user, pass);
        if (id < 0) {

            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Successfully inserted a Row", Toast.LENGTH_LONG).show();
        }


    }

    public void getData(View view) {

        String data = anishhelper.getData();
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();

    }

    public void showUser(View view) {

        String usernaam = showuser.getText().toString();
        String sub1 = usernaam.substring(0, usernaam.indexOf(" "));
        String sub2 = usernaam.substring(usernaam.indexOf(" ") + 1);
        String receive = anishhelper.gettheData(sub1, sub2);
        Toast.makeText(this, receive, Toast.LENGTH_LONG).show();

    }

    public void update(View view) {

        anishhelper.updateName("anish", "Vivek");
        Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();

    }

    public void delete(View view) {

        anishhelper.deleteRow();
        Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();


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

        return super.onOptionsItemSelected(item);
    }
}
