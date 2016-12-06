package com.view;

import java.time.LocalDate;

import com.MainApp;
import com.crud.InvestimentoDAO;
import com.crud.VariacaoRegistroDAO;
import com.model.Investimento;
import com.model.TextFieldMoney;
import com.model.VariacaoRegistro;
import com.util.MascaraFinanceira;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarInvestimentoController {

	MainApp mainApp;

	// Para ser usado quando de atualização

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;
	
	
	@FXML
	private TextField nomeTextField;

	@FXML
	private TextFieldMoney valorTextField;

	@FXML
	private TextArea planoTextArea;

	@FXML
	private DatePicker dataDatePicker;

	@FXML
	private Button cadastrarButton;

	@FXML
	private Button cancelarButton;

	@FXML
	private void investir() {
		String nome = nomeTextField.getText();
		String valor = valorTextField.getCleanValue();
		String plano = planoTextArea.getText();
		String data = dataDatePicker.getValue().toString();

		Investimento i = new Investimento(nome, valor, data, plano,"0");
		InvestimentoDAO.investir(i);
		this.mainApp.retornarATelaInicial();
	}

	@FXML
	private void cancelar() {
		this.mainApp.retornarATelaInicial();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	// Métodos de atualização

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
		okClicked = true;
		
		String data = dataDatePicker.getValue().toString();
		String valor = valorTextField.getCleanValue();
		String nome = nomeTextField.getText();
		String plano = planoTextArea.getText();

		
		//Autalizando o objeto investimento na memória
		MainApp.investimentoSelecionado.setData(data);
		MainApp.investimentoSelecionado.setValor(valor);
		MainApp.investimentoSelecionado.setNome(nome);
		MainApp.investimentoSelecionado.setPlano(plano);
		

		//Atualizando o Banco
		InvestimentoDAO.atualizarInvestimento(MainApp.investimentoSelecionado);
		
		
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

	public void povoarFormulario(Investimento i) {
		this.nomeTextField.setText(i.getNome());
		this.valorTextField.setText(MascaraFinanceira.show(i.getValor()));
		this.dataDatePicker.setValue(LocalDate.parse(i.getData()));
		this.planoTextArea.setText(i.getPlano());
		
		//Alterando os botões para funcionarem como atualização
		cadastrarButton.setText("Atualizar");
		cadastrarButton.setOnAction(event -> handleOk());
		cancelarButton.setOnAction(event -> handleCancel());

		
	}

	
}
