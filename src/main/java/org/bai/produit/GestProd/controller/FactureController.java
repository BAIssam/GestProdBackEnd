package org.bai.produit.GestProd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.bai.produit.GestProd.dao.ArticleRepository;
import org.bai.produit.GestProd.dao.FactureRepository;
import org.bai.produit.GestProd.dao.InventaireRepository;
import org.bai.produit.GestProd.dao.LigneFactureRepository;
import org.bai.produit.GestProd.entity.Article;
import org.bai.produit.GestProd.entity.Facture;
import org.bai.produit.GestProd.entity.Inventaire;
import org.bai.produit.GestProd.entity.LigneFacture;
import org.bai.produit.GestProd.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FactureController {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	LigneFactureRepository lFactureRepository;
	@Autowired
	ArticleRepository articleRepo;
	
	@Autowired InventaireRepository inventaireRepository;
	@Autowired
	ServletContext context;
	// @Autowired CompteurRepository comptrepo;

	@GetMapping("/facts")
	public List<Facture> getAllFacts() {
		System.out.println("Get all Facts...");
		List<Facture> Facts = new ArrayList<>();
		factureRepository.findAll().forEach(Facts::add);
		return Facts;
	}
	
	@GetMapping("/facts/{id}")
	public ResponseEntity<Facture> getFactById(@PathVariable(value = "id") Long FactId)
			throws ResourceNotFoundException {
		Facture Facture = factureRepository.findById(FactId)
				.orElseThrow(() -> new ResourceNotFoundException("Facture not found for this id :: " + FactId));
		return ResponseEntity.ok().body(Facture);
	}
	
	@GetMapping("/fact/numero")
	public int getBigestNumberFacture(){
		String response = factureRepository.getBigestNumberFacture();
		if (response == null)
			return 0;
		return Integer.valueOf(response);
	}

	@PostMapping("/facts")
	public ResponseEntity<Facture> createFacture(@Valid @RequestBody Facture facture)
			throws JsonParseException, JsonMappingException, Exception {
		factureRepository.save(facture);
		List<LigneFacture> lfacts = facture.getLignesFactures();
		for (LigneFacture lf : lfacts) {
			lf.setFacture(facture);
			lFactureRepository.save(lf);

			Article article = articleRepo.findByCode(lf.getCode());
			article.setStock(article.getStock() - lf.getQte());
			articleRepo.save(article);
			
			Inventaire inv = inventaireRepository.findBycodeArticle(lf.getCode());
       		if(inv != null) {
       			inv.setQteSortie(inv.getQteSortie() + lf.getQte());
       			inv.setValeurSortie(inv.getValeurSortie() + lf.getTotttc());
       			inv.setStockFinal(inv.getQteEntree() - inv.getQteSortie());
       			inv.setValeurStock(inv.getValeurEntree() - inv.getValeurSortie());
       			inventaireRepository.save(inv);
       		}else {
       			Inventaire newInv = new Inventaire();
       			newInv.setCodeArticle(lf.getCode());
       			newInv.setLibart(lf.getLibelle());
       			newInv.setQteSortie(lf.getQte());
       			newInv.setValeurSortie(lf.getTotttc());
       			newInv.setStockFinal(newInv.getQteSortie());
       			newInv.setValeurStock(newInv.getValeurSortie());
       			inventaireRepository.save(newInv);
       		}
		}
//		Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(Facture.getAnnee());
//		if (CompteurInfo.isPresent()) {
//			Compteur compteur = CompteurInfo.get();
//			compteur.setNumfac(compteur.getNumfac() + 1);
//			compteur = comptrepo.save(compteur);
//		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/facts/{id}")
	public Map<String, Boolean> deleteFact(@PathVariable(value = "id") Long FactId) throws ResourceNotFoundException {
		Facture Facture = factureRepository.findById(FactId)
				.orElseThrow(() -> new ResourceNotFoundException("Facture not found  id :: " + FactId));
		factureRepository.delete(Facture);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/facts/delete")
	public ResponseEntity<String> deleteAllFacts() {
		System.out.println("Delete All Facts...");
		factureRepository.deleteAll();
		return new ResponseEntity<>("All Facts have been deleted!", HttpStatus.OK);
	}

	@PutMapping("/Facts/{id}")
	public ResponseEntity<Facture> updateFact(@PathVariable("id") long id, @RequestBody Facture Facture) {
		System.out.println("Update Facture with ID = " + id + "...");
		Optional<Facture> FactInfo = factureRepository.findById(id);
		if (FactInfo.isPresent()) {
			Facture fact = FactInfo.get();
			return new ResponseEntity<>(factureRepository.save(Facture), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
