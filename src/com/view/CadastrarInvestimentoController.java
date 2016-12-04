package com.view;

import com.MainApp;
import com.crud.InvestimentoDAO;
import com.model.Investimento;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CadastrarInvestimentoController {
	
	MainApp mainApp;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField valorTextField;

    @FXML
    private TextArea planoTextArea;

    @FXML
    private DatePicker dataDatePicker;

    @FXML
    private Button cadastrarButton;

    @FXML
    private Button cancelarButton;
    
    @FXML
    private void investir(){
    	String nome = nomeTextField.getText();
    	String valor =valorTextField.getText();
    	String plano = planoTextArea.getText();
    	String data = dataDatePicker.getValue().toString();
    	
    	Investimento i = new Investimento(nome, valor, data, plano);
    	InvestimentoDAO.investir(i);
    	/*
    	for (Investimento inv : InvestimentoDAO.getTodosInvestimentos()) {
    		System.out.println(inv);
		}
    	*/
    }
    
    @FXML
    private void cancelar(){
    	this.mainApp.retornarATelaInicial();
    }

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
}
