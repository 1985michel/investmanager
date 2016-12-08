package com.util;

import java.time.LocalDate;

import com.model.Investimento;

import javafx.collections.ObservableList;

public class OrdenaListDeInvestimentosPorData {

	/** In�cio dos m�todos para ordenar os Investimentos por data */
	public static void ordenaInvestimentosPorData(ObservableList<Investimento> list) {
		list.sort((o1, o2) -> comparaDatas(geraData(o1.getData()), geraData(o2.getData())));
	}

	private static LocalDate geraData(String data) {
		//System.out.println("data: " + data);
		if (data != null && data.length() == 10) {

			String[] estru = data.split("-");
			int ano = Integer.parseInt(estru[0]);
			int mes = Integer.parseInt(estru[1]);
			int dia = Integer.parseInt(estru[2]);
			LocalDate date = LocalDate.of(ano, mes, dia);
			return date;
		} else
			return null;

	}

	private static int comparaDatas(LocalDate data1, LocalDate data2) {
		if (data1.isEqual(data2))
			return 0;
		else if (data1.isBefore(data2))
			return -1;
		else
			return 1;
	}

	/** Fim dos m�todos para odenar os investimentos por data */

}
