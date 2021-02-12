package nicebank;

import java.util.Currency;
import java.util.Locale;

// My version - not used
public class MyMoney {
    private final float amount;
    private Locale locale;
    private final Currency currency;

    public MyMoney() {
        this.currency = Currency.getInstance(Locale.UK);
        this.amount = 0;
    }

    public MyMoney(float amount) {
        this.currency = Currency.getInstance(Locale.UK);
        this.amount = amount;
    }

    public MyMoney(Locale locale, float amount) {
        this.locale = locale;
        this.currency = Currency.getInstance(locale);
        this.amount = amount;
    }

    public MyMoney(String amountStr) {
        this.currency = Currency.getInstance(Locale.UK);
        //this.amount = Double.parseDouble(amountStr);
        this.amount = Float.parseFloat(amountStr);
    }

    public float getAmount() {
        return amount;
    }

    public Locale getLocale() {
        return locale;
    }

    public Currency getCurrency() {
        return currency;
    }
}
