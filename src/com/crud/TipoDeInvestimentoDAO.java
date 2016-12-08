package com.crud;

import java.sql.ResultSet;

import com.model.TipoDeInvestimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TipoDeInvestimentoDAO {

	public static void criarNovoTipo(TipoDeInvestimento tipo) {

		int id = 0;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("INSERT INTO TIPODEINVESTIMENTO (nome,idCalculadora) VALUES ('"
					+ tipo.getNome() + "','" + tipo.getCalculadoraDeCustos().getId() + "');CALL IDENTITY();");

			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				tipo.setId(id + "");
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

	public static ObservableList<TipoDeInvestimento> getTodosTiposDeInvestimentos() {

		int id = 0;
		ObservableList<TipoDeInvestimento> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM TIPODEINVESTIMENTO");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(
						new TipoDeInvestimento(idt, resultSet.getString("nome"), resultSet.getString("idCalculadora")));
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

	public static TipoDeInvestimento getTipoDeInvestimentoPorId(String idTipoInv) {

		TipoDeInvestimento tipo = null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM TIPODEINVESTIMENTO WHERE id= '" + idTipoInv + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				tipo = new TipoDeInvestimento(idt, resultSet.getString("nome"), resultSet.getString("idCalculadora"));
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

		return tipo;

	}

	public static void atualizarTipoDeInvestimento(TipoDeInvestimento tipo) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud
					.getResultSet("UPDATE TIPODEINVESTIMENTO SET nome= '" + tipo.getNome() + "', idCalculadora= '"
							+ tipo.getCalculadoraDeCustos().getId() + "' WHERE id='" + tipo.getId() + "'");

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

	public static void deletarTipoDeInvestimento(TipoDeInvestimento tipo) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM TIPODEINVESTIMENTO WHERE id= '" + tipo.getId() + "'");
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
