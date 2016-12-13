package com.model.metas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.util.EstruturaData;

/**
 * Classe responsável por realizar os calculos necessários ao acompanhamento das
 * metas
 * 
 */
public class AcompanhamentoDeMeta {

	long qtdDeMeses;
	double parcelas;
	double acumulado;

	public AcompanhamentoDeMeta(Meta meta, List<Meta> subMetas) {
		
		this.qtdDeMeses = EstruturaData.getQtdMesesFromNow(meta.getData());
		this.parcelas = new Double(meta.getValor())/qtdDeMeses;
		
		ArrayList<LocalDate> datas = geradoraDeDatas();
		
		for (int i = 0; i < qtdDeMeses; i++) {
			subMetas.add(new Meta(EstruturaData.estruturaData(datas.get(i)), this.parcelas));			
		}

	}

	public long getQtdDeMeses() {
		return qtdDeMeses;
	}

	public double getParcelas() {
		return parcelas;
	}
	

	public void setParcelas(double parcelas) {
		this.parcelas = parcelas;
	}
	
	private ArrayList<LocalDate> geradoraDeDatas(){
		ArrayList<LocalDate> datas = new ArrayList<>();		
				
		for (int i = 0; i < this.qtdDeMeses; i++) {
			datas.add(LocalDate.now().plusMonths(i));
		}
		
		return datas;
	}

	

}
