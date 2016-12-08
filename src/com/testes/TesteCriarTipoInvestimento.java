package com.testes;

import java.util.List;

import com.crud.TipoDeInvestimentoDAO;
import com.model.TipoDeInvestimento;

public class TesteCriarTipoInvestimento {

	public static void main(String... args) {

		/*
		 * TipoDeInvestimento tInv = new TipoDeInvestimento("Teste", "0");
		 * TipoDeInvestimentoDAO.criarNovoTipo(tInv);
		 * 
		 * TipoDeInvestimento ti =
		 * TipoDeInvestimentoDAO.getTipoDeInvestimentoPorId(tInv.getId());
		 * 
		 * System.out.println(ti);
		 * 
		 * ti.setNome("teste atualizado 2");
		 * 
		 * TipoDeInvestimentoDAO.atualizarTipoDeInvestimento(ti);
		 * 
		 * System.out.println(ti);
		 * 
		 * 
		 */

		TipoDeInvestimento tInv = new TipoDeInvestimento("Poupança", "0");
		TipoDeInvestimentoDAO.criarNovoTipo(tInv);

		TipoDeInvestimento tInv2 = new TipoDeInvestimento("LCI", "0");
		TipoDeInvestimentoDAO.criarNovoTipo(tInv2);

		TipoDeInvestimento tInv3 = new TipoDeInvestimento("Tesouro", "0");
		TipoDeInvestimentoDAO.criarNovoTipo(tInv3);

		List<TipoDeInvestimento> lista = TipoDeInvestimentoDAO.getTodosTiposDeInvestimentos();
		for (TipoDeInvestimento ti : lista) {
			System.out.println(ti);
		}

		System.out.println("id manual: " + lista.get(0).getId());

		tInv3.setNome("Tesouro Direto");
		tInv3.setCalculoDeCustos("1");

		TipoDeInvestimentoDAO.atualizarTipoDeInvestimento(tInv3);

		lista = TipoDeInvestimentoDAO.getTodosTiposDeInvestimentos();
		for (TipoDeInvestimento ti : lista) {
			System.out.println(ti);
		}

		TipoDeInvestimento tInv4 = new TipoDeInvestimento("Loucão", "99");
		TipoDeInvestimentoDAO.criarNovoTipo(tInv4);

		lista = TipoDeInvestimentoDAO.getTodosTiposDeInvestimentos();
		for (TipoDeInvestimento ti : lista) {
			System.out.println(ti);
		}

		TipoDeInvestimentoDAO.deletarTipoDeInvestimento(tInv4);

		lista = TipoDeInvestimentoDAO.getTodosTiposDeInvestimentos();
		for (TipoDeInvestimento ti : lista) {
			System.out.println(ti);
		}

	}

}
