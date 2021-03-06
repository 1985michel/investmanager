package com.view;

import java.util.List;

import com.MainApp;
import com.calculosDeEficiencia.EficienciaDeInvestimento;
import com.crud.InvestidorDAO;
import com.crud.InvestimentoDAO;
import com.crud.TipoDeInvestimentoDAO;
import com.crud.VariacaoRegistroDAO;
import com.model.Investidor;
import com.model.Investimento;
import com.model.TipoDeInvestimento;
import com.model.Variacao;
import com.model.VariacaoRegistro;
import com.util.CalcularVariacao;
import com.util.EstruturaData;
import com.util.MascaraFinanceira;
import com.util.OrdenaListDeInvestimentosPorData;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableCell;

public class InvestimentosController {

	MainApp mainApp;

	// static Investidor investidorSelecionado;
	Investidor investidorGeral;
	// static TipoDeInvestimento tipoDeInvestimentoSelecionado;
	TipoDeInvestimento tipoDeInvestimentoGeral;
	ObservableList<Investimento> listaDeInvestimentosFiltrada = FXCollections.observableArrayList();

	// Sobre a colora��o das rows
	Investimento iMelhor;
	Investimento iPior;

	// Observable list que conter� todos os investimentos
	public ObservableList<Investimento> list = FXCollections.observableArrayList();

	@FXML
	private ComboBox<Investidor> selecionarInvestidorComboBox;

	@FXML
	private ComboBox<TipoDeInvestimento> selecionarTipoDeInvestimentoComboBox;

	@FXML

	private TableView<Investimento> todosInvestimentosTableView;

	@FXML
	private TableColumn<Investimento, String> idTableColumn;

	@FXML
	private TableColumn<Investimento, String> eficienciaTableColumn;

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

	@FXML
	private ImageView cadVarImageViewButton;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;

		// Carregando a listaa
		this.list = InvestimentoDAO.getTodosInvestimentos();
		// OrdenaListDeInvestimentosPorData.ordenaInvestimentosPorData(list);

		filtrandoInvestimentosPorInvestidorETipoDeInvestimento();
		// showBalanco(listaDeInvestimentosFiltrada);

		// >>>>
		// setMelhorEPiorInvestimentoEmTermosDeEficiencia(listaDeInvestimentosFiltrada);

		// Passando a lista para o main
		MainApp.listaInvestimentos = list;

		// Apresentando o total investido
		// showBalanco(list);

	}

	private void calcularVaricoes() {

		calculoVariacoes(listaDeInvestimentosFiltrada);
		showBalanco(listaDeInvestimentosFiltrada);
		calcularEficiencia();
		setMelhorEPiorInvestimentoEmTermosDeEficiencia(listaDeInvestimentosFiltrada);

	}

	private void calcularEficiencia() {
		EficienciaDeInvestimento eficiencia = new EficienciaDeInvestimento(listaDeInvestimentosFiltrada);
		eficiencia.calcularEficiencia();
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
	 * Inicializa a classe controller. M�todo chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {

		// setando o investidor geral
		if (MainApp.investidorSelecionado == null) {
			investidorGeral = new Investidor("-1", "Todos", "0");
			MainApp.investidorSelecionado = investidorGeral;
		} else {
			selecionarInvestidorComboBox.setValue(MainApp.investidorSelecionado);
		}

		if (MainApp.tipoDeInvestimentoSelecionado == null) {
			// setando o tipo de investimento Geral
			tipoDeInvestimentoGeral = new TipoDeInvestimento("-1", "Todos", "0");
			MainApp.tipoDeInvestimentoSelecionado = tipoDeInvestimentoGeral;
		} else {
			selecionarTipoDeInvestimentoComboBox.setValue(MainApp.tipoDeInvestimentoSelecionado);
			// filtrandoInvestimentosPorInvestidorETipoDeInvestimento();
			// calcularVaricoes();
		}

		filtrandoInvestimentosPorInvestidorETipoDeInvestimento();

		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

		eficienciaTableColumn.setCellValueFactory(cellData -> cellData.getValue().eficienciaProperty());

		eficienciaTableColumn.setCellFactory(column -> {
			return new TableCell<Investimento, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {

					TableRow<Investimento> currentRow = getTableRow();

					// limpando css anteriorores
					currentRow.setStyle("");

					// limpando os dados da �ltima atualiza��o feita
					//THIS IS MY SOLUTION TO CLEAR THE OLD DATAS <<<<<<<<<<<<<<<<<<<<<<<<<<<
					if (item == null) {
						setText("");
					}

					// Trabalhando a visualiza��o
					if (item != null && item != "") {
						super.updateItem(item, empty);

						// LocalDate data = geraData(item);

						String variacaoCorrente = item;

						setText(item);

						if (currentRow != null) {
							setRowColorPorEficiencia(currentRow, variacaoCorrente);
						}

					}
				}
			};
		});

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

		// Detecta mudan�as de sele��o e habilita e desabilita as a��es do HBox
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
			MainApp.investidorSelecionado = selecionarInvestidorComboBox.getValue();
			filtrandoInvestimentosPorInvestidorETipoDeInvestimento();
			calcularVaricoes();
		});

		List<TipoDeInvestimento> listaDeTipos = TipoDeInvestimentoDAO.getTodosTiposDeInvestimentos();
		listaDeTipos.add(tipoDeInvestimentoGeral);
		for (TipoDeInvestimento tipo : listaDeTipos) {
			selecionarTipoDeInvestimentoComboBox.getItems().add(tipo);
		}

		selecionarTipoDeInvestimentoComboBox.setOnAction((event) -> {
			// System.out.println(mcb.getValue().idMateria);
			MainApp.tipoDeInvestimentoSelecionado = selecionarTipoDeInvestimentoComboBox.getValue();
			filtrandoInvestimentosPorInvestidorETipoDeInvestimento();
			calcularVaricoes();
		});

		/*
		 * // Detecta o duplo click do mouse e apresenta o investimento para
		 * edi��o // Caso ok, o investimento � carregado no formul�rio
		 * todosInvestimentosTableView.setOnMousePressed((event) -> { if
		 * (event.isPrimaryButtonDown() && event.getClickCount() == 2) { Alert
		 * alert = new Alert(AlertType.CONFIRMATION);
		 * alert.setTitle("Necess�ria confirma��o");
		 * alert.setHeaderText("Voc� deseja continuar com esse atendimento?");
		 * alert.setContentText(
		 * "Ao clicar em \"Ok\" os dados desse atendimento ser�o carregados na tela principal sobrepondo os dados atuais."
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
		 * ConsultarAtendimentoPeloId(idAte); // fecha o dialog do hist�rico
		 * this.dialogStage.close(); } } });
		 */

	}

	private void setRowColorPorEficiencia(TableRow<Investimento> currentRow, String variacao) {

		// System.out.println("Melhor: "+iMelhor.getEficiencia());
		// System.out.println("Pior: "+iPior.getEficiencia());

		if (listaDeInvestimentosFiltrada.size() > 2) {
			if (variacao.equalsIgnoreCase(iPior.getEficiencia())) {
				currentRow.setStyle("-fx-background-color: #FE7B51;");
			}

			if (variacao.equalsIgnoreCase(iMelhor.getEficiencia())) {
				currentRow.setStyle("-fx-background-color: #009926;");
			}
		}

	}

	public void setMelhorEPiorInvestimentoEmTermosDeEficiencia(List<Investimento> list) {
		Investimento iMelhor = null;
		Investimento iPior = null;

		double a = 0.0;
		double b = 0.0;

		if (list != null && list.size() > 2) {

			iMelhor = list.get(0);
			iPior = list.get(0);

			for (Investimento i : list) {

				a = new Double(iMelhor.getEficiencia().replace(',', '.'));
				b = new Double(iPior.getEficiencia().replace(',', '.'));

				if (a < new Double(i.getEficiencia().replace(',', '.'))) {
					iMelhor = i;
				}
				if (b > new Double(i.getEficiencia().replace(',', '.'))) {
					// System.out.println("Piorando: "+i.getEficiencia());
					iPior = i;
				}
			}

			this.iMelhor = iMelhor;
			this.iPior = iPior;

		}
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
		valorTotalLabel.setText("R$ " + MascaraFinanceira.show(totalAtual));
	}

	private void permitirAcoes(Investimento newValue) {
		boolean status = (newValue == null);
		cadastrarVariacaoButton.setDisable(status);
		editarInvestimentoButton.setDisable(status);
		excluirInvestimentoButton.setDisable(status);

		EficienciaDeInvestimento eficiencia = new EficienciaDeInvestimento(listaDeInvestimentosFiltrada);
		/*
		 * Investimento iv = null; for (Investimento i :
		 * listaDeInvestimentosFiltrada) { if(i.getId().equalsIgnoreCase("23"))
		 * iv = i;
		 * 
		 * } eficiencia.getEficiencia(iv);
		 */
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

	private void filtrandoInvestimentosPorInvestidorETipoDeInvestimento() {

		// refreshTable();

		listaDeInvestimentosFiltrada.removeAll(listaDeInvestimentosFiltrada);
		for (Investimento i : list) {
			if (isInvestidorSelecionado(i) && isTipoFiltrado(i))
				listaDeInvestimentosFiltrada.add(i);
		}
		if (listaDeInvestimentosFiltrada != null) {

			OrdenaListDeInvestimentosPorData.ordenaInvestimentosPorData(listaDeInvestimentosFiltrada);
			todosInvestimentosTableView.setItems(listaDeInvestimentosFiltrada);
			calcularVaricoes();

		}
	}



	// M�todo que verifica se o investimento � do tipo selecionado no comboBox
	private boolean isTipoFiltrado(Investimento i) {
		if (MainApp.tipoDeInvestimentoSelecionado.getId().equals("-1"))
			return true;
		else if (i.getTipoInvestimento().equals(MainApp.tipoDeInvestimentoSelecionado))
			return true;

		return false;
	}

	// M�todo que verifica se o investimento � do investidor selecionado no
	// comboBox
	private boolean isInvestidorSelecionado(Investimento i) {
		if (MainApp.investidorSelecionado.getId().equals("-1"))
			return true;
		else if (i.getInvestidor().equals(MainApp.investidorSelecionado))
			return true;

		return false;
	}

}
