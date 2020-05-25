package com.example.secretmessages;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    //set up variables for our GUI

    EditText txtIn;
    EditText txtOut;
    EditText txtValue;
    SeekBar sb;
    Button btnCode;

    public String encode(String message, int k) {

        String out ="";
        char key = (char) k;
        for (int x = 0; x < message.length();x++){
            char in = message.charAt(x);
            if (in >= 'A' && in <= 'Z'){
                in += key;
                if (in > 'Z')
                    in-= 26;
                if (in<'A')
                    in+=26;
            }if (in >= 'a' && in <= 'z'){
                in += key;
                if (in > 'z')
                    in-= 26;
                if (in<'a')
                    in+=26;
            }
            if (in >= '0' && in<= '9') {
                in+= (k % 10);
                if (in >'9');
                    in-= 10;
                if (in <'0')
                    in += 10;
            }
            out += in;
        }

        return out;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtIn = findViewById(R.id.txtIn);
        txtOut = findViewById(R.id.txtOut);
        txtValue = findViewById(R.id.txtValue);
        sb = findViewById(R.id.sb);
        btnCode = findViewById(R.id.btnCode);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int key = progress - 13;
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                txtOut.setText(out);
                txtValue.setText("" + key);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = txtIn.getText().toString();
                int key = Integer.parseInt(txtValue.getText().toString());
                int progress = key +13;
                String out = encode(message,key);
                txtOut.setText(out);
                sb.setProgress(progress);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
