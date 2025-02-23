package ca.mohawk.assignment1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class represents the second activity where users can select a currency
 * for conversion. It allows users to save their currency preference and provides
 * a help dialog with application usage instructions.
 *
 * I, Agam Singh, certify that this material is my original work.
 * No other person's work has been used without due acknowledgement.
 */

public class MainActivity2 extends AppCompatActivity {

    private Spinner spinnerCurrency;
    private TextView tvValue;
    private Button btnSave, btnHelp;
    private SharedPreferences prefs;
    private String[] currencies;
    private String[] rates;

    /**
     * This Function is called when the activity is first created.
     * Initializes UI components and loads preferences.
     *
     * @param savedInstanceState Saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize UI Components
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        tvValue = findViewById(R.id.tvValue);
        btnSave = findViewById(R.id.btnSave);
        btnHelp = findViewById(R.id.btnHelp);

        // Load arrays from strings.xml
        currencies = getResources().getStringArray(R.array.currencies);
        rates = getResources().getStringArray(R.array.conversion_rates);

        prefs = getSharedPreferences("CurrencyPrefs", Context.MODE_PRIVATE);

        // Set up Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencies);
        spinnerCurrency.setAdapter(adapter);

        loadPreferences();

        // Set Click and selected Listeners
        spinnerCurrency.setOnItemSelectedListener(createItemSelectedListener());
        btnSave.setOnClickListener(this::saveCurrencySelection);
        btnHelp.setOnClickListener(this::showHelp);
    }

    /**
     * This method saves the selected currency to SharedPreferences and closes the activity.
     *
     * @param view The button that triggered the event.
     */
    public void saveCurrencySelection(View view) {
        int selectedPosition = spinnerCurrency.getSelectedItemPosition();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("currency_index", selectedPosition);
        editor.apply();
        finish();
    }

    /**
     * This function loads the saved currency preference
     */
    private void loadPreferences() {
        int savedIndex = prefs.getInt("currency_index", 0);
        spinnerCurrency.setSelection(savedIndex);
        tvValue.setText(rates[savedIndex]);
    }

    /**
     * This creates an item selected listener for the currency spinner and updates the
     * currency rate dynamically on selected item changes.
     *
     * @return An instance of OnItemSelectedListener.
     */
    private AdapterView.OnItemSelectedListener createItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvValue.setText(rates[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    /**
     * This displays a help dialog explaining the app's features and usage.
     *
     * @param view The button that triggered the event.
     */

    // Took this part from Stack Overflow
    // "https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android"
    public void showHelp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help & Features")
                .setMessage("**Bi-Directional Conversion**\n" +
                        "You can now convert both:\n" +
                        "✔ **CAD → Selected Currency**\n" +
                        "✔ **Selected Currency → CAD**\n\n" +

                        "**How to Swap Conversion Direction**\n" +
                        "- Tap the Switch button to switch between conversion modes.\n" +
                        "- The button text updates to show the new rate.\n" +
                        "- Flags also swap to indicate the direction.\n\n" +

                        "**Reset to Default (Canada as Source)**\n" +
                        "- Opening Settings will reset conversion to \"CAD → Selected Currency\".\n" +
                        "- This ensures Canada is always the source when returning.\n\n" +


                        "Thank you for using our Currency Converter!!!")
                .setPositiveButton("OK", null)
                .show();
    }

}