package com.ml.secretmessage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFS="my prefs";
    private static final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateFields();
    }

    private void updateFields(){
        final SharedPreferences myPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String existingPassword=myPreferences.getString(KEY_PASSWORD,null);

        final TextView label = findViewById(R.id.label_password);
        Button submit = findViewById(R.id.submit);
        final EditText passwordField=findViewById(R.id.password);

        if(existingPassword==null){
            //the user has no password yet
            label.setText(getString(R.string.set_your_password));

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    @Override
                    public void onClick(View view){
                        String enteredPassword=passwordField.getText().toString();

                        myPreferences.edit()
                                .putString(KEY_PASSWORD,enteredPassword)
                                .commit();

                        passwordField.setText("");

                        updateFields();

                }
            }

            });
        } else {
            //the user has set a password
            label.setText(R.string.enter_password);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String enteredPassword=passwordField.getText().toString();
                    String savedPassword=myPreferences.getString(KEY_PASSWORD,null);

                    if(enteredPassword.equals(savedPassword)){
                        //go to next screen
                        Intent myIntent=new Intent(MainActivity.this,
                                SecretActivity.class);
                        startActivity(myIntent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                R.string.password_does_not_match,Toast.LENGTH_LONG).show();
                    }


                }
            });
        }
    }
}
