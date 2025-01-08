package com.example.centcalculate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button buttonCalculate;
    private TextView textViewResult;
    private EditText editTextTotalMarks, editTextObtainedMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        editTextTotalMarks = findViewById(R.id.editTextTotalMarks);
        editTextObtainedMarks = findViewById(R.id.editTextObtainedMarks);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove this toast or change its message
                // Toast.makeText(MainActivity.this, "Percentage displayed", Toast.LENGTH_SHORT).show();

                String total = editTextTotalMarks.getText().toString();
                String obtained = editTextObtainedMarks.getText().toString();

                if (total.isEmpty() || obtained.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double totalValue = Double.parseDouble(total);
                    double obtainedValue = Double.parseDouble(obtained);

                    // Log inputs
                    Log.d("Input", "Total Marks: " + totalValue);
                    Log.d("Input", "Obtained Marks: " + obtainedValue);

                    if (totalValue == 0) {
                        Toast.makeText(MainActivity.this, "Total value cannot be zero", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double percentage = (obtainedValue / totalValue) * 100;

                    // Log the calculated percentage
                    Log.d("Calculated Percentage", "Percentage: " + percentage);

                    textViewResult.setText(String.format("%.2f", percentage));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid input, please enter valid numbers", Toast.LENGTH_SHORT).show();
                    Log.e("Error", "Invalid input", e);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
