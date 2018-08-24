package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.Nodo;

import java.io.*;
import java.util.ArrayList;

public class JsonManager implements Serializable {

    public String filename = "automata.json";
    public String dirIndex = "index.aut";
    public File file;

    public JsonManager()
    {
        try{
            file = new File(filename);
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createJson(String s)
    {
        try
        {
            FileWriter writer = new FileWriter(file);
            writer.write(s);
            writer.close();
            System.out.println("Se guardo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadJson()
    {

    }
}
