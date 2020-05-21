package ro.pub.cs.systems.eim.practicaltest02;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ro.pub.cs.systems.eim.practicaltest02.general.Constants;
import ro.pub.cs.systems.eim.practicaltest02.network.ClientThread;
import ro.pub.cs.systems.eim.practicaltest02.network.ServerThread;

public class PracticalTest02MainActivity  extends AppCompatActivity {

    // set alarm
    private EditText hourText = null;
    private EditText minuteText = null;
    private Button setButton = null;
    private Button resetButton = null;
    private Button pollButton = null;
    private TextView alarmTextView = null;

    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private setButtonButtonClickListener setButtonClickListener = new setButtonButtonClickListener();
    private class setButtonButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = "13";
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();

            String hour = hourText.getText().toString();
            String minute = minuteText.getText().toString();
            if (hourText == null || hourText.isEmpty()
                    || minuteText == null || minuteText.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client connection parameters should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }

    private resetButtonClickListener resetButtonClickListener = new resetButtonClickListener();
    private class resetButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {



        }

    }

    private pollButtonClickListener pollButtonClickListener = new pollButtonClickListener();
    private class pollButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {



        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onCreate() callback method has been invoked");
        setContentView(R.layout.activity_practical_test02_main);

        hourText = (EditText)findViewById(R.id.editText);
        setButton = (Button)findViewById(R.id.button);
        setButton.setOnClickListener(setButtonButtonClickListener);

        minuteText = (EditText)findViewById(R.id.editText2);
        resetButton = (Button)findViewById(R.id.button2);
        resetButton.setOnClickListener(resetButtonClickListener);
        pollButton = (Button)findViewById(R.id.button3);
        pollButton.setOnClickListener(pollButtonClickListener);
        alarmTextView = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy() {
        Log.i(Constants.TAG, "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }

}