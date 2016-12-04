package com.model;

import com.util.CalcularVariacao;
import com.util.MascaraFinanceira;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Investimento {

	private StringProperty id;
	private StringProperty nome;
	private StringProperty valor;
	private StringProperty data;
	private StringProperty plano;
	private Variacao variacao;

	public Investimento() {
	}

	public Investimento(String nome, String valor, String data, String plano) {
		this.nome = new SimpleStringProperty(nome);
		this.valor = new SimpleStringProperty(valor);
		this.data = new SimpleStringProperty(data);
		this.plano = new SimpleStringProperty(plano);

		// Definindo a variação
		this.variacao = new Variacao(this.getData(), this.getValor());
		this.variacao.setVariacao("0");

	}

	public Investimento(String id, String nome, String valor, String data, String plano) {
		this.id = new SimpleStringProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.valor = new SimpleStringProperty(valor);
		this.data = new SimpleStringProperty(data);
		this.plano = new SimpleStringProperty(plano);
		// Definindo a variação
		this.variacao = new Variacao(this.getData(), this.getValor());
		this.variacao.setVariacao("0");
	}

	public final StringProperty nomeProperty() {
		return this.nome;
	}

	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(String nome) {
		this.nomeProperty().set(nome);
	}

	public final StringProperty valorProperty() {
		return this.valor;		
	}

	public final java.lang.String getValor() {
		return this.valorProperty().get();
	}

	public final void setValor(final java.lang.String valor) {
		this.valorProperty().set(valor);
	}

	public final StringProperty dataProperty() {
		return this.data;
	}

	public final java.lang.String getData() {
		return this.dataProperty().get();
	}

	public final void setData(String data) {
		this.dataProperty().set(data);
	}

	public final StringProperty planoProperty() {
		return this.plano;
	}

	public final java.lang.String getPlano() {
		return this.planoProperty().get();
	}

	public final void setPlano(String plano) {
		this.planoProperty().set(plano);
	}

	public final StringProperty idProperty() {
		return this.id;
	}

	public final String getId() {
		return this.idProperty().get();
	}

	public final void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}

	public Variacao getVariacao() {
		return variacao;
	}

	
	//Não é dinheiro, é porcentagem
	public void setVariacao(Variacao variacao) {
		this.variacao = variacao;
		variacao.setVariacao(this);// Passando o proprio investimento para
									// calculo da variação

	}
	
	//Já apliquei a mascara financeira
	public final StringProperty getLucroProperty() {
		return  CalcularVariacao.getLucroProperty(this.getValor(), this.variacao.getValor());
	}

	@Override
	public String toString() {
		return "Investimento [id=" + id + ", nome=" + nome + ", valor=" + valor + ", data=" + data + ", plano=" + plano
				+ "]";
	}

}
