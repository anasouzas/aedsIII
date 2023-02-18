import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class App {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String line = "";

        FileOutputStream fos = new FileOutputStream("../db/banco.db");
        DataOutputStream dos = new DataOutputStream(fos);

        FileInputStream fis = new FileInputStream("../db/banco.db");
        DataInputStream dis = new DataInputStream(fis);

        try {
            BufferedReader br = new BufferedReader(new FileReader("netflix.csv"));

            line = br.readLine(); // to skip first row

            while ((line = br.readLine()) != null) {
                Show show = new Show();

                show.getShowsInformations(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
