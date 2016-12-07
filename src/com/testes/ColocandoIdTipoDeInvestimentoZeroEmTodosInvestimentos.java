package com.testes;

import java.util.List;

import com.crud.InvestidorDAO;
import com.crud.InvestimentoDAO;
import com.crud.TipoDeInvestimentoDAO;
import com.model.Investidor;
import com.model.Investimento;
import com.model.TipoDeInvestimento;

public class ColocandoIdTipoDeInvestimentoZeroEmTodosInvestimentos {
	
	public static void main(String[] args){
		
		/*
		TipoDeInvestimento ti = new TipoDeInvestimento("Poupança", "0");
		TipoDeInvestimentoDAO.criarNovoTipo(ti);
		*/
		Investidor iU = InvestidorDAO.getInvestidorPeloId("0");
	
		System.out.println(iU);
		iU.setSenha(" ");
		
		
		
		List<Investimento> list = InvestimentoDAO.getTodosInvestimentos();
		for (Investimento i : list) {
			i.setInvestidor(iU);
			InvestimentoDAO.atualizarInvestimento(i);
		}
		
	}

}
