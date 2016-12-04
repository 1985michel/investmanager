package com.testes;

import com.crud.InvestimentoDAO;
import com.model.Investimento;

public class TesteAtualizarInvestimento {

	public static void main(String[] args) {
		
		
		Investimento i = InvestimentoDAO.getTodosInvestimentos().get(0);
		i.setNome("Prata");
		i.setData("08/03/1985");
		i.setPlano("Viver 100 anos");
		i.setValor("500");
		InvestimentoDAO.atualizarInvestimento(i);
		
		System.out.println(InvestimentoDAO.getTodosInvestimentos().get(0));
	}

}
