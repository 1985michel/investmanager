package com.testes;

import java.time.LocalDate;

import com.model.metas.Meta;
import com.model.metas.MetaGeral;
import com.util.EstruturaData;

public class TesteCriarMeta {
	
	public static void main(String... args){
		
		double valorInicial = 0;
		double valorDesejado = 12000;
		
		String data = "2017-12-31";
		
		MetaGeral mg = new MetaGeral(data,valorInicial,valorDesejado,"Juntar R$ "+valorDesejado+" até "+data);
		
		
		System.out.println("Nº de meses: "+mg.acompanhamentoDeMeta.getQtdDeMeses());
		
		System.out.println("Investimento Mensal Necessário: "+mg.acompanhamentoDeMeta.getParcelas());
		
		int i =1;
		for (Meta m : mg.submetas) {
			System.out.println(i+" - "+m.getDescricao());
			i++;
		}
		
	}

}
