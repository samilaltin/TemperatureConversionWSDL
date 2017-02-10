package com.example.saltin.temperatureconversionwsdl.Fragments;

import android.content.Context;
import android.graphics.ImageFormat;
import android.inputmethodservice.InputMethodService;
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

import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
import android.widget.Toast;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by saltin on 08.02.2017.
 */

public class CelToFahFragment extends Fragment {

    private static EditText celsius;
    private static TextView fahrenheit;


    TemperatureInput tmpInput;

    public CelToFahFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_celcius_to_fahrenheit, container, false);


        Button btnConvert = (Button) rootView.findViewById(R.id.btnConvert);
        celsius = (EditText) rootView.findViewById(R.id.txtCelsius);
        fahrenheit = (TextView) rootView.findViewById(R.id.lblFahrenheit);

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
//                        resultCel = tempConvert.CelsiusToFahrenheit(celcius.getText().toString());
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                fahrenheit.setText(celcius.getText() + "°C is " + resultCel + "°F");
//                            }
//                        });
//                    }
//                });
//                thread.start();
//            }
//        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmpInput = new TemperatureInput();
                tmpInput.setCelsius(celsius.getText().toString());
                String errorCelsius = celsius.getText().toString();
                if (TextUtils.isEmpty(errorCelsius)) {
                    celsius.setError("The item cannot be empty.");
                    fahrenheit.setText("Data not entered");
                }
                CelsiusToFahrenheit();
            }
        });

        return rootView;
    }


    private void CelsiusToFahrenheit() {
        final TempConvert tc = new TempConvert(new IWsdl2CodeEvents() {
            @Override
            public void Wsdl2CodeStartedRequest() {

            }

            @Override
            public void Wsdl2CodeFinished(String methodName, Object resultCel) {

                fahrenheit.setText(celsius.getText() + "°C is " + resultCel + "°F");

            }

            @Override
            public void Wsdl2CodeFinishedWithException(Exception ex) {

            }

            @Override
            public void Wsdl2CodeEndedRequest() {

            }
        });
        try {
            tc.CelsiusToFahrenheitAsync(tmpInput.getCelsius());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
