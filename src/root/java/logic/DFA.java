package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import json.JsonManager;

import java.util.ArrayList;

public class DFA extends Automata {

    public int ultimoEstado;
    private JsonManager jsonManager;
    public int cantEstados;
    public String alfabeto;
    public String estados[][];

    public DFA(int cant, String alf)
    {
        cantEstados = cant;
        alfabeto = alf;
        nodos = new ArrayList<Nodo>();
        jsonManager = new JsonManager();
        ultimoEstado = 0;
    }

    public boolean buildAutomataFromER(String s) {
        //Aqui se inicializa el automata
        while (true) {
            if (ultimoEstado == 0) {
                Nodo nodInicial = new Nodo("q0");
                nodInicial.estadoInicial = true;
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
                boolean or = false;
                for (int c=0; c<s.length() ; c++) {
                    Nodo actual = nodos.get(c);
                    /*/if (s.charAt(c) == '+') {
                        //actual.estadoFinal = true;
                        //ultimoEstado++;
                        String ult = "q"+ultimoEstado;
                        actual.addArista(ult,actual.valor,s.charAt(c+1));
                        or = true;
                        Nodo nuevo = new Nodo(ult);
                        nodos.add(nuevo);
                        continue;
                    }*/
                    if (c == s.length()-1) {
                        if (s.charAt(s.length() - 1) == '*') {
                            actual.addArista(actual.valor, actual.valor, s.charAt(s.length() - 2));
                            actual.estadoFinal = true;
                            return true;
                        }
                    }
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

    public void buildTabla(String[] estFin) {
        int length = alfabeto.length();
        estados = new String[cantEstados+1][length+1];
        estados[0][0] = "q";
        char[] alf = new char[length];
        for (int i=0; i<length; i++) {
            alf[i] = alfabeto.charAt(i);
            estados[0][i+1] = ""+alf[i];
        }
        for (int c=0; c<cantEstados; c++)
        {
            String nodActual = "q"+c;
            Nodo nodo = new Nodo(nodActual);
            if (nodActual.equals("q0")) {
                nodo.estadoInicial = true;
                nodActual = "-> q"+c;
            }
            nodos.add(nodo);
            for (int i=0; i<estFin.length; i++)
            {
                if (estFin[i].equals(nodActual)){
                    nodo.estadoFinal = true;
                    nodActual = "* q"+c;
                    break;
                }
            }
            estados[c+1][0] = nodActual;
        }
    }

    public void asignarAristas(String[][] aristas)
    {
        Nodo estadoActual;
        for (int f=1; f<=cantEstados; f++)
        {
            estadoActual = getEstadoActual(f);
            for (int c=1; c<= alfabeto.length(); c++)
                estadoActual.addArista(aristas[f][c],estadoActual.valor,estados[0][c].charAt(0));
        }
    }

    public boolean evaluarDFA(String s)
    {
        Nodo nodtmp = nodos.get(0);
        String cadenaComparar = "";
        String nuevo = "";
        for (int c=0; c<s.length(); c++)
        {
            for (Aristas a : nodtmp.aristas)
            {
                if (a.valor == s.charAt(c)){
                    nuevo = a.to;
                    cadenaComparar += s.charAt(c);
                    if (cadenaComparar.equals(s) && nodtmp.estadoFinal)
                        return true;
                    break;
                }
            }
            for (Nodo n : nodos)
            {
                if (n.valor.equals(nuevo))
                    nodtmp = n;
            }
        }
        return false;
    }

    //public void fillTabla()

    private Nodo getEstadoActual(int fila)
    {
        for (Nodo n: nodos)
        {
            if (n.valor.equals(estados[fila][0]))
                return n;
        }
        return null;
    }

    public void printTabla()
    {
        for (int c=0; c<=cantEstados; c++)
        {
            //System.out.print(estados[c][0]+" | ");
            for (int i=0; i<=alfabeto.length(); i++)
                System.out.print(estados[c][i]+" | ");
            System.out.println("");
        }
    }

    public boolean buildAutomata(String c) {
        return false;
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
        DFA dfa = new DFA(4,"abc");
        String[] finales = new String[2];
        finales[0] = "q1";
        finales[1] = "q3";
        dfa.buildTabla(finales);
        dfa.printTabla();
    }

}
