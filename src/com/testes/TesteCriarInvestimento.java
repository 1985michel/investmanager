package com.testes;

import com.crud.InvestimentoDAO;
import com.model.Investimento;

public class TesteCriarInvestimento {

	public static void main(String[] args) {
		
		
		Investimento inv = new Investimento("Euro", "999.50", "2016-12-04", "Viajar");
		InvestimentoDAO.investir(inv);
				
		
		
		Investimento inv2 = new Investimento("Poupança BB", "100.2", "2016-12-04", "Reserva de Emergências");
		InvestimentoDAO.investir(inv2);
		
		Investimento inv3 = new Investimento("Ações", "27.35", "2016-12-04", "Risco");
		InvestimentoDAO.investir(inv3);
		
		for (Investimento i : InvestimentoDAO.getTodosInvestimentos()) {
			System.out.println(i);
		}
		
		

	}

}
