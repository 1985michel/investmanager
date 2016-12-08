package com.view;

import com.MainApp;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MolduraController {

	public MainApp mainApp;

	@FXML
	public BorderPane areaDeTrabalhoBorderPane;

	@FXML
	private void initialize() {
	}

	/**
	 * � chamado pela aplica��o principal para dar uma refer�ncia de volta a si
	 * mesmo. *
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void exibirCadastrarInvestimento() {
		this.mainApp.showCadastrarInvestimento(this.areaDeTrabalhoBorderPane);
	}

	public void exibierCadastrarTipoDeInvestimento() {
		this.mainApp.showCadastrarTipoDeInvestimentoOverview();
	}

	public void exibirCadastrarInvestidor() {
		this.mainApp.showCadastrarInvestidorOverview();
	}

}
