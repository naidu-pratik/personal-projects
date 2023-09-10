package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public BufferedReader createReader(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        return reader;
    }

    public BufferedWriter createWriter(String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        return writer;
    }

    public Boolean checkForEmptyFile(BufferedReader reader) throws IOException {
        if(reader.readLine() == null){
            return true;
        }
        else
            return false;
    }
}
