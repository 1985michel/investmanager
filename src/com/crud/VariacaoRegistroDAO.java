package com.crud;

import java.sql.ResultSet;
import java.util.List;

import com.model.Investimento;
import com.model.VariacaoRegistro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VariacaoRegistroDAO {

	public static void registrarVariacao(VariacaoRegistro v) {

		int id = 0;

		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet(
					"INSERT INTO VARIACAOREGISTRO (idinvestimento,data,valor) VALUES ('" + v.getIdInvestimento() + "','"
							+ v.getData() + "','" + v.getValor() + "');CALL IDENTITY();");

			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				v.setId(id + "");
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

	public static ObservableList<VariacaoRegistro> getTodasVariacoesRegistro() {

		int id = 0;
		ObservableList<VariacaoRegistro> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM VARIACAOREGISTRO");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new VariacaoRegistro(idt, resultSet.getString("idInvestimento"), resultSet.getString("data"),
						resultSet.getString("valor")));
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

	public static ObservableList<VariacaoRegistro> getVariacoesRegistroPorInvestimento(String idI) {

		int id = 0;
		ObservableList<VariacaoRegistro> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM VARIACAOREGISTRO WHERE idINVESTIMENTO = '" + idI + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new VariacaoRegistro(idt, resultSet.getString("idInvestimento"), resultSet.getString("data"),
						resultSet.getString("valor")));
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

	public static void atualizarVariacaoRegistro(VariacaoRegistro v) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud.getResultSet("UPDATE VARIACAOREGISTRO SET idInvestimento= '" + v.getIdInvestimento()
					+ "', data= '" + v.getData() + "', valor= '" + v.getValor() + "' WHERE id='" + v.getId() + "'");

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

	public static void deletarVariacaoRegistro(VariacaoRegistro v) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM VARIACAOREGISTRO WHERE id= '" + v.getId() + "'");
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
	
	public static VariacaoRegistro getUltimaVariacaoPorRegistroPorInvestimento(String idI) {

		try {
			List<VariacaoRegistro> listVR = VariacaoRegistroDAO.getVariacoesRegistroPorInvestimento(idI);
			return listVR.get(listVR.size()-1);
		} catch (Exception e) {
			return null;
		}
		

	}

	
	

}
