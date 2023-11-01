package model;

import java.util.Objects;

public class Currency {
    private final String code;
    private final String name;

    private final String symbol;

    private final Money amount;

    public Currency(String code, String name, String symbol, Money amount) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
        this.amount = amount;
    }

    public Currency(String code) {
        this.code = code;
        this.name="";
        this.amount=null;
        this.symbol="";
    }

    public String getSymbol() {
        return symbol;
    }

    public Money getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code) && Objects.equals(name, currency.name) && Objects.equals(symbol, currency.symbol) && Objects.equals(amount, currency.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, symbol, amount);
    }

}
