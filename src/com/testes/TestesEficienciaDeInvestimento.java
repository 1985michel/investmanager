package com.testes;

import java.util.List;

import com.calculosDeEficiencia.EficienciaDeInvestimento;
import com.crud.InvestimentoDAO;
import com.model.Investimento;

public class TestesEficienciaDeInvestimento {
	
	public static void main(String[] args){
		
		List<Investimento> list = InvestimentoDAO.getTodosInvestimentos();
		
		EficienciaDeInvestimento eficiencia = new EficienciaDeInvestimento(list);
		eficiencia.getEficiencia(InvestimentoDAO.getInvestimentoPeloId("23"));
		//System.out.println(eficiencia.periodoMaisLongo);
		//System.out.println("Eficiencia: "+eficiencia.getEficiencia(list.get(0)));
	}

}
