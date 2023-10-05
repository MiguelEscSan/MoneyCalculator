package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import model.ExchangeRates;
import org.decimal4j.util.DoubleRounder;

import static java.lang.String.valueOf;

public class MoneyCalculatorGui extends JFrame implements ActionListener {

    private static JTextField cantidadOrigen, cantidadCambiar, year
            ;
    private static JComboBox<String> opcionesOrigen;
    private static JComboBox<String> opcionesCambiar;
    private static JComboBox opcionesYear;
    // Dentro de algún método o evento, por ejemplo, dentro de un ActionListener
    private static JButton BotonCambio;

    ExchangeRates JsonCambioMoneda;
    public MoneyCalculatorGui(ExchangeRates nuevo) {
        this.setVisible(true);
        this.setSize(500, 200);
        this.setTitle("MoneyCounter");
        this.JsonCambioMoneda  = nuevo;

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Set<String> keys = this.JsonCambioMoneda.getRatesMap().keySet();
        String[] arrayDeClaves = new String[keys.size()];
        keys.toArray(arrayDeClaves);

        opcionesYear = new JComboBox<>();
        this.year = new JTextField(10);

        for (int i = 2023; i >= 2003; i--) {
            opcionesYear.addItem(i);
        }

        opcionesOrigen = new JComboBox<>(arrayDeClaves);
        opcionesOrigen.addItem(this.JsonCambioMoneda.getMonedaActual());
        opcionesOrigen.setSelectedItem(this.JsonCambioMoneda.getMonedaActual());
        cantidadOrigen = new JTextField(10);

        opcionesCambiar = new JComboBox<>(arrayDeClaves);
        opcionesCambiar.addItem(this.JsonCambioMoneda.getMonedaActual());
        opcionesCambiar.setSelectedItem(this.JsonCambioMoneda.getMonedaActual());
        cantidadCambiar = new JTextField(10);

        cantidadCambiar = new JTextField(10);

        BotonCambio = new JButton();
        BotonCambio.setText("Realizar conversión");
        cantidadCambiar.setEditable(false); // Hace que el segundo campo de texto no sea editable

        // Crea paneles para agrupar etiquetas y campos de texto
        JPanel panelOrigen = new JPanel();
        panelOrigen.add(new JLabel("Cantidad:"));

        JPanel panelYear = new JPanel();
        panelYear.add(new JLabel("Año"));
        panelYear.add(opcionesYear);


        JPanel panelCambiar = new JPanel();
        panelCambiar.add(new JLabel("Cambio:"));
        panelCambiar.add(cantidadCambiar);
        panelCambiar.add(opcionesCambiar);

        // Agrega los paneles al panelFondo
        panelFondo.add(panelOrigen, BorderLayout.NORTH);
        panelFondo.add(panelCambiar, BorderLayout.CENTER);
        panelFondo.add(BotonCambio, BorderLayout.SOUTH);
        panelFondo.add(panelYear, BorderLayout.WEST);

        // Agrega el panelFondo al JFrame
        this.add(panelFondo);

        Color colorDeFondo = Color.LIGHT_GRAY; // Puedes cambiar esto a tu color preferido
        Font nuevaFuente = new Font("Arial", Font.BOLD, 16); // Puedes cambiar la fuente según tus preferencias

        // Configurar color de fondo y fuente para los componentes
        panelFondo.setBackground(colorDeFondo);
        cantidadOrigen.setBackground(Color.WHITE);
        cantidadCambiar.setBackground(Color.WHITE);
        opcionesOrigen.setBackground(Color.WHITE);
        opcionesCambiar.setBackground(Color.WHITE);
        opcionesYear.setBackground(Color.WHITE);

        // Configurar fuente para los componentes
        cantidadOrigen.setFont(nuevaFuente);
        cantidadCambiar.setFont(nuevaFuente);
        opcionesOrigen.setFont(nuevaFuente);
        opcionesCambiar.setFont(nuevaFuente);
        BotonCambio.setFont(nuevaFuente);
        opcionesYear.setFont(nuevaFuente);

        panelOrigen.add(cantidadOrigen);
        panelOrigen.add(opcionesOrigen);

        BotonCambio.addActionListener(this);
        // Configura el cierre de la aplicación cuando se cierra la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BotonCambio) {
            try {
                // Obtener el texto del JTextField cantidadOrigen
                double CantidadOrigen = Double.parseDouble(cantidadOrigen.getText());
                // Obtener el valor seleccionado del JComboBox opcionesOrigen
                String opcionOrigen = opcionesOrigen.getSelectedItem().toString();
                // Obtener el valor seleccionado del JComboBox opcionesCambiar
                String opcionCambiar = opcionesCambiar.getSelectedItem().toString();

                String año = opcionesYear.getSelectedItem().toString();

                this.JsonCambioMoneda.setRatesMap("http://api.exchangeratesapi.io/v1/"+año+"-01-01?access_key=e7a83fdc52a9dac2758d5d8fdc326d4a&format=1");
                cantidadCambiar.setEditable(true); // Hace que el segundo campo de texto no sea editable
                double valorCambio = (this.JsonCambioMoneda.getChange(opcionCambiar)*CantidadOrigen);

                cantidadCambiar.setText(String.valueOf(DoubleRounder.round(Double.parseDouble(valueOf(valorCambio)),4)));
                cantidadCambiar.setEditable(false);

            } catch (NumberFormatException ex) {
                // Manejar la excepción si el usuario ingresa un valor que no es un entero válido
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                // También podrías imprimir el mensaje de error en la consola si lo deseas
                ex.printStackTrace();
            }

        }
    }

}
