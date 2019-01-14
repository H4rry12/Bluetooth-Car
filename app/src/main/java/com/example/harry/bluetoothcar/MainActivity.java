package com.example.harry.bluetoothcar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView_name;
    RadioButton radioButton_state;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_name = findViewById(R.id.textView_name);
        radioButton_state = findViewById(R.id.radioButton_state);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.GeneralPreferenceFragment.class.getName());
                startActivity(intent);
                return true;
            case R.id.action_toast:
                Toast.makeText(this, "Toast clicked :-)", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String name = preferences.getString("edit_text_preference_test", "Default value");
        textView_name.setText(name);

        boolean state = preferences.getBoolean("switch_preference_test", false);
        radioButton_state.setChecked(state);
    }

    public void onClickButtonTest(View v){
        Toast.makeText(this, "Clicked on Test button", Toast.LENGTH_SHORT).show();

        Map<String, ?> pref_keys = preferences.getAll();

        for (Map.Entry<String, ?> pref_key : pref_keys.entrySet()) {
            Log.d(this.getClass().getName() + "_test_pref", pref_key.getKey() + ": " + pref_key.getValue());
        }
    }
}
