package logic;

import java.util.ArrayList;

public class Nodo {

    public String valor;
    public ArrayList<Aristas> aristas;
    public int cantAristas;

    public Nodo()
    {
        valor = null;
        aristas = null;
    }

    public Nodo(String v)
    {
        valor = v;
        cantAristas = 0;
    }

    public void addArista(String v, String to)
    {
        cantAristas +=1;
        aristas.add(new Aristas(to,v,cantAristas));
    }

}
