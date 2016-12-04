package com.testes;

import com.crud.VariacaoRegistroDAO;
import com.model.VariacaoRegistro;

public class TesteGetTodasVariacoesRegistro {

	public static void main(String[] args) {
		for (VariacaoRegistro v : VariacaoRegistroDAO.getTodasVariacoesRegistro()) {
			System.out.println(v);
		}

	}

}
