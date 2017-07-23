package com.narcielitonlopes.conversorvalores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    private ViewHolder mViewHolder = new ViewHolder();

    private double moedaValue = 0d;
    private String symbolValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.editValorReal = (EditText) findViewById(R.id.edit_valor_real);
        this.mViewHolder.editOtherValues = (EditText) findViewById(R.id.edit_other_values);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_moedas);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.moedas, R.layout.support_simple_spinner_dropdown_item);
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        this.mViewHolder.editValorReal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!mViewHolder.editValorReal.getText().toString().equals("")) {
                    if (!hasFocus) {
                        Double realValue = Double.valueOf(mViewHolder.editValorReal.getText().toString());
                        mViewHolder.editOtherValues.setText(symbolValue + " " + String.format("%.2f", realValue / moedaValue));
                    }
                }
            }
        });

        this.mViewHolder.editOtherValues.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!mViewHolder.editOtherValues.getText().toString().equals("")) {
                    if (!hasFocus) {
                        Double otherValue = Double.valueOf(mViewHolder.editOtherValues.getText().toString());
                        mViewHolder.editValorReal.setText("R$" + " " + String.format("%.2f", otherValue * moedaValue));
                    }
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    moedaValue = 3.14d;
                    symbolValue = "$";
                }
                else {
                    moedaValue = 3.67d;
                    symbolValue = "€";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.cleanValues();
    }

    private void cleanValues(){
        this.mViewHolder.editValorReal.setText("");
        this.mViewHolder.editOtherValues.setText("");
    }

    private static class ViewHolder{
        EditText editValorReal;
        EditText editOtherValues;
    }
}
