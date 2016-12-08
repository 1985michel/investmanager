package com.view;

import com.MainApp;
import com.crud.TipoDeInvestimentoDAO;
import com.model.TipoDeInvestimento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarTipoDeInvestimentoController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TextField nomeTextField;

	@FXML
	private Button cadastrarButton;

	@FXML
	private Button cancelarButton;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {

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
	 * Chamado quando o usuário clica ok
	 */
	@FXML
	private void handleOk() {

		String nome = nomeTextField.getText();
		String idCalculadora = "0";

		okClicked = true;

		// Criando o Tipo
		TipoDeInvestimento ti = new TipoDeInvestimento(nome, idCalculadora);

		// Colocando a variação no db
		TipoDeInvestimentoDAO.criarNovoTipo(ti);

		this.mainApp.atualizarTelaInvestimentos();
		dialogStage.close();

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
