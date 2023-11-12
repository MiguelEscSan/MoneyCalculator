import control.ExchangeRatesCommand;
import interfaces.*;
import mock.JsonExchangeRatesLoader;
import mock.TsvFileCoinsLoader;
import model.Currency;
import model.ExchangeRates;
import swing.MainFrame;

import java.io.File;
import java.util.List;

public class MainGUI {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        CoinsLoader coinsLoader = new TsvFileCoinsLoader(new File("src/main/resources/currencies.tsv"));
        List<Currency> currencyList = coinsLoader.load();
        MoneyDialog moneyDialog = frame.getMoneyDialog().define(currencyList);
        CurrencyDialog currencyDialog = frame.getCurrencyDialog().define(currencyList);
        MoneyDisplay moneyDisplay = frame.getMoneyDisplay();
        ExchangeRatesLoader exchangeRatesLoader = new JsonExchangeRatesLoader();
        frame.add("change", new ExchangeRatesCommand(moneyDisplay, moneyDialog, currencyDialog, exchangeRatesLoader));

        frame.setVisible(true);

    }
}
