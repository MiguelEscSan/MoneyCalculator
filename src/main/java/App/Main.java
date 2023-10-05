package App;

import gui.MoneyCalculatorGui;

import model.ExchangeRates;

public class Main {

    public static void main(String args[]) {
        // Por defecto usaremos la mas reciente
        java.awt.EventQueue.invokeLater(() -> {
            new MoneyCalculatorGui(new ExchangeRates("http://api.exchangeratesapi.io/v1/latest?access_key=e7a83fdc52a9dac2758d5d8fdc326d4a&format=1")).setVisible(true);
        });

    }
}
