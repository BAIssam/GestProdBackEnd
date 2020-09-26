package org.bai.produit.GestProd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bai.produit.GestProd.dao.FactureRepository;
import org.bai.produit.GestProd.dao.LigneFactureRepository;
import org.bai.produit.GestProd.entity.Facture;
import org.bai.produit.GestProd.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LigneFactureController {

	@Autowired
	public LigneFactureRepository ligneFactureRepository;
	
	@Autowired
	public FactureRepository factureRepository;
	
	@RequestMapping(value="/GestProd/api/ligneFacture", method=RequestMethod.GET)
	public List<LigneFacture> getAllLFactures(){
		return ligneFactureRepository.findAll();
	}
	
	@RequestMapping(value="/api/lignesFacture/{id}", method=RequestMethod.GET)
	public List<LigneFacture> getAllLFacturesByIdFacture(@PathVariable(value = "id") Long id){
		Optional<Facture> f = factureRepository.findById(id);
		
		List<LigneFacture> lFacture = new ArrayList<LigneFacture>();
		ligneFactureRepository.findByFacture(f).forEach(lFacture::add);
		
		return lFacture;
	}
	
	@RequestMapping(value="/GestProd/api/ligneFacture", method=RequestMethod.POST)
	public LigneFacture saveLFacture(@RequestBody LigneFacture lf) {
		return ligneFactureRepository.save(lf);
	}
	
	@RequestMapping(value="/GestProd/api/ligneFacture", method=RequestMethod.PUT)
	public LigneFacture updateLFacture(@RequestBody LigneFacture lf) {
		return ligneFactureRepository.save(lf);
	}
	
	@RequestMapping(value="/GestProd/api/ligneFacture/{id}", method=RequestMethod.DELETE)
	public boolean deleteLFacture(@PathVariable Long id) {
		ligneFactureRepository.deleteById(id);
		return true;
	}

}
