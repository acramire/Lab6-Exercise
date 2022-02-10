package edu.ucsd.cse110.lab6_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);

        //String name = prefs.getString("name", "Name not yet ready");
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("name");
        editor.apply();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartClicked(View view) {
        NameService.startActionName(this, "Kevin Shi");
    }

    public void onGetNameClicked(View view) {
        SharedPreferences prefs = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);

        TextView textView = findViewById(R.id.text_view);
        textView.setText(prefs.getString("name","Name not yet ready"));
    }
}