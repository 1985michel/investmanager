package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CalculadoraDeCustos {
	
	private StringProperty id;
	
	public CalculadoraDeCustos(){}
	
	public CalculadoraDeCustos(String id){
		this.id = new SimpleStringProperty(id);
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
	
	
	

}
