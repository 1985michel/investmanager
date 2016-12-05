package com.view;

import java.time.LocalDate;

import com.MainApp;
import com.crud.VariacaoRegistroDAO;
import com.model.TextFieldMoney;
import com.model.VariacaoRegistro;
import com.util.EstruturaData;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarVariacaoController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Vari�vel que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private DatePicker dataDatePicker;

	@FXML
	private TextFieldMoney valorTextField;

	@FXML
	private Button registrarButton;

	@FXML
	private Button cancelarButton;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	/**
	 * Inicializa a classe controller. M�todo chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		//setando a data de hoje no local date
		dataDatePicker.setValue(LocalDate.now());
	}
	/**
	 * Define o palco deste dialog. Usado para fecha-lo, por exemplo
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}



	/**
	 * Retorna true se o ok for clicado
	 */
	public boolean isOkCLicked() {
		return okClicked;
	}

	/**
	 * Chamado quando o usu�rio clica ok
	 */
	@FXML
	private void handleOk() {
		String data = dataDatePicker.getValue().toString();
		String valor = valorTextField.getCleanValue();

		okClicked = true;

		//Criando a varia��o
		VariacaoRegistro vr = new VariacaoRegistro(MainApp.investimentoSelecionado, data, valor);

		//Colocando a varia��o no db
		VariacaoRegistroDAO.registrarVariacao(vr);
		
		dialogStage.close();

	}

	/**
	 * Chamado quando o usu�rio clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}