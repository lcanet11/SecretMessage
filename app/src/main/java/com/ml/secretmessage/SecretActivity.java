package com.ml.secretmessage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lucic on 12/7/2017.
 */

public class SecretActivity extends AppCompatActivity{
    private static final String KEY_SECRET="secret";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        updateSecret();
    }

    private void updateSecret(){
        final EditText secretText= (EditText) findViewById(R.id.secret_text);
        Button update= (Button) findViewById(R.id.submit);

        final SharedPreferences myPrefs=getSharedPreferences(MainActivity.SHARED_PREFS,MODE_PRIVATE);
        String secretString=myPrefs.getString(KEY_SECRET, null);

        secretText.setText(secretString);

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //save whatever string is in the EditText
                String updatedSecret=secretText.getText().toString();

                myPrefs.edit()
                    .putString(KEY_SECRET,updatedSecret)
                    .commit();

                Toast.makeText(SecretActivity.this, "Updated Secret", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
