package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;



@SpringBootTest
public class StockServiceImpTest {
	@Autowired
	IStockService stockService ;
	
	
	@Test
	public void TestAddStock() {
		List<Stock> stocks = stockService.retrieveAllStocks();
		int expected=stocks.size();
		Stock s = new Stock();
		s.setLibelleStock("stock test");
		s.setQte(10);
		s.setQteMin(100);
		Stock savedStock= stockService.addStock(s);
		assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());
		
	}
	


	
	
}
