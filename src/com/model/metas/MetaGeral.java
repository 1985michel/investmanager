package com.model.metas;

import java.util.ArrayList;
import java.util.List;

public class MetaGeral{

	public Meta meta;
	public AcompanhamentoDeMeta acompanhamentoDeMeta;
	public List<Meta> submetas;
	public double valorInicial;
	

	public MetaGeral(String data, String valorInicial, String valorDesejado) {
		this.meta = new Meta(data,new Double(valorInicial), valorDesejado);
		submetas = new ArrayList<>();
		this.acompanhamentoDeMeta = new AcompanhamentoDeMeta(this.valorInicial,this.meta,submetas);
	}
	
	public MetaGeral(String data, double valorInicial, double valorDesejado, String descricao) {
		this(data,String.valueOf(valorInicial), String.valueOf(valorDesejado));
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}
	
	
	
	
	
}
