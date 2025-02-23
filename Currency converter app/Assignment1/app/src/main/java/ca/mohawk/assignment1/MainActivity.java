package ca.mohawk.assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity handles the currency conversion functionality.
 * It allows users to convert between CAD and other currencies,
 * swap source and destination currencies, and navigate to settings.
 *
 * I, Agam Singh, certify that this material is my original work.
 * No other person's work has been used without due acknowledgement.
 */

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewSource, imageViewDestination;
    private EditText editTextInput;
    private TextView tvResult;
    private Button btnConvert, btnReconfigure, btnSwap;
    private SharedPreferences prefs;
    private String[] rates;
    private int[] flagImages = {
            R.drawable.us,
            R.drawable.thailand,
            R.drawable.india,
            R.drawable.japan,
            R.drawable.pakistan
    };

    // Default conversion rate (CAD to USD)
    private double conversionRate = 0.69;
    private boolean isCadToOther = true; // True = CAD → Other, False = Other → CAD

    /**
     * This Function is called when the activity is first created.
     * Initializes UI components and loads preferences.
     *
     * @param savedInstanceState Saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Components
        imageViewSource = findViewById(R.id.imageViewSource);
        imageViewDestination = findViewById(R.id.imageViewDestination);
        editTextInput = findViewById(R.id.editTextInput);
        tvResult = findViewById(R.id.tvResult);
        btnConvert = findViewById(R.id.btnConvert);
        btnReconfigure = findViewById(R.id.btnReconfigure);
        btnSwap = findViewById(R.id.btnSwap);

        // Load arrays from strings.xml
        rates = getResources().getStringArray(R.array.conversion_rates);

        prefs = getSharedPreferences("CurrencyPrefs", Context.MODE_PRIVATE);

        // Set default value in EditText
        editTextInput.setText("1.0");

        loadPreferences();

        // Set Click Listeners
        btnConvert.setOnClickListener(this::convertCurrency);
        btnReconfigure.setOnClickListener(this::openSettings);
        btnSwap.setOnClickListener(this::swapCountries);
    }

    /**
     * This function reloads the user prefernces when the
     * activity resumes.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
    }

    /**
     * This function converts the entered currency amount based on the selected conversion rate
     *
     * @param view The button that triggered this method
     */
    public void convertCurrency(View view) {
        String input = editTextInput.getText().toString();
        if (input.isEmpty()) {
            editTextInput.setText("1.0");
            input = "1.0";
        }
        double amount = Double.parseDouble(input);
        double result;

        if (isCadToOther) {
            result = amount * conversionRate; // CAD → Other
        } else {
            result = amount / conversionRate; // Other → CAD
        }
        tvResult.setText(String.format("%.2f", result));
    }

    /**
     * This function navigates the user to the settings screen
     * @param view The button that triggered this method
     */
    public void openSettings(View view) {
        // Reset to default (Canada as source)
        if (!isCadToOther) {
            swapCountries(view);
        }

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    /**
     * This function loads the saved currency preference
     */
    private void loadPreferences() {
        int savedIndex = prefs.getInt("currency_index", 0);
        conversionRate = Double.parseDouble(rates[savedIndex]);
        imageViewDestination.setImageResource(flagImages[savedIndex]); // Update flag

        // Display conversion rate on button
        btnConvert.setText("@ " + conversionRate + " =");
    }

    /**
     * This function swaps the countries, updates all the UI elements connected to those and also
     * recalculates the conversion rate.
     * @param view The button that triggered this method
     */
    public void swapCountries(View view) {
        isCadToOther = !isCadToOther;

        // Update button text
        if (isCadToOther) {
            btnConvert.setText("@ " + conversionRate); // CAD → Other
        } else {
            btnConvert.setText("@ " + String.format("%.2f", 1 / conversionRate)); // Other → CAD
        }

        //Clear the result textview
        tvResult.setText("");

        // Swap flags visually
        int selectedCurrencyIndex = prefs.getInt("currency_index", 0);
        int selectedFlag = flagImages[selectedCurrencyIndex];

        if (isCadToOther) {
            imageViewSource.setImageResource(R.drawable.canada);
            imageViewDestination.setImageResource(selectedFlag);
        } else {
            imageViewSource.setImageResource(selectedFlag);
            imageViewDestination.setImageResource(R.drawable.canada);
        }
    }
}