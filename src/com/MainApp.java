package com;

import java.io.IOException;
import java.util.List;

import com.model.Investimento;
import com.view.CadastrarInvestimentoController;
import com.view.InvestimentosController;
import com.view.MolduraController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private MolduraController molduraController;
	public static List<Investimento> listaInvestimentos;
		

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("InvestManager - Consquistando sua Liberdade Financeira!");

		initRootLayout();
		
		//Colocando a InvestimentosOverview na tela inicial
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
	
	public void retornarATelaInicial(){
		showInvestimentosOverview(molduraController.areaDeTrabalhoBorderPane);
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
            
            //Atribuindo o controller e o mainApp
            CadastrarInvestimentoController controller = loader.getController();
            controller.setMainApp(this);

            // Define o person overview dentro do root layout.
            areaDeTrabalhoBorderPane.setCenter(cadInvOverView);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
	

	
}