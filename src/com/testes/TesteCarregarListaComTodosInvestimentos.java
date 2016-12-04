package com.testes;

import com.crud.InvestimentoDAO;
import com.model.Investimento;

public class TesteCarregarListaComTodosInvestimentos {

	public static void main(String[] args) {

		for (Investimento i : InvestimentoDAO.getTodosInvestimentos()) {
			System.out.println(i);
		}

	}

}
