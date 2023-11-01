package mock;


import interfaces.CurrencyDialog;
import model.Currency;

import java.util.List;

public class MockCurrencyDialog implements CurrencyDialog {
    private List<Currency> currencies;

    @Override
    public CurrencyDialog define(List<Currency> currencyList) {
        currencies = currencyList;
        return this;
    }

    @Override
    public Currency get() {
        return currencies.get(1);
    }
}
