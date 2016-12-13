package com.model.metas;

import com.util.MascaraFinanceira;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe responsável por descrever cada submeta necessária para o atingimento da meta na data proposta
 * As submetas são geradas automaticamente pelo AcompanhamentoDeMetas
 * 
 * */

public class Meta{
	
	StringProperty data;
	StringProperty valor;
	StringProperty descricao;
	
	Meta(String data,String valor){
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleStringProperty(valor);
		this.descricao = new SimpleStringProperty("Ter uma Carteira de Investimentos de R$ "+MascaraFinanceira.formataMoeda(new Double(valor))+" até "+data);
	}
	
	Meta(String data,double valor){
		this(data,String.valueOf(valor));
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
	
	public final void setValor(final double valor) {
		this.valor = new SimpleStringProperty(String.valueOf(valor));
	}
	
	public final StringProperty descricaoProperty() {
		return this.descricao;
	}
	
	public final java.lang.String getDescricao() {
		return this.descricaoProperty().get();
	}
	
	public final void setDescricao(final java.lang.String descricao) {
		this.descricaoProperty().set(descricao);
	}
	
	
	

}
