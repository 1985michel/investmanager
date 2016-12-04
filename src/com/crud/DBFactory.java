package com.crud;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.CRUDException;



public class DBFactory {

	private List<String> comandosDeCriacao = new ArrayList<>();

	public DBFactory() {
		// String criarTabelaUsuarios = "CREATE TABLE USUARIOS (" + "id INTEGER
		// IDENTITY PRIMARY KEY,"
		// + "LOGIN VARCHAR(50)," + "senha VARCHAR(100)," + ");";

		String criarTabelaUsuarios = "CREATE TABLE USUARIOS (" + "id INTEGER IDENTITY PRIMARY KEY,"
				+ "LOGIN VARCHAR(50)," + "senha VARCHAR(100)," + ");";
		
		this.comandosDeCriacao.add(criarTabelaUsuarios);
		
		
		String criarTabelaInvestimento = "CREATE TABLE INVESTIMENTO (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "nome VARCHAR(200)," + "valor VARCHAR(50)," + "data VARCHAR(200)," + "plano VARCHAR(5000)," + ");";

		this.comandosDeCriacao.add(criarTabelaInvestimento);
		
		String criarTabelaVariacao = "CREATE TABLE VARIACAO (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "idInvestimento VARCHAR(10)," + "data VARCHAR(10)," + "valor VARCHAR(50)," + "plano VARCHAR(5000)," + ");";

		this.comandosDeCriacao.add(criarTabelaVariacao);

		
	}
		

	public boolean criarBancos(CRUD crud) throws ClassNotFoundException, SQLException, CRUDException {

		for (String comando : comandosDeCriacao) {
			crud.getResultSet(comando);
		}
		return true;
	}
	


}
