package com.model;

import com.util.CalcularVariacao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VariacaoRegistro extends Variacao {

	private StringProperty idInvestimento;
	protected StringProperty id;

	public VariacaoRegistro(String id, String idInvestimento, String data, String valor) {

		this.id = new SimpleStringProperty(id);
		this.idInvestimento = new SimpleStringProperty(idInvestimento);
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleStringProperty(valor);
		//this.variacao = new SimpleStringProperty(CalcularVariacao.calc(this,i));
	}

	public VariacaoRegistro(Investimento i, String data, String valor) {

		this.idInvestimento = new SimpleStringProperty(i.getId());
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleStringProperty(valor);
		this.variacao = new SimpleStringProperty(CalcularVariacao.calc(this,i));
	}
	
	public final StringProperty idProperty() {
		return this.id;
	}
	

	public final java.lang.String getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final java.lang.String id) {
		this.id = new SimpleStringProperty(id);
	}

	public final StringProperty idInvestimentoProperty() {
		return this.idInvestimento;
	}

	public final java.lang.String getIdInvestimento() {
		return this.idInvestimentoProperty().get();
	}

	public final void setIdInvestimento(final java.lang.String idInvestimento) {
		this.idInvestimentoProperty().set(idInvestimento);
	}

	@Override
	public String toString() {
		return "VariacaoRegistro [idInvestimento=" + idInvestimento + ", id=" + id + ", data=" + data + ", valor="
				+ valor + ", variacao=" + variacao + "]";
	}

	
}
