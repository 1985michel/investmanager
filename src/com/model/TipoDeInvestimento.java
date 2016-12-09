package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoDeInvestimento {

	private StringProperty id;
	private StringProperty nome;
	private CalculadoraDeCustos calculadoraDeCustos;

	/*
	 * public TipoDeInvestimento() { }
	 * 
	 * public TipoDeInvestimento(String nome) { this.nome = new
	 * SimpleStringProperty(nome); }
	 */

	public TipoDeInvestimento(String nome, String idCalculadora) {
		this.nome = new SimpleStringProperty(nome);
		this.calculadoraDeCustos = new CalculadoraDeCustos(idCalculadora);
	}

	public TipoDeInvestimento(String id, String nome, String idCalculadora) {
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);

		// Para testes
		this.calculadoraDeCustos = new CalculadoraDeCustos(idCalculadora);
	}

	public CalculadoraDeCustos getCalculadoraDeCustos() {
		return calculadoraDeCustos;
	}

	public void setCalculoDeCustos(String idCalculadora) {

		// Para testes
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
		// return "TipoDeInvestimento [id=" + id + ", nome=" + nome + ",
		// calculadoraDeCustos=" + calculadoraDeCustos.getId() + "]";
		return this.getNome();
	}
	
	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;// if both of them points the same address in memory

		if (!(that instanceof TipoDeInvestimento))
			return false; // if "that" is not a People or a childclass

		TipoDeInvestimento thatPeople = (TipoDeInvestimento) that; // than we can cast it to
													// People safely

		return this.getNome().equals(thatPeople.getNome()) && this.getCalculadoraDeCustos().equals(thatPeople.getCalculadoraDeCustos());
	}

}
