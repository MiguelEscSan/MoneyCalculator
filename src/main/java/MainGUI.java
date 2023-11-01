import mock.JsonExchangeRatesLoader;
import model.ExchangeRates;
import swing.MainFrame;

public class MainGUI {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        JsonExchangeRatesLoader ratesLoader = new JsonExchangeRatesLoader("http://api.exchangeratesapi.io/v1/latest?access_key=e7a83fdc52a9dac2758d5d8fdc326d4a&symbols=USD&format=1");


        frame.setVisible(true);
    }
}
