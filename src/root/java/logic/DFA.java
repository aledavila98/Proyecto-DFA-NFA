package logic;

import java.util.ArrayList;

public class DFA extends Automata {

    public int ultimoEstado;

    public DFA()
    {
        nodos = new ArrayList<Nodo>();
        ultimoEstado = 0;
    }

    public void generateAutomataInJson(ArrayList<Nodo> estados) {

    }

    public void loadAutomataFromJson() {

    }

    public boolean buildAutomata(String c) {
        char[] lenguaje = this.findLetters(c);
        boolean finalizar = false;
        while (!finalizar)
        {
            if (ultimoEstado == 0)
            {
                String estadoActual = "q"+ultimoEstado;
                Nodo nodo = new Nodo(estadoActual);
                nodos.add(nodo);
                for (int i=0; i<lenguaje.length; i++)
                {
                    ultimoEstado++;
                    estadoActual = "q"+ultimoEstado;
                    Nodo nuevo = new Nodo(estadoActual);
                    nodo.addArista("q0",estadoActual,lenguaje[i]);
                    nodos.add(nuevo);
                }
            }
            else{

            }
            finalizar = true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        DFA dfa = new DFA();
        dfa.buildAutomata("aab");
        dfa.printNodos();
    }

}
