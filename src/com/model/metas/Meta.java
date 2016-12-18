package com.model.metas;

import com.util.MascaraFinanceira;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe responsável por descrever cada submeta necessária para o atingimento da meta na data proposta
 * As submetas são geradas automaticamente pelo AcompanhamentoDeMetas
 * 
 * */

public class Meta{
	
	StringProperty data;
	StringProperty investimentoNecessario;
	StringProperty descricao;
	StringProperty investimentoRealizado;
	BooleanProperty cumprida;
	
	Meta(String data, double valorInicial, String parcela){
		this.data = new SimpleStringProperty(data);
		this.investimentoNecessario = new SimpleStringProperty(parcela);
		this.descricao = new SimpleStringProperty("Ter uma Carteira de Investimentos de R$ "+MascaraFinanceira.formataMoeda(new Double(valorInicial+parcela))+" até "+data);
	}
	
	Meta(String data, double valorInicial, double parcela){
		this(data,valorInicial,String.valueOf(parcela));
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
	
	
	public final StringProperty descricaoProperty() {
		return this.descricao;
	}
	
	public final java.lang.String getDescricao() {
		return this.descricaoProperty().get();
	}
	
	public final void setDescricao(final java.lang.String descricao) {
		this.descricaoProperty().set(descricao);
	}

	public final StringProperty investimentoNecessarioProperty() {
		return this.investimentoNecessario;
	}
	

	public final java.lang.String getInvestimentoNecessario() {
		return this.investimentoNecessarioProperty().get();
	}
	

	public final void setInvestimentoNecessario(final java.lang.String investimentoNecessario) {
		this.investimentoNecessarioProperty().set(investimentoNecessario);
	}
	

	public final StringProperty investimentoRealizadoProperty() {
		return this.investimentoRealizado;
	}
	

	public final java.lang.String getInvestimentoRealizado() {
		return this.investimentoRealizadoProperty().get();
	}
		

	public final void setInvestimentoRealizado(final java.lang.String investimentoRealizado) {
		this.investimentoRealizadoProperty().set(investimentoRealizado);
	}
	
	public final void addInvestimentoRealizado(final double novoInvestimento) {
		try {
			double investimentoRealizado = new Double(getInvestimentoRealizado());
			investimentoRealizado+=novoInvestimento;
			this.investimentoRealizado = new SimpleStringProperty(String.valueOf(investimentoRealizado));
						
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public final BooleanProperty cumpridaProperty() {
		return this.cumprida;
	}
	

	public final boolean isCumprida() {
		return this.cumpridaProperty().get();
	}
	

	public final void setCumprida(final boolean cumprida) {
		this.cumpridaProperty().set(cumprida);
	}
	
	
	
	
	
	
	
	

}
