package nicebank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Money {
    private final int dollars;
    private final int cents;

    public Money() {
        this.dollars = 0;
        this.cents = 0;
    }

    public Money(int dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    public Money(String amount) {
        Pattern pattern = Pattern.compile("^[^\\d]*([\\d]+)\\.([\\d][\\d])$");
        Matcher matcher = pattern.matcher(amount);

        matcher.find();
        this.dollars = Integer.parseInt(matcher.group(1));
        this.cents = Integer.parseInt(matcher.group(2));
    }

    public int getDollars() {
        return dollars;
    }

    public int getCents() {
        return cents;
    }

    public Money add(Money amount){
        int newCents = cents + amount.getCents();
        int newDollars = dollars + amount.getDollars();

        if (newCents >= 100){
            newCents -= 100;
            newDollars++;
        }

        return new Money(newDollars, newCents);
    }

    public Money minus(Money amount){
        int newCents = cents - amount.getCents();
        int newDollars = dollars - amount.getDollars();

        if (newCents < 0){
            newCents += 100;
            newDollars--;
        }

        return new Money(newDollars, newCents);
    }

    @Override
    public boolean equals(Object other){
        boolean equal = false;

        if (other instanceof Money){
            Money otherMoney = (Money)other;
            equal = (this.getDollars() == otherMoney.getDollars()
                    && this.getCents() == otherMoney.getCents());
        }

        return equal;
    }

    @Override
    public String toString() {
        return String.format("$%01d.%02d", this.getDollars(), this.getCents());
    }
}
