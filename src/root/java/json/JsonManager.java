package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.Automata;
import logic.Nodo;

import java.io.*;
import java.util.ArrayList;

public class JsonManager implements Serializable {

    public int index;
    public String filename;
    public String dirIndex = "./json_files/index.idx";
    public File file;
    public RandomAccessFile ram;

    public JsonManager()
    {
        try{
            ram = new RandomAccessFile(dirIndex, "rw");
            ram.seek(0);
            index = ram.readInt();
            filename =  "./json_files/automata_"+index+".json";
            file = new File(filename);
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeIndexFile()
    {
        try {
            if (ram.length()==0){
                ram.seek(0);
                ram.writeInt(0);
                ram.close();
            }
            else{
                ram.seek(0);
                index = ram.readInt();
                System.out.println("ReadInt: "+index);
                index++;
                ram.seek(0);
                ram.writeInt(index);
                ram.seek(0);
                System.out.println("Nuevo valor: "+ram.readInt());
                ram.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetIndex()
    {
        try{
            ram.seek(0);
            ram.writeInt(0);
            ram.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createJson(String s)
    {
        try
        {
            writeIndexFile();
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

    public ArrayList<Nodo> loadJson()
    {
        try{
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Nodo> nodoList  = gson.fromJson(br,ArrayList.class);
            return nodoList;
        } catch (FileNotFoundException ex)  {
            ex.printStackTrace();
        }
        return null;
    }
}
