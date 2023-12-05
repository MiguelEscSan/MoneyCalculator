package mock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import interfaces.*;
import model.Currency;

public class TsvFileCoinsLoader implements CoinsLoader {

    private final File file;

    public TsvFileCoinsLoader(File file) {
        this.file = file;
    }

    @Override
    public List<Currency> load() {
        try {
            return load(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Currency> load(FileReader fileReader) throws IOException {
        return load(new BufferedReader(fileReader));
    }

    private List<Currency> load(BufferedReader bufferedReader) throws IOException {
        List<Currency> result = new ArrayList<>();
        while (true) {
            String line = bufferedReader.readLine();
            if(line == null) break;
            result.add(toCurrency(line));
        } return result;
    }

    private Currency toCurrency(String line) {
        return toCurrency(line.split("\t"));
    }

    private Currency toCurrency(String[] split) {
        if(2 < split.length) {
            return new Currency(
                    split[0],
                    split[1],
                    split[2]
            );
        } else {
            return new Currency(
                    split[0],
                    split[1],
                    "");
        }

    }
}
