package com.testes;

import com.crud.InvestidorDAO;
import com.model.Investidor;

public class TesteCriarInvestidor {
	
	public static void main(String[] args){
		
		//Investidor iU = new Investidor("Miguel", "");
		//InvestidorDAO.registrarInvestidor(iU);
		
		Investidor iU2 = InvestidorDAO.getInvestidorPeloId("1");
		iU2.setNome("Familia");
		InvestidorDAO.atualizarInvestidor(iU2);
		
		iU2 = InvestidorDAO.getInvestidorPeloId("1");
		
		System.out.println(iU2);
		
		
		
		
	}

}
