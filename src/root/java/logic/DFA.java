package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import json.JsonManager;

import java.util.ArrayList;

public class DFA extends Automata {

    public int ultimoEstado;

    public DFA()
    {
        nodos = new ArrayList<Nodo>();
        ultimoEstado = 0;
    }

    public String generateAutomataInJson() {
        Gson gsonObject = new GsonBuilder().setPrettyPrinting().create();
        String representacion = gsonObject.toJson(nodos);
        System.out.println(representacion);
        return representacion;
    }

    public void loadAutomataFromJson() {

    }

    public boolean buildAutomata(String s) {
        char[] lenguaje = this.findLetters(s);
        boolean finalizar = false;
        while (!finalizar)
        {
            if (ultimoEstado == 0)
            {
                Nodo nodInicial = new Nodo("q0");
                if (s.length()==1 || (s.length()==2 && s.charAt(1) == '*'))
                {
                    nodInicial.addArista("q0","q0", s.charAt(0));
                    nodInicial.estadoFinal = true;
                    nodos.add(nodInicial);
                    return true;
                }
                else{

                }
            }
            finalizar = true;
        }
        return false;
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
        dfa.buildAutomata("a*");
        JsonManager jsonManager = new JsonManager();
        jsonManager.createJson(dfa.generateAutomataInJson());
    }

}
