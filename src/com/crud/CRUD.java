package com.crud;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.exceptions.CRUDException;
import com.model.Usuario;

public class CRUD {

	static Usuario user;
	public String address;
	public static String diretorioDb = "D:/Program Files/investmanager/hsqldb-2.3.3/hsqldb/db/";

	public CRUD(){
		CRUD.user = new Usuario("michel", "livre");
		address = "jdbc:hsqldb:file:"+diretorioDb + user.getNome();
	}
	public CRUD(Usuario user) {
		CRUD.user = user;				
		address = "jdbc:hsqldb:file:"+diretorioDb + user.getNome();
		//address = "jdbc:hsqldb:file:D:/Program Files/wedoffSecurity/hsqldb-2.3.3/hsqldb/db/" + user.getNome();		
	}

	public ResultSet getResultSet(String strSql) throws SQLException, ClassNotFoundException, CRUDException {
		//Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		Class.forName("org.hsqldb.jdbcDriver");

		try(Connection connection = DriverManager.getConnection(this.address, user.getNome(), user.getSenha())){
			//Class.forName("org.hsqldb.jdbcDriver");
			//connection = DriverManager.getConnection(this.address, user.getNome(), user.getSenha());
			if(connection!= null) statement = connection.createStatement();
			else return null;

			if(statement!= null)resultSet = statement.executeQuery(strSql);

		} catch (Exception e) {
			e.printStackTrace();
			//throw new CRUDException("Houve um problema em obter os dados do banco.");
		} finally {
			// logou(logou);
			try {
				// resultSet.close();
				statement.close();
				//connection.close();
				return resultSet;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return resultSet;
	}
	
}
