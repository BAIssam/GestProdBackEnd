package org.bai.produit.GestProd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.bai.produit.GestProd.dao.ArticleRepository;
import org.bai.produit.GestProd.dao.CommandeRepository;
import org.bai.produit.GestProd.dao.InventaireRepository;
import org.bai.produit.GestProd.dao.LigneCommandeRepository;
import org.bai.produit.GestProd.entity.Article;
import org.bai.produit.GestProd.entity.Commande;
import org.bai.produit.GestProd.entity.Inventaire;
import org.bai.produit.GestProd.entity.LigneCommande;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommandeController {

	@Autowired
	CommandeRepository cmdeRepository;
	@Autowired
	LigneCommandeRepository lCmdeRepo;
	@Autowired
	ArticleRepository articleRepo;
	@Autowired
	InventaireRepository inventaireRepository;
//	@Autowired CompteurRepository comptrepo;
	@Autowired
	ServletContext context;

	@GetMapping("/commande")
	public List<Commande> getAllComms() {
		System.out.println("Get all Comms...");
		List<Commande> Comms = new ArrayList<>();
		cmdeRepository.findAll().forEach(Comms::add);
		return Comms;
	}

	@GetMapping("/commande/{id}")
	public ResponseEntity<Commande> getCommById(@PathVariable(value = "id") Long CommId)
			throws ResourceNotFoundException {
		Commande Commande = cmdeRepository.findById(CommId)

				.orElseThrow(() -> new ResourceNotFoundException("Commande not found for this id :: " + CommId));
		return ResponseEntity.ok().body(Commande);
	}

	@PostMapping("/commande")
	public ResponseEntity<Commande> createComm(@Valid @RequestBody Commande commande)
			throws JsonParseException, JsonMappingException, Exception {

		cmdeRepository.save(commande);
		List<LigneCommande> lcomms = commande.getLignesCommande();
		for (LigneCommande lc : lcomms) {
			lc.setCommande(commande);
			lCmdeRepo.save(lc);

			Article article = articleRepo.findByCode(lc.getCode_article());
			article.setStock(article.getStock() + lc.getQte());
			articleRepo.save(article);

			Inventaire inv = inventaireRepository.findBycodeArticle(lc.getCode_article());
			if (inv != null) {
				inv.setQteEntree(inv.getQteEntree() + lc.getQte());
				inv.setValeurEntree(inv.getValeurEntree() + lc.getTotttc());
				inv.setStockFinal(inv.getQteEntree() - inv.getQteSortie());
				inv.setValeurStock(inv.getValeurEntree() - inv.getValeurSortie());
				inventaireRepository.save(inv);
			} else {
				Inventaire newInv = new Inventaire();
				newInv.setCodeArticle(lc.getCode_article());
				newInv.setLibart(lc.getLibart());
				newInv.setValeurEntree(lc.getTotttc());
				newInv.setQteEntree(lc.getQte());
				newInv.setStockFinal(newInv.getQteEntree());
				newInv.setValeurStock(newInv.getValeurEntree());
				inventaireRepository.save(newInv);
			}

		}

		/*
		 * Optional<Compteur> CompteurInfo = comptrepo.findByAnnee(commande.getAnnee());
		 * if (CompteurInfo.isPresent()) { Compteur compteur = CompteurInfo.get();
		 * compteur.setNumcomm(compteur.getNumcomm()+1); compteur =
		 * comptrepo.save(compteur); }
		 */
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/commande/{id}")
	public ResponseEntity<Commande> deleteComm(@PathVariable(value = "id") Long CommId) {
		Optional<Commande> CommInfo = cmdeRepository.findById(CommId);
		if (CommInfo.isPresent()) {
			System.out.println("Commande 11");
			Commande Commande = CommInfo.get();
			lCmdeRepo.deleteByNumero(Commande.getNumCommande());
			cmdeRepository.delete(Commande);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/commande/delete")
	public ResponseEntity<String> deleteAllComms() {
		System.out.println("Delete All Comms...");
		cmdeRepository.deleteAll();
		return new ResponseEntity<>("All Comms have been deleted!", HttpStatus.OK);
	}

	@PutMapping("/commande/{id}")
	public ResponseEntity<Commande> updateComm(@PathVariable("id") long id, @RequestBody Commande Commande) {
		System.out.println("Update Commande with ID = " + id + "...");
		Optional<Commande> CommInfo = cmdeRepository.findById(id);
		if (CommInfo.isPresent()) {
			Commande comm = CommInfo.get();
			return new ResponseEntity<>(cmdeRepository.save(comm), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
