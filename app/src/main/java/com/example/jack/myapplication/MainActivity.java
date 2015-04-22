package com.example.jack.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity {

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private double purchaseAmount = 0.0;
    private double downAmount = 0.0;

    private double intAmount = 0.0;

    private int customYear = 40;

    private TextView purchaseDisplayTextView;
    private TextView downPayDisplayTextView;
    private TextView interestDisplayTextView;

    private TextView tenTextView;
    private TextView twentyTextView;
    private TextView thirtyTextView;
    private TextView customPayTextView;

    private TextView customTextView;

    private double calcYears(int years) {
        double amt = (purchaseAmount-downAmount);
        double period = 1 + intAmount/12;
        double result = Math.pow(period,12*years);
        double pResult = result-1;
        double intCalc = amt * pResult;
        double monthlyAmt = amt/(12*years);
        return monthlyAmt + intCalc;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        purchaseDisplayTextView =
                (TextView) findViewById(R.id.purchaseDisplayTextView);
        downPayDisplayTextView =
                (TextView) findViewById(R.id.downPayDisplayTextView);
        interestDisplayTextView =
                (TextView) findViewById(R.id.interestDisplayTextView);

        tenTextView =
                (TextView) findViewById(R.id.tenTextView);
        twentyTextView =
                (TextView) findViewById(R.id.twentyTextView);
        thirtyTextView =
                (TextView) findViewById(R.id.thirtyTextView);
        customPayTextView =
                (TextView) findViewById(R.id.customPayTextView);

        customTextView =
                (TextView) findViewById(R.id.customTextView);


        purchaseDisplayTextView.setText(
                currencyFormat.format(purchaseAmount));
        downPayDisplayTextView.setText(
                currencyFormat.format(downAmount));
        interestDisplayTextView.setText(
                percentFormat.format(intAmount));
        updatePurchase();
        updateDown();
        updateInterest();
        updateCustom();


        EditText purchaseEditText =
                (EditText) findViewById(R.id.purchaseEditText);
        purchaseEditText.addTextChangedListener(purchaseEditTextWatcher);

        EditText downPayEditText =
        (EditText) findViewById(R.id.downPayEditText);
        downPayEditText.addTextChangedListener(downPayEditTextWatcher);

        EditText interestEditText =
        (EditText) findViewById(R.id.interestEditText);
        interestEditText.addTextChangedListener(interestEditTextWatcher);


        SeekBar customSeekBar =
                (SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }

    private void updatePurchase() {
        tenTextView.setText(currencyFormat.format(calcYears(10)));
        twentyTextView.setText(currencyFormat.format(calcYears(20)));
        thirtyTextView.setText(currencyFormat.format(calcYears(30)));
        customPayTextView.setText(currencyFormat.format(calcYears(customYear)));
    }
    private void updateDown() {
        tenTextView.setText(currencyFormat.format(calcYears(10)));
        twentyTextView.setText(currencyFormat.format(calcYears(20)));
        thirtyTextView.setText(currencyFormat.format(calcYears(30)));
        customPayTextView.setText(currencyFormat.format(calcYears(customYear)));
    }
    private void updateInterest() {
        tenTextView.setText(currencyFormat.format(calcYears(10)));
        twentyTextView.setText(currencyFormat.format(calcYears(20)));
        thirtyTextView.setText(currencyFormat.format(calcYears(30)));
        customPayTextView.setText(currencyFormat.format(calcYears(customYear)));
    }
    private void updateCustom() {
        customPayTextView.setText(currencyFormat.format(calcYears(customYear)));
        customTextView.setText(customYear + " Years");
    }

    private SeekBar.OnSeekBarChangeListener customSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    customYear = progress;
                    updateCustom();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private TextWatcher purchaseEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try
            {
                purchaseAmount = Double.parseDouble(s.toString()) / 100;
                updatePurchase();
            }
            catch (NumberFormatException e)
            {
                purchaseAmount = 0.0;
            }
            purchaseDisplayTextView.setText(currencyFormat.format(purchaseAmount));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher downPayEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try
            {
                downAmount = Double.parseDouble(s.toString()) / 100;
                updateDown();
            }
            catch (NumberFormatException e)
            {
                downAmount = 0.0;
            }
            downPayDisplayTextView.setText(currencyFormat.format(downAmount));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher interestEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try
            {
                intAmount = Double.parseDouble(s.toString())/100;
                updateInterest();
            }
            catch (NumberFormatException e)
            {
                intAmount = 0.0;
            }
            interestDisplayTextView.setText(percentFormat.format(intAmount));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


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
