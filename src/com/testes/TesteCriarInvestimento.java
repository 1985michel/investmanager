package com.testes;

import java.util.List;

import com.crud.InvestidorDAO;
import com.crud.InvestimentoDAO;
import com.crud.TipoDeInvestimentoDAO;
import com.model.Investidor;
import com.model.Investimento;
import com.model.TipoDeInvestimento;

public class TesteCriarInvestimento {

	public static void main(String[] args) {
		
		Investidor iU2 = InvestidorDAO.getInvestidorPeloId("1");
		
		TipoDeInvestimento ti = TipoDeInvestimentoDAO.getTipoDeInvestimentoPorId("0");
		
		Investimento inv = new Investimento("Teste", "10", "2016-12-04", "Viajar",ti.getId(),iU2.getId());
		//InvestimentoDAO.investir(inv);
			
		System.out.println(inv);
		//for (Investimento i : InvestimentoDAO.getTodosInvestimentos()) {
		//	System.out.println(i);
		//}
		
		

	}

}
