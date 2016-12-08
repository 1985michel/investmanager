package com.view;

import java.util.List;

import com.MainApp;
import com.crud.InvestidorDAO;
import com.crud.InvestimentoDAO;
import com.crud.VariacaoRegistroDAO;
import com.model.Investidor;
import com.model.Investimento;
import com.model.Variacao;
import com.model.VariacaoRegistro;
import com.util.CalcularVariacao;
import com.util.EstruturaData;
import com.util.MascaraFinanceira;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InvestimentosController {

	MainApp mainApp;

	Investidor investidorSelecionado;
	ObservableList<Investimento> listaDeInvestimentosPorInvestidor = FXCollections.observableArrayList();
	Investidor investidorGeral;
	
	// Observable list que conterá todos os investimentos
	public ObservableList<Investimento> list = FXCollections.observableArrayList();

	@FXML
	private ComboBox<Investidor> selecionarInvestidorComboBox;

	@FXML
	private TableView<Investimento> todosInvestimentosTableView;

	@FXML
	private TableColumn<Investimento, String> idTableColumn;

	@FXML
	private TableColumn<Investimento, String> nomeTableColumn;

	@FXML
	private TableColumn<Investimento, String> dataTableColumn;

	@FXML
	private TableColumn<Investimento, String> valorInvestidoTableColumn;

	@FXML
	private TableColumn<Investimento, String> valorAtualTableColumn;

	@FXML
	private TableColumn<Investimento, String> lucroTableColumn;

	@FXML
	private TableColumn<Investimento, String> variacaoTableColumn;

	@FXML
	private Label variacaoLabel;

	@FXML
	private Label valorTotalLabel;

	@FXML
	private Label valorTotalInvestidoLabel;

	@FXML
	private Label lucroTotalBrutoLabel;

	@FXML
	private Button cadastrarVariacaoButton;

	@FXML
	private Button editarInvestimentoButton;

	@FXML
	private Button excluirInvestimentoButton;



	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;

		// Carregando a listaa
		this.list = InvestimentoDAO.getTodosInvestimentos();

		// Adiciona os dados da observable list à tabela
		exibirTodosInvestimentosNaTabela();

		// Passando a lista para o main
		MainApp.listaInvestimentos = list;

		// Calculando as variações com base na atualização mais recente
		calcularVaricoes();

		// Apresentando o total investido
		showBalanco(list);

	}

	private void exibirTodosInvestimentosNaTabela() {
		todosInvestimentosTableView.setItems(list);
	}

	private void calcularVaricoes() {

		if (investidorSelecionado.getId().equals("-1")) {
			calculoVariacoes(list);
			showBalanco(list);
		} else {
			calculoVariacoes(listaDeInvestimentosPorInvestidor);
			showBalanco(listaDeInvestimentosPorInvestidor);
		}

	}

	private void calculoVariacoes(ObservableList<Investimento> lista) {
		for (Investimento i : lista) {
			VariacaoRegistro vr = VariacaoRegistroDAO.getUltimaVariacaoPorRegistroPorInvestimento(i.getId());
			if (vr != null) {
				Variacao v = new Variacao(vr.getData(), vr.getValor());
				i.setVariacao(v);
			}

		}
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {

		// setando o investidor geral
		investidorGeral = new Investidor("-1", "Todos", "0");
		investidorSelecionado = investidorGeral;

		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		// Informando o foramto de datas que quero que seja apresentado na
		// tabela
		dataTableColumn
				.setCellValueFactory(cellData -> EstruturaData.estruturaData(cellData.getValue().dataProperty()));
		valorInvestidoTableColumn
				.setCellValueFactory(cellData -> MascaraFinanceira.showProperty(cellData.getValue().valorProperty()));
		valorAtualTableColumn.setCellValueFactory(
				cellData -> MascaraFinanceira.showProperty(cellData.getValue().getVariacao().valorProperty()));
		lucroTableColumn.setCellValueFactory(cellData -> cellData.getValue().getLucroProperty());

		variacaoTableColumn.setCellValueFactory(cellData -> cellData.getValue().getVariacao().variacaoProperty());

		// Detecta mudanças de seleção e habilita e desabilita as ações do HBox
		todosInvestimentosTableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> permitirAcoes(newValue));

		// alinhando os dados nas colunas
		idTableColumn.setStyle("-fx-alignment: CENTER;");
		nomeTableColumn.setStyle("-fx-alignment: CENTER;");
		dataTableColumn.setStyle("-fx-alignment: CENTER;");
		valorInvestidoTableColumn.setStyle("-fx-alignment: CENTER;");
		valorAtualTableColumn.setStyle("-fx-alignment: CENTER;");
		lucroTableColumn.setStyle("-fx-alignment: CENTER;");
		variacaoTableColumn.setStyle("-fx-alignment: CENTER;");

		List<Investidor> listaInvestidor = InvestidorDAO.getTodosInvestidores();
		listaInvestidor.add(investidorGeral);
		for (Investidor iU : listaInvestidor) {
			selecionarInvestidorComboBox.getItems().add(iU);
		}

		selecionarInvestidorComboBox.setOnAction((event) -> {
			// System.out.println(mcb.getValue().idMateria);
			investidorSelecionado = selecionarInvestidorComboBox.getValue();
			filtrandoInvestimentosPorInvestidor();
			calcularVaricoes();
		});

		/*
		 * // Detecta o duplo click do mouse e apresenta o investimento para
		 * edição // Caso ok, o investimento é carregado no formulário
		 * todosInvestimentosTableView.setOnMousePressed((event) -> { if
		 * (event.isPrimaryButtonDown() && event.getClickCount() == 2) { Alert
		 * alert = new Alert(AlertType.CONFIRMATION);
		 * alert.setTitle("Necessária confirmação");
		 * alert.setHeaderText("Você deseja continuar com esse atendimento?");
		 * alert.setContentText(
		 * "Ao clicar em \"Ok\" os dados desse atendimento serão carregados na tela principal sobrepondo os dados atuais."
		 * );
		 * 
		 * Optional<ButtonType> result = alert.showAndWait(); if (result.get()
		 * == ButtonType.OK) { // Obtem o id do cliente selecionado String idCli
		 * = atendimentosTableView.getSelectionModel().getSelectedItem().
		 * getIdCliente(); String idAte =
		 * atendimentosTableView.getSelectionModel().getSelectedItem().
		 * getIdAtendimento(); // Passa o id para o controller do
		 * AtendendoCliente
		 * this.mainApp.getAtendendoClienteController().ConsultarClientePeloId(
		 * idCli); this.mainApp.getAtendendoClienteController().
		 * ConsultarAtendimentoPeloId(idAte); // fecha o dialog do histórico
		 * this.dialogStage.close(); } } });
		 */

	}

	private void showBalanco(ObservableList<Investimento> lista) {
		double totalInvestido = 0.0;
		double totalAtual = 0.0;

		for (Investimento i : lista) {
			totalInvestido += new Double(i.getValor());
			totalAtual += new Double(i.getVariacao().getValor());
		}

		double lucro = (totalAtual - totalInvestido);

		valorTotalInvestidoLabel.setText("R$ " + MascaraFinanceira.show(totalInvestido));
		lucroTotalBrutoLabel.setText("R$ " + MascaraFinanceira.show(lucro));
		variacaoLabel.setText((CalcularVariacao.getLucroPercentualString(totalInvestido, totalAtual)) + "%");
	}

	private void permitirAcoes(Investimento newValue) {
		boolean status = (newValue == null);
		cadastrarVariacaoButton.setDisable(status);
		editarInvestimentoButton.setDisable(status);
		excluirInvestimentoButton.setDisable(status);

		informarInvestimentoParaMainApp(newValue);
	}

	private void informarInvestimentoParaMainApp(Investimento i) {
		MainApp.investimentoSelecionado = i;
	}

	@FXML
	public void handleCadastrarVariacao() {
		this.mainApp.showCadastrarVariacaoOverview();
	}

	@FXML
	public void atualizarInvestimento() {
		this.mainApp.showAtualizarInvestimentoOverview();
	}

	@FXML
	public void excluirInvestimento() {
		this.mainApp.excluirInvestimento();
	}

	private void filtrandoInvestimentosPorInvestidor() {
		// Primeiro exibindo todos
		if (investidorSelecionado.getId().equals("-1")) {
			exibirTodosInvestimentosNaTabela();
			return;
		}

		// Agora Filtrando por investidor

		listaDeInvestimentosPorInvestidor.clear();
		for (Investimento i : list) {
			if (i.getInvestidor().equals(investidorSelecionado))
				listaDeInvestimentosPorInvestidor.add(i);
		}
		if (listaDeInvestimentosPorInvestidor != null)
			todosInvestimentosTableView.setItems(listaDeInvestimentosPorInvestidor);
	}

}
