package com.testes;

import com.crud.InvestimentoDAO;
import com.model.Investimento;

public class TesteGetInvestimentoPeloId {

	public static void main(String[] args) {
		
		Investimento i = InvestimentoDAO.getInvestimentoPeloId("6");
		System.out.println(i);

	}

}
