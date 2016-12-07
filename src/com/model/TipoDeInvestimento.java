package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoDeInvestimento {

	private StringProperty id;
	private StringProperty nome;
	private CalculadoraDeCustos calculadoraDeCustos;

	/*
	public TipoDeInvestimento() {
	}

	public TipoDeInvestimento(String nome) {
		this.nome = new SimpleStringProperty(nome);
	}
	*/

	public TipoDeInvestimento(String nome, String idCalculadora) {
		this.nome = new SimpleStringProperty(nome);
		this.calculadoraDeCustos = new CalculadoraDeCustos(idCalculadora);
	}

	public TipoDeInvestimento(String id, String nome, String idCalculadora) {
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);
		
		//Para testes
		this.calculadoraDeCustos = new CalculadoraDeCustos(idCalculadora); 
	}

	public CalculadoraDeCustos getCalculadoraDeCustos() {
		return calculadoraDeCustos;
	}

	public void setCalculoDeCustos(String idCalculadora) {
		
		//Para testes
		this.calculadoraDeCustos = new CalculadoraDeCustos(idCalculadora);
	}

	public final StringProperty idProperty() {
		return this.id;
	}

	public final String getId() {
		return this.id.get();
	}

	public final void setId(String id) {
		this.id = new SimpleStringProperty(id);
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

	@Override
	public String toString() {
		//return "TipoDeInvestimento [id=" + id + ", nome=" + nome + ", calculadoraDeCustos=" + calculadoraDeCustos.getId() + "]";
		return this.getNome();
	}
	
	

}
