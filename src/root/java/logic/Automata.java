package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.JsonManager;

import java.util.ArrayList;

public abstract class Automata {
    public ArrayList<Nodo> nodos;

    public abstract boolean buildAutomata(String c);

    public abstract String generateAutomataInJson();

    public abstract void loadAutomataFromJson();


    public char[] findAlphabet(String s)
    {
        int length = s.length();
        boolean repeat[] = new boolean[length];
        int lettersLength = 0;
        for (int c=0; c<length; c++)
        {
            for (int i=0; i<length; i++)
            {
                if (c == i || repeat[i])
                    continue;
                if (s.charAt(c) == s.charAt(i))
                    repeat[c] = true;
            }
        }
        for (int c=0; c<length; c++)
        {
            if (!repeat[c])
                lettersLength++;
        }
        char[] letters = new char[lettersLength];
        int cont = 0;
        for (int c=0; c<length; c++)
        {
            if (!repeat[c]) {
                letters[cont] = s.charAt(c);
                cont+=1;
            }
        }
        return letters;
    }

    public void printLetters(String s)
    {
        char[] array = findAlphabet(s);
        System.out.println(array.length);
        for (int c = 0; c<array.length; c++)
            System.out.print(array[c]);
    }

    public void printNodos()
    {
        for (Nodo n : nodos)
        {
            if (n.valor.equals("q0")){
                if (n.estadoFinal)
                    System.out.print("* -> "+n.valor+" ");
                else
                    System.out.print("-> "+n.valor);
            }
            else
                System.out.print(n.valor+" - ");
            for (Aristas a : n.aristas)
                System.out.println(a.valor+" - "+a.from+"- "+a.to);
        }
    }
}
