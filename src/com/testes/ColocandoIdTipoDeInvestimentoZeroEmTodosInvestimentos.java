package com.testes;

import java.util.List;

import com.crud.InvestimentoDAO;
import com.crud.TipoDeInvestimentoDAO;
import com.model.Investimento;
import com.model.TipoDeInvestimento;

public class ColocandoIdTipoDeInvestimentoZeroEmTodosInvestimentos {
	
	public static void main(String[] args){
		TipoDeInvestimento ti = new TipoDeInvestimento("Poupança", "0");
		TipoDeInvestimentoDAO.criarNovoTipo(ti);
		
		
		
		List<Investimento> list = InvestimentoDAO.getTodosInvestimentos();
		for (Investimento i : list) {
			i.setTipoInvestimento(ti);
			InvestimentoDAO.atualizarInvestimento(i);
		}
	}

}
