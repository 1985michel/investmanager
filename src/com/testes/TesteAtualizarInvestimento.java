package com.testes;

import com.crud.InvestimentoDAO;
import com.crud.TipoDeInvestimentoDAO;
import com.model.Investimento;

public class TesteAtualizarInvestimento {

	public static void main(String[] args) {

		Investimento i = InvestimentoDAO.getInvestimentoPeloId("2");
		i.setTipoInvestimento(TipoDeInvestimentoDAO.getTipoDeInvestimentoPorId("3"));
		InvestimentoDAO.atualizarInvestimento(i);

		System.out.println(InvestimentoDAO.getInvestimentoPeloId("2"));
	}

}
