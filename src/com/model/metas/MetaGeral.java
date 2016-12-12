package com.model.metas;

import java.util.ArrayList;
import java.util.List;

public class MetaGeral extends Meta {

	AcompanhamentoDeMeta acompanhamentoDeMeta;
	List<Meta> submetas;
	

	MetaGeral(String data, String valor, String descricao) {
		super(data, valor);
		this.setDescricao(descricao);
		submetas = new ArrayList<>();
		this.acompanhamentoDeMeta = new AcompanhamentoDeMeta(submetas);
	}
	
	MetaGeral(String data, double valor, String descricao) {
		this(data, String.valueOf(valor), descricao);
	}
	
	
	
}
