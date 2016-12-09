package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CalculadoraDeCustos {

	private StringProperty id;

	public CalculadoraDeCustos() {
	}

	public CalculadoraDeCustos(String id) {
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
	
	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;// if both of them points the same address in memory

		if (!(that instanceof CalculadoraDeCustos))
			return false; // if "that" is not a People or a childclass

		CalculadoraDeCustos thatCalculadora = (CalculadoraDeCustos) that; // than we can cast it to
													// People safely

		return this.getId().equals(thatCalculadora.getId());
	}

}
