package com;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.crud.InvestimentoDAO;
import com.model.Investimento;
import com.view.CadastrarInvestimentoController;
import com.view.CadastrarVariacaoController;
import com.view.InvestimentosController;
import com.view.MolduraController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private MolduraController molduraController;
	public static List<Investimento> listaInvestimentos;
	public static Investimento investimentoSelecionado;

	public void addPersonalStyle(Scene scene) {

		try {
			scene.getStylesheets().clear();
			setUserAgentStylesheet(null);
			scene.getStylesheets().add(getClass().getResource("view/modenaDark.css").toExternalForm());
		} catch (Exception e) {
			System.out.println("Erro ao aplicar css Dark");
		}

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("InvestManager - Consquistando sua Liberdade Financeira!");

		initRootLayout();

		// Colocando a InvestimentosOverview na tela inicial
		showInvestimentosOverview(molduraController.areaDeTrabalhoBorderPane);

	}

	/**
	 * Retorna o palco principal.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void retornarATelaInicial() {
		showInvestimentosOverview(molduraController.areaDeTrabalhoBorderPane);
	}

	// Para ser chamado de fora sem ter que passar parâmetros
	public void atualizarTelaInvestimentos() {
		retornarATelaInicial();
	}

	/**
	 * Inicializa o root layout (layout base).
	 */
	public void initRootLayout() {
		try {
			// Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/moldura.fxml"));
			rootLayout = (AnchorPane) loader.load();

			MolduraController controller = loader.getController();
			controller.setMainApp(this);
			this.molduraController = controller;

			// Mostra a scene (cena) contendo o root layout.
			Scene scene = new Scene(rootLayout);
			addPersonalStyle(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra o person overview dentro do root layout.
	 */
	public void showInvestimentosOverview(BorderPane areaDeTrabalhoBorderPane) {
		try {
			// Carrega o person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/investimentosOverview.fxml"));
			AnchorPane investimentosOverview = (AnchorPane) loader.load();

			InvestimentosController controller = loader.getController();
			controller.setMainApp(this);

			// Define o person overview dentro do root layout.
			areaDeTrabalhoBorderPane.setCenter(investimentosOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCadastrarInvestimento(BorderPane areaDeTrabalhoBorderPane) {
		try {
			// Carrega o person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/cadastrarInvestimentoOverview.fxml"));
			AnchorPane cadInvOverView = (AnchorPane) loader.load();

			// Atribuindo o controller e o mainApp
			CadastrarInvestimentoController controller = loader.getController();
			controller.setMainApp(this);

			// Define o person overview dentro do root layout.
			areaDeTrabalhoBorderPane.setCenter(cadInvOverView);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showCadastrarVariacaoOverview() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/cadastrarVariacaoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Dá ao controlador acesso ao MainApp
			CadastrarVariacaoController controller = loader.getController();
			controller.setMainApp(this);

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Variação");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu próprio dialogStage
			controller.setDialogStage(dialogStage);

			// Show
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAtualizarInvestimentoOverview() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/cadastrarInvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Dá ao controlador acesso ao MainApp
			CadastrarInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			controller.povoarFormulario(MainApp.investimentoSelecionado);

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu próprio dialogStage
			controller.setDialogStage(dialogStage);

			// Show
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void excluirInvestimento() {

		Alert alert = new Alert(AlertType.CONFIRMATION);

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("view/modenaDark.css").toExternalForm());
		dialogPane.getStyleClass().add("view/modenaDark.css");

		alert.setTitle("Deseja Deletar o Investimento?");
		alert.setHeaderText("");
		alert.setContentText(
				"Ao clicar em \"Ok\" você estará APAGANDO TODOS OS DADOS DESSE INVESTIMENTO SEM POSSIBILIDADE DE RECUPERA-LOS.\n\n\n"
						+ "Você tem certeza desta deleção?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			// Deletando Investimento Do Banco
			InvestimentoDAO.deletarInvestimento(investimentoSelecionado);

			// Atualizando a View
			atualizarTelaInvestimentos();

		}
	}

}