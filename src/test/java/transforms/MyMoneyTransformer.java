package transforms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyMoneyTransformer {
    private int pounds;
    private int pence;
    private String moneyStr;

    public MyMoneyTransformer() {
        this.pounds = 0;
        this.pence = 0;
    }

    public MyMoneyTransformer(int pounds, int pence) {
        this.pounds = pounds;
        this.pence = pence;
    }

    public MyMoneyTransformer(String amount) {
        Pattern pattern = Pattern.compile("^[\\d]*([\\d+])\\.([\\d][\\d])$");
        Matcher matcher = pattern.matcher(amount);
        matcher.find();
        this.pounds = Integer.parseInt(matcher.group(1));
        this.pence = Integer.parseInt(matcher.group(2));
        //getMoney();
    }

    public int getPounds() {
        return pounds;
    }

    public int getPence() {
        return pence;
    }

    public double getMoney(){
        return (this.pounds + (pence/100));
    }
}