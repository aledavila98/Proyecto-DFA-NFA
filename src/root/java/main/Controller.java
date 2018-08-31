package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.DFA;

public class Controller {

    public TableView tablaEstados;
    public TextField tfAlfabeto;
    public TextField tfEstFinales;
    public TextField tfCantEstados;
    public TableColumn columnaDeTransiciones;

    @FXML
    protected void crearDFA(ActionEvent actionEvent){
        tablaEstados.setEditable(true);
        String alfabeto = tfAlfabeto.getText();
        int cantEstados = Integer.parseInt(tfCantEstados.getText());
        String estFinales = tfEstFinales.getText();
        String[] estadosFinales = parserEstadosFinales(estFinales);
        DFA dfa = new DFA(cantEstados,alfabeto);
        dfa.buildTabla(estadosFinales);
        dfa.printTabla();
        TableColumn[] tableColumns = new TableColumn[alfabeto.length()];
    }

    @FXML
    protected void crearNFA(ActionEvent actionEvent){}

    private String[] parserEstadosFinales(String s)
    {
        return s.split(",");
    }
}
