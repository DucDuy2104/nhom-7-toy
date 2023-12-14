package com.example.myapp;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyConvert {


    public static int converFromVNCurrencyToFloat(String formattedAmount) {
        String numericString = formattedAmount.replaceAll("[^\\d]", "");

        int amount = Integer.parseInt(numericString);
        return amount;
    }
}
