package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private EditText firstEditText = null;
    private EditText secondEditText = null;
    private EditText thirdEditText = null;
    private EditText fourthEditText = null;
    private Button set = null;

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.set_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                    ArrayList v = new ArrayList<>();
                    int first = Integer.parseInt(firstEditText.getText().toString());
                    int second = Integer.parseInt(secondEditText.getText().toString());
                    int third = Integer.parseInt(thirdEditText.getText().toString());
                    int fourth = Integer.parseInt(fourthEditText.getText().toString());
                    intent.putExtra("first", first);
                    intent.putExtra("second", second);
                    intent.putExtra("third", third);
                    intent.putExtra("fourth", fourth);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    int SERVICE_STOPPED = 0;
    int SERVICE_STARTED = 1;
    private int serviceStatus = SERVICE_STOPPED;
    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[message]", intent.getStringExtra("message"));


            if (serviceStatus == SERVICE_STARTED) {

                int first = random.nextInt(100);
                int second = random.nextInt(100);
                int third = random.nextInt(100);
                int fourth = random.nextInt(100);


                firstEditText.setText(String.valueOf(first));
                secondEditText.setText(String.valueOf(second));
                thirdEditText.setText(String.valueOf(third));
                fourthEditText.setText(String.valueOf(fourth));

            }
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }
    String[] actionTypes = { "1", "10", "11", "100"};
    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        firstEditText = findViewById(R.id.first_edit_text);
        secondEditText = findViewById(R.id.second_edit_text);
        thirdEditText = findViewById(R.id.third_edit_text);
        fourthEditText = findViewById(R.id.fourth_edit_text);

        set = findViewById(R.id.set_button);
        set.setOnClickListener(buttonClickListener);

        for (int index = 0; index < actionTypes.length; index++) {
            intentFilter.addAction(actionTypes[index]);
        }

        if (serviceStatus == SERVICE_STOPPED) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);

            int first = random.nextInt(100);
            int second = random.nextInt(100);
            int third = random.nextInt(100);
            int fourth = random.nextInt(100);
            intent.putExtra("first", first);
            intent.putExtra("second",  second);
            intent.putExtra("third",  third);
            intent.putExtra("fourth",  fourth);

            getApplicationContext().startService(intent);
            serviceStatus = SERVICE_STARTED;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("first", firstEditText.getText().toString());
        savedInstanceState.putString("second", secondEditText.getText().toString());
        savedInstanceState.putString("third", thirdEditText.getText().toString());
        savedInstanceState.putString("fourth", fourthEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("first")) {
            firstEditText.setText(savedInstanceState.getString("first"));
        } else {
            firstEditText.setText("");
        }
        if (savedInstanceState.containsKey("first")) {
            secondEditText.setText(savedInstanceState.getString("second"));
        } else {
            secondEditText.setText("");
        }
        if (savedInstanceState.containsKey("first")) {
            thirdEditText.setText(savedInstanceState.getString("third"));
        } else {
            thirdEditText.setText("");
        }
        if (savedInstanceState.containsKey("first")) {
            fourthEditText.setText(savedInstanceState.getString("fourth"));
        } else {
            fourthEditText.setText("");
        }
    }
}
