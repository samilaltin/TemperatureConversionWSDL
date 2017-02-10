package com.example.saltin.temperatureconversionwsdl.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saltin.temperatureconversionwsdl.R;
import com.example.saltin.temperatureconversionwsdl.TempConvertWSDL.IWsdl2CodeEvents;
import com.example.saltin.temperatureconversionwsdl.TempConvertWSDL.TempConvert;
import com.example.saltin.temperatureconversionwsdl.TempConvertWSDL.TemperatureInput;


/**
 * Created by saltin on 08.02.2017.
 */

public class FahToCelFragment extends Fragment {

    private static EditText fahrenheit;
    private static TextView celsius;


    TemperatureInput tmpInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fahrenheit_to_celcius, container, false);


        Button btnConvert = (Button) rootView.findViewById(R.id.btnConvert);
        fahrenheit = (EditText) rootView.findViewById(R.id.txtFahrenheit);
        celsius = (TextView) rootView.findViewById(R.id.lblCelsius);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                return false;
            }
        });


//        btnConvert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tempConvert = new TempConvert();
//                        resultFah = tempConvert.FahrenheitToCelsius(fahrenheit.getText().toString());
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                celcius.setText(fahrenheit.getText() + "°F is " + resultFah + "°C");
//                            }
//                        });
//                    }
//
//                });
//                thread.start();
//            }
//        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmpInput = new TemperatureInput();
                tmpInput.setFahrenheit(fahrenheit.getText().toString());
                String errorFahrenheit = fahrenheit.getText().toString();
                if (TextUtils.isEmpty(errorFahrenheit)) {
                    fahrenheit.setError("The item cannot be empty.");
                    celsius.setText("Data not entered");
                }
                FahrenheitToCelsius();
            }
        });

        return rootView;
    }

    private void FahrenheitToCelsius() {
        final TempConvert tc = new TempConvert(new IWsdl2CodeEvents() {
            @Override
            public void Wsdl2CodeStartedRequest() {

            }

            @Override
            public void Wsdl2CodeFinished(String methodName, Object resultFah) {

                celsius.setText(fahrenheit.getText() + "°F is " + resultFah + "°C");
            }

            @Override
            public void Wsdl2CodeFinishedWithException(Exception ex) {

            }

            @Override
            public void Wsdl2CodeEndedRequest() {

            }
        });

        try {
            tc.FahrenheitToCelsiusAsync(tmpInput.getFahrenheit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
