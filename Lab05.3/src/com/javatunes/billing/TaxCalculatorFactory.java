package com.javatunes.billing;

public class TaxCalculatorFactory {

    public static TaxCalculator getTaxCalculator(Location location) {

        /* early return version, because we "return early," no need for the breaks
        switch (location) {
            case ONLINE:
                return new OnlineTax();
            case USA:
                return new USATax();
            case EUROPE:
                return new EuropeTax();
        }
        return null;
        */

        return switch (location) {
            case ONLINE -> new OnlineTax();
            case USA -> new USATax();
            case EUROPE -> new EuropeTax();
        };
    }
}