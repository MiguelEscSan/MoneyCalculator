package interfaces;

import model.Currency;
import model.ExchangeRates;

import java.io.IOException;
import java.util.HashMap;

public interface ExchangeRatesLoader {
    ExchangeRates load(Currency from, Currency to) throws IOException;

    void setUrl(Currency currency, Currency target);
}
