package com.crud;

import java.sql.ResultSet;

import com.model.Investimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvestimentoDAO {

	public static void investir(Investimento i) {

		int id = 0;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("INSERT INTO INVESTIMENTO (nome,valor,data,plano) VALUES ('" + i.getNome()
					+ "','" + i.getValor() + "','" + i.getData() + "','" + i.getPlano() + "');CALL IDENTITY();");

			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				i.setId(id+"");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static ObservableList<Investimento> getTodosInvestimentos() {

		int id = 0;
		ObservableList<Investimento> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIMENTO");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id")+"";
				lista.add(new Investimento(idt, resultSet.getString("nome"),
						resultSet.getString("valor"), resultSet.getString("data"), resultSet.getString("plano")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		return lista;

	}
	
	public static Investimento getInvestimentoPeloId(String id) {

		
		Investimento i =null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIMENTO WHERE id= '" + id + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id")+"";
				i = new Investimento(idt, resultSet.getString("nome"),
						resultSet.getString("valor"), resultSet.getString("data"), resultSet.getString("plano"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return i;

	}
	
	public static void atualizarInvestimento(Investimento i) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud.getResultSet("UPDATE INVESTIMENTO SET nome= '" + i.getNome()
					+ "', valor= '" + i.getValor() + "', data= '"
					+ i.getData() +  "', plano= '"
							+ i.getPlano() +"' WHERE id='" + i.getId()
					+ "'");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static void deletarInvestimento(Investimento i) {
		
		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM INVESTIMENTO WHERE id= '" + i.getId() + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
