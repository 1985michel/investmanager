package com.calculosDeEficiencia;

import java.util.List;

import com.model.Investimento;
import com.util.EstruturaData;

public class EficienciaDeInvestimento {
	
	
	private List<Investimento>lista;
	public long periodoMaisLongo;
	
	public EficienciaDeInvestimento(List<Investimento> lista){
		this.lista = lista;
		setaPeriodoMaisLongo();
	}
	
	public void calcularEficiencia(){
		for (Investimento i : lista) {
			i.setEficiencia(getEficiencia(i));
		}		
	}
	
	private void setaPeriodoMaisLongo() {
		long p = 0l;
		if(lista!=null && lista.size()>0)
			p = EstruturaData.getQtdDias(lista.get(0).getData());

		for (Investimento i : lista) {
			long dias = EstruturaData.getQtdDias(i.getData());
			if(dias>p)
				p = dias;
		}		
		periodoMaisLongo = p;		
	}

	/*
	 * 
	 * */
	public String getEficiencia(Investimento i){
		
		//Primeiro obtenho a quantidade de dias da aplicação
		long tempoEmDias = EstruturaData.getQtdDias(i.getData());
		
		//System.out.println("Periodo mais longo: "+periodoMaisLongo);
		//System.out.println("Tempo aplicado: "+tempoEmDias);
		
		
		//Depois vamos projetar a lucratividade no período mais longo
		 
		double var = new Double((i.getVariacao().getVariacao()).replace(',', '.'));
		//System.out.println("Variação: "+var);
		
		
		double resultado = (var*periodoMaisLongo)/tempoEmDias;
		//double resultado = 10;
		//System.out.println("Resultado: "+resultado);		
		return String.format("%.2f", resultado);
	}

	

}
