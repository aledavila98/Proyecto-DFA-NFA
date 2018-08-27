package logic;

import java.util.ArrayList;

public class Nodo {

    public String valor;
    public ArrayList<Aristas> aristas;
    public int cantAristas;
    public boolean estadoFinal;

    public Nodo()
    {
        valor = null;

    }

    public Nodo(String v)
    {
        valor = v;
        aristas = new ArrayList<Aristas>();
        cantAristas = 0;
    }

    public void addArista(String to, String f, char c)
    {
        cantAristas +=1;
        aristas.add(new Aristas(to,f,c));
    }

}
