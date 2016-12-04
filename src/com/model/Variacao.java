package com.model;

import com.util.CalcularVariacao;
import com.util.MascaraFinanceira;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variacao {
	
	private StringProperty id;
	private StringProperty data;
	private StringProperty valor;
	private StringProperty variacao;
	
	public Variacao( String data, String valor) {
		
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleStringProperty(valor);
	}
	
	public Variacao(String id, String data, String valor) {
		
		this.id = new SimpleStringProperty(id);
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleStringProperty(valor);
	}

	public Variacao() {
		// TODO Auto-generated constructor stub
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
	
	public final StringProperty dataProperty() {
		return this.data;
	}
	
	public final java.lang.String getData() {
		return this.dataProperty().get();
	}
	
	public final void setData(final java.lang.String data) {
		this.dataProperty().set(data);
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

	public final StringProperty variacaoProperty() {
		return this.variacao;
	}
	

	public final java.lang.String getVariacao() {
		return this.variacaoProperty().get();
	}
	

	public final void setVariacao(Investimento i) {
		this.variacao =  new SimpleStringProperty(CalcularVariacao.calc(this,i));
	}
	
	
	
	public final void setVariacao(String var) {
		this.variacao = new SimpleStringProperty(var);
		
	}
	
	@Override
	public String toString() {
		return "Variacao [id=" + id + ", data=" + data + ", valor=" + valor
				+ ", variacao=" + variacao + "]";
	}
	
	
	

	
	
	
	
	
	
}
