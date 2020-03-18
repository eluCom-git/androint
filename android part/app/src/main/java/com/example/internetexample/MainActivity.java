package com.example.internetexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textviewer);
    }
    //
    public void serverReply(String ret){
        textView.setText(ret);
    }
    // Internet Loader class
    /*
        This class is important because it separate Main UI from activity that load for long time
        using Async Task class. Async Task class load and update the main interface.
     */

    class Loader extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("http://put-address-here/myscript/welcome.php"); //the address is system IP
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                /*
                 you can add more options;
                    - wait time
                    - method - post, get
                    -
                 */

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String result = bufferedReader.readLine();
                bufferedReader.close();
                //
                return result;
            } catch (IOException e) {
                return e.getMessage();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            serverReply(s);
        }
    }
}
