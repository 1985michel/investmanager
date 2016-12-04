package com.testes;

import com.crud.VariacaoRegistroDAO;
import com.model.VariacaoRegistro;

public class TesteGetUltimaVariacaoRegistroPorInvestimento {

	public static void main(String[] args) {

		VariacaoRegistro vr = VariacaoRegistroDAO.getUltimaVariacaoPorRegistroPorInvestimento("0");
		System.out.println(vr);

	}

}
