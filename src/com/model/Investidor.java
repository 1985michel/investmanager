package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Investidor {

	private StringProperty id;
	private StringProperty nome;
	private StringProperty senha;
	
	
	
	public Investidor(String nome, String senha) {
		super();
		this.nome = new SimpleStringProperty(nome);
		if(senha==null || senha == "")
			senha = "0";
		this.senha = new SimpleStringProperty(senha);
	}
	
	public Investidor(String id, String nome, String senha) {
		super();
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.senha = new SimpleStringProperty(senha);
	}



	public final StringProperty nomeProperty() {
		return this.nome;
	}
	



	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}
	



	public final void setNome(final java.lang.String nome) {
		this.nome = new SimpleStringProperty(nome);
	}
	
	
	public final StringProperty idProperty() {
		return this.id;
	}
	



	public final java.lang.String getId() {
		return this.id.getValue();
	}
	



	public final void setId(final java.lang.String id) {
		this.id = new SimpleStringProperty(id);
	}
	



	public final StringProperty senhaProperty() {
		return this.senha;
	}
	



	public final java.lang.String getSenha() {
		return this.senhaProperty().get();
	}
	



	public final void setSenha(final java.lang.String senha) {
		this.senha = new SimpleStringProperty(senha);
	}

	@Override
	public String toString() {
		return  nome.get();
	}
	
	@Override
	public boolean equals(Object that){
	  if(this == that) return true;//if both of them points the same address in memory

	  if(!(that instanceof Investidor)) return false; // if "that" is not a People or a childclass

	  Investidor thatPeople = (Investidor)that; // than we can cast it to People safely

	  return this.getNome().equals(thatPeople.getNome()) && this.getSenha().equals(thatPeople.getSenha());// if they have the same name and same age, then the 2 objects are equal unless they're pointing to different memory adresses
	}
	
	
	
	
	
	
	
}