package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;

import lombok.extern.slf4j.Slf4j;




@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
 class TestFacture {
	
@InjectMocks
FactureServiceImpl factureService;

@Mock
FactureRepository factureRepository; 

List<DetailFacture> listDetail = new ArrayList<DetailFacture>();
List<Reglement> listReglement = new ArrayList<Reglement>();
Fournisseur fourn = new Fournisseur();


@Test
 void getAllFacture() throws ParseException {	
	List<Facture> listFacture = new ArrayList<Facture>();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date dateCreationFacture = dateFormat.parse("30/09/2000");	
	listFacture.add(new Facture(1L, 23,21,dateCreationFacture,dateCreationFacture,true,listDetail,fourn,listReglement));
	listFacture.add(new Facture(1L,30,44,dateCreationFacture,dateCreationFacture,true,listDetail,fourn,listReglement));

	Mockito.when(factureService.retrieveAllFactures()).thenReturn(listFacture);
	List<Facture> empList = factureService.retrieveAllFactures();
	assertEquals(2, empList.size());
	verify(factureRepository, times(1)).findAll();
	log.info("retrieve All done ///////////////// ");
}
	

@Test
 void AddFacture() throws ParseException {
	
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date dateCreationFacture = dateFormat.parse("30/09/2000");
	Facture f =new Facture(1L, 23,21,dateCreationFacture,dateCreationFacture,true,listDetail,fourn,listReglement);

	//FactureDTO factureDTO =factureConverter.convertEntityToDto(f);

	
	MockitoAnnotations.initMocks(this);	
	factureService.addFacture(f);

    verify(factureRepository, times(1)).save(f);
    log.info("retrieve All done ///////////////// ");
	

}

/**
 * Method under test: {@link FactureServiceImpl#deleteFacture()}
 */
@Test
	 void testDelete() throws ParseException {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date dateCreationFacture = dateFormat.parse("30/09/2000");
	Facture f =new Facture(1L, 23,21,dateCreationFacture,dateCreationFacture,true,listDetail,fourn,listReglement);

Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(f));
if(factureService.retrieveFacture(1L) == null) {
	factureService.deleteFacture(f.getIdFacture());
	}
else
	factureService.deleteFacture((factureService.retrieveFacture(1L).getIdFacture()));
    verify(factureRepository, Mockito.times(1)).deleteById(f.getIdFacture());
      log.info("Delete done ///////////////// ");
    



}




}