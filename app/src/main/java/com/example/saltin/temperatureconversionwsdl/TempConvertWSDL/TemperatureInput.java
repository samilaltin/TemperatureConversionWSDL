package com.example.saltin.temperatureconversionwsdl.TempConvertWSDL;

import java.security.PublicKey;

/**
 * Created by saltin on 09.02.2017.
 */

public class TemperatureInput {

    private String fahrenheit;
    private String celsius;

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }
}
