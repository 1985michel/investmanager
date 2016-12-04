package com.testes;

import com.crud.InvestimentoDAO;
import com.model.Investimento;
import com.model.Variacao;
import com.util.CalcularVariacao;

public class TesteCalcularVariacao {
	
	public static void main(String args[]){
		Investimento i = InvestimentoDAO.getTodosInvestimentos().get(0);
		i.setValor("10");
		Variacao v = new Variacao(i.getId(), "04/12/2016", "15");
		System.out.println(CalcularVariacao.calc(v,i));
	}

}
