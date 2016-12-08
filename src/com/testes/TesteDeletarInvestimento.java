package com.testes;

import com.crud.InvestimentoDAO;
import com.model.Investimento;

public class TesteDeletarInvestimento {

	public static void main(String arg[]) {
		Investimento i = InvestimentoDAO.getTodosInvestimentos().get(0);
		System.out.println(i);

		InvestimentoDAO.deletarInvestimento(i);
		Investimento i2 = InvestimentoDAO.getTodosInvestimentos().get(0);
		System.out.println(i2);
	}

}
