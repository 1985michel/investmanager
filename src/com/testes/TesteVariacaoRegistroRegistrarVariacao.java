package com.testes;

import com.crud.InvestimentoDAO;
import com.crud.VariacaoRegistroDAO;
import com.model.Investimento;
import com.model.VariacaoRegistro;

public class TesteVariacaoRegistroRegistrarVariacao {

	public static void main(String[] args) {

		Investimento i = new Investimento("Yan", "50", "2016-12-04", "japão", "0", "0");
		InvestimentoDAO.investir(i);

		VariacaoRegistro v = new VariacaoRegistro(i, "2016-12-04", "99.96");
		VariacaoRegistroDAO.registrarVariacao(v);

		System.out.println(v);
	}

}
