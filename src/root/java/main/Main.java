package main;

import json.JsonManager;
import logic.Automata;
import logic.DFA;

public class Main {
    public static void main (String[] args)
    {
        DFA dfa = new DFA();
        dfa.printLetters("aabcaa");
    }
}
