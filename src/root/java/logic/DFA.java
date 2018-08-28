package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import json.JsonManager;

import java.util.ArrayList;

public class DFA extends Automata {

    public int ultimoEstado;
    private JsonManager jsonManager;

    public DFA()
    {
        nodos = new ArrayList<Nodo>();
        jsonManager = new JsonManager();
        ultimoEstado = 0;
    }

    public String generateAutomataInJson() {
        Gson gsonObject = new GsonBuilder().setPrettyPrinting().create();
        String representacion = gsonObject.toJson(nodos);
        jsonManager.createJson(representacion);
        System.out.println(representacion);
        return representacion;
    }

    public void loadAutomataFromJson() {
        nodos = jsonManager.loadJson();
    }

    public boolean buildAutomata(String s) {
        //Aqui se inicializa el automata
        while (true) {
            if (ultimoEstado == 0) {
                Nodo nodInicial = new Nodo("q0");
                ultimoEstado++;
                nodos.add(nodInicial);
                //esta condicion se ejecuta cuando la cadena solo contiene un caracter
                if (s.length() == 1 || (s.length() == 2 && s.charAt(1) == '*')) {
                    nodInicial.addArista("q0", "q0", s.charAt(0));
                    nodInicial.estadoFinal = true;
                    //nodos.add(nodInicial);
                    return true;
                }
            } else {
                for (int c=0; c<s.length() ; c++) {
                    Nodo actual = nodos.get(c);
                    String nuevoEstado = "q" + ultimoEstado;
                    Nodo nodtmp = new Nodo(nuevoEstado);
                    actual.addArista(nuevoEstado, actual.valor, s.charAt(c));
                    nodos.add(nodtmp);
                    if (c == s.length() - 1)
                    {
                        nodtmp.estadoFinal = true;
                        return true;
                    }
                    ultimoEstado++;
                }
            }
        }
    }

    private boolean[] findCycle(String c)
    {
        boolean[] repeating = new boolean[c.length()];
        for (int i=0; i<c.length(); i++)
        {
            if (c.charAt(i) == '*')
                repeating[i-1] = true;
        }
        return repeating;
    }

    public static void main(String[] args)
    {
        DFA dfa = new DFA();
        dfa.buildAutomata("aabca");
        dfa.generateAutomataInJson();
        //dfa.printNodos();
    }

}
