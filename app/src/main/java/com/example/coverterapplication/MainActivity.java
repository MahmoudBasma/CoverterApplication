package com.example.coverterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adding a drop list
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        List<String> currencies = new ArrayList<String>();
        currencies.add("");
        currencies.add("USD"); currencies.add("LBP");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //After choosing their option, the user will have a token with a message that the app is converting
        Toast.makeText(parent.getContext(), "converting to: " + item, Toast.LENGTH_SHORT).show();
        //Checking the selected option by the user
        if(item.equals("USD")) convertToUSD(view);
        else if(item.equals("LBP")) convertToLBP(view);
        

    }
    //Needed to iherit this function due to the implementation of the interface
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    //First Option
    public void convertToUSD(View view) {
        try{
        TextView LBPTxt = findViewById(R.id.numLBP);
        double x = Double.parseDouble(LBPTxt.getText().toString());
        TextView edtUSDTxt = findViewById(R.id.numUSD);
        String USDValue = "" + Math.round(x/22000 * 100.0) / 100.0;
        edtUSDTxt.setText(USDValue);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Error: Please enter a number", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Seond option
    public void convertToLBP(View view) {
        try {
            TextView USDTxt = findViewById(R.id.numUSD);
            double x = Double.parseDouble(USDTxt.getText().toString());
            TextView edtLBPTxt = findViewById(R.id.numLBP);
            String LBPValue = "" + Math.round(x*22000 * 100) / 100;
            edtLBPTxt.setText(LBPValue);

        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Error: Please enter a number", Toast.LENGTH_LONG);
            toast.show();
        }

    }

}