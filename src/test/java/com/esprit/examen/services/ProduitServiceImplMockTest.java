package com.esprit.examen.services;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;



@SpringBootTest
public class ProduitServiceImplMockTest {
	@Mock
	ProduitRepository produitRepository ;
	
	@InjectMocks
	ProduitServiceImpl produitService;
	
	
	Produit p1 = new Produit(55L, "2365","produit1",50, null, null, null, null, null);
	
	@Test
    public void testaddProduit(){
        Mockito.when(produitRepository.save(p1)).thenReturn(p1);
        Produit produit1 = produitService.addProduit(p1);
        //assertNotNull(produit1);
        Mockito.verify(produitRepository, times(1)).save(Mockito.any(Produit.class));
        System.out.println("3");
    }

}
