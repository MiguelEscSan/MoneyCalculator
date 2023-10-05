package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class ExchangeRates {

    String url;
    String monedaActual;
    Map<String, Double> ratesMap = new HashMap<>();
    public ExchangeRates(String urlDivisa) {
        this.url = urlDivisa;
        try {
            // Crear una URL y abrir una conexión HTTP
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud HTTP
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Obtener el contenido de la URL como un BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            // Cerrar el BufferedReader y la conexión
            reader.close();
            connection.disconnect();

            // Parsear el JSON utilizando JsonParser
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(response.toString());
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Obtener el objeto 'rates' del JSON
            JsonObject ratesObject = jsonObject.getAsJsonObject("rates");
            this.monedaActual = jsonObject.get("base").getAsString();



            for (Map.Entry<String, JsonElement> entry : ratesObject.entrySet()) {
                ratesMap.put(entry.getKey(), entry.getValue().getAsDouble());
            }

            // Imprimir el diccionario rates
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMonedaActual() {
        return monedaActual;
    }

    public void setRatesMap(String urlNueva) {
        this.url = urlNueva;
        try {
            // Crear una URL y abrir una conexión HTTP
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud HTTP
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Obtener el contenido de la URL como un BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            // Cerrar el BufferedReader y la conexión
            reader.close();
            connection.disconnect();

            // Parsear el JSON utilizando JsonParser
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(response.toString());
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Obtener el objeto 'rates' del JSON
            JsonObject ratesObject = jsonObject.getAsJsonObject("rates");

            this.monedaActual = jsonObject.get("base").getAsString();

            // Convertir el objeto 'rates' a un Map<String, Double>

            for (Map.Entry<String, JsonElement> entry : ratesObject.entrySet()) {
                ratesMap.put(entry.getKey(), entry.getValue().getAsDouble());
            }


            // Imprimir el diccionario rates
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Double> getRatesMap() {
        return ratesMap;
    }

    public void setRatesMap(Map<String, Double> ratesMap) {
        this.ratesMap = ratesMap;
    }
    
    public Double getChange(String divisa) {
        return  this.ratesMap.get(divisa);
    }
}
