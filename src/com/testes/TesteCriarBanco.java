package com.testes;

import java.sql.SQLException;

import com.crud.CRUD;
import com.crud.DBFactory;
import com.exceptions.CRUDException;

public class TesteCriarBanco {
	
	public static void main(String...args){
		DBFactory dbf = new DBFactory();
		try {
			dbf.criarBancos(new CRUD());
		} catch (ClassNotFoundException | SQLException | CRUDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
