package com.util;


import com.MainApp;
import com.model.Investimento;
import com.model.Variacao;

import javafx.beans.property.SimpleStringProperty;

public class CalcularVariacao {

	
	
public static String calc(Variacao v, Investimento i){
		
		
		
		double vInicial = new Double(i.getValor());
		double vFinal = new Double(v.getValor());
		
		double variacao = ((100*vFinal)/vInicial)-100;
		
		//NÃO É DINEHRIO É PORCENTAGEM
		return String.format("%.2f", variacao);
		
	}
	
	private static Investimento getInvestimentoById(String id){
		for (Investimento i : MainApp.listaInvestimentos) {
			if(i.getId().equalsIgnoreCase(id))
				return i;
		}
		return null;
	}
	
	public static SimpleStringProperty getLucroProperty(String i,String f){
		
		double valorI = new Double(i);
		double valorF = new Double(f);
		double lucro = valorF - valorI;
		String lucroString = MascaraFinanceira.show(lucro);
		return new SimpleStringProperty(lucroString);
		
	}
}
