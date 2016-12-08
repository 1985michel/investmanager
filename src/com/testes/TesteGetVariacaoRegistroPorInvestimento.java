package com.testes;

import com.crud.VariacaoRegistroDAO;
import com.model.VariacaoRegistro;

public class TesteGetVariacaoRegistroPorInvestimento {

	public static void main(String[] args) {

		for (VariacaoRegistro v : VariacaoRegistroDAO.getVariacoesRegistroPorInvestimento("13")) {
			System.out.println(v);
		}
		;

	}

}
