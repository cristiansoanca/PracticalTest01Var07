package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {
    private EditText firstEditText = null;
    private EditText secondEditText = null;
    private EditText thirdEditText = null;
    private EditText fourthEditText = null;
    private Button sumButton = null;
    private Button productButton = null;

    void toast(String input) {
        Toast.makeText(this, input, Toast.LENGTH_LONG).show();
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int num1 = Integer.parseInt(firstEditText.getText().toString());
            int num2 = Integer.parseInt(secondEditText.getText().toString());
            int num3 = Integer.parseInt(thirdEditText.getText().toString());
            int num4 = Integer.parseInt(fourthEditText.getText().toString());

            switch(view.getId()) {
                case R.id.sum_button:
                    int sum = num1 + num2 + num3 + num4;
                    toast(String.valueOf(sum));

                    break;
                case R.id.product_button:
                    int prod = num1 * num2 * num3 * num4;
                    toast(String.valueOf(prod));
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        firstEditText = findViewById(R.id.first_edit_text);
        secondEditText = findViewById(R.id.second_edit_text);
        thirdEditText = findViewById(R.id.third_edit_text);
        fourthEditText = findViewById(R.id.fourth_edit_text);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("first")) {
            int first = intent.getIntExtra("first", -1);
            firstEditText.setText(String.valueOf(first));
        }

        if (intent != null && intent.getExtras().containsKey("second")) {
            int first = intent.getIntExtra("second", -1);
            secondEditText.setText(String.valueOf(first));
        }

        if (intent != null && intent.getExtras().containsKey("third")) {
            int first = intent.getIntExtra("third", -1);
            thirdEditText.setText(String.valueOf(first));
        }

        if (intent != null && intent.getExtras().containsKey("fourth")) {
            int first = intent.getIntExtra("fourth", -1);
            fourthEditText.setText(String.valueOf(first));
        }

        sumButton = findViewById(R.id.sum_button);
        sumButton.setOnClickListener(buttonClickListener);
        productButton = findViewById(R.id.product_button);
        productButton.setOnClickListener(buttonClickListener);
    }


}
