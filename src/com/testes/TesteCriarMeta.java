package com.testes;

import java.time.LocalDate;

import com.model.metas.Meta;
import com.model.metas.MetaGeral;
import com.util.EstruturaData;

public class TesteCriarMeta {
	
	public static void main(String... args){
		
		double valor = 12000;
		String data = "2017-12-31";
		
		MetaGeral mg = new MetaGeral(data,valor,"Juntar R$ "+valor+" até "+data);
		
		
		System.out.println(mg.acompanhamentoDeMeta.getQtdDeMeses());
		
		System.out.println(mg.acompanhamentoDeMeta.getParcelas());
		
		int i =1;
		for (Meta m : mg.submetas) {
			System.out.println(i+" - "+m.getDescricao());
			i++;
		}
		
	}

}
