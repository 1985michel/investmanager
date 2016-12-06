package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoDeInvestimento {
	
	private StringProperty id;
	private StringProperty nome;
	private CalculadoraDeCustos calculoDeCustos;
	
	public TipoDeInvestimento(){}
	
	public TipoDeInvestimento(String nome){
		this.nome = new SimpleStringProperty(nome);		
	}
	
	public TipoDeInvestimento(String id,String nome){
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.calculoDeCustos = new CalculadoraDeCustos();
	}
	
	

	public CalculadoraDeCustos getCalculoDeCustos() {
		return calculoDeCustos;
	}

	public void setCalculoDeCustos(CalculadoraDeCustos calculoDeCustos) {
		this.calculoDeCustos = calculoDeCustos;
	}

	public final StringProperty idProperty() {
		return this.id;
	}
	

	public final java.lang.String getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final java.lang.String id) {
		this.idProperty().set(id);
	}
	

	public final StringProperty nomeProperty() {
		return this.nome;
	}
	

	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}
	

	public final void setNome(final java.lang.String nome) {
		this.nomeProperty().set(nome);
	}
	
	
	

}
