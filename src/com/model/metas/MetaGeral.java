package com.model.metas;

import java.util.ArrayList;
import java.util.List;

public class MetaGeral{

	public Meta meta;
	public AcompanhamentoDeMeta acompanhamentoDeMeta;
	public List<Meta> submetas;
	

	public MetaGeral(String data, String valor) {
		this.meta = new Meta(data, valor);
		submetas = new ArrayList<>();
		this.acompanhamentoDeMeta = new AcompanhamentoDeMeta(this.meta,submetas);
	}
	
	public MetaGeral(String data, double valor, String descricao) {
		this(data, String.valueOf(valor));
	}
	
	
	
	
	
}
