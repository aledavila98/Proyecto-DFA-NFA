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
        //Aqui se inicializa el automata
        if (ultimoEstado == 0)
        {
            Nodo nodInicial = new Nodo("q0");
            ultimoEstado++;
            //esta condicion se ejecuta cuando la cadena solo contiene un caracter
            if (s.length()==1 || (s.length()==2 && s.charAt(1) == '*'))
            {
                nodInicial.addArista("q0","q0", s.charAt(0));
                nodInicial.estadoFinal = true;
                nodos.add(nodInicial);
                return true;
            }

            else{
                boolean[] repitentes = findCycle(s);
                boolean hayRepitente = false;
                for (int c=0; c<repitentes.length; c++)
                {
                    if (repitentes[c])
                    {
                        hayRepitente = true;
                        break;
                    }
                }
                if (!hayRepitente) {
                    nodos.add(nodInicial);
                    for (int c = 0; c < lenguaje.length; c++)
                    {
                        Nodo nodTmp = new Nodo("q"+ultimoEstado);
                        nodInicial.addArista("q"+ultimoEstado,"q0",lenguaje[c]);
                        nodos.add(nodTmp);
                        ultimoEstado++;
                    }
                }
            }
        }
        else {

        }
        finalizar = true;
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
        dfa.buildAutomata("aab");
        JsonManager jsonManager = new JsonManager();
        jsonManager.createJson(dfa.generateAutomataInJson());
    }

}
