package org.bai.produit.GestProd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import org.bai.produit.GestProd.dao.CommandeRepository;
import org.bai.produit.GestProd.dao.LigneCommandeRepository;
import org.bai.produit.GestProd.entity.Commande;
import org.bai.produit.GestProd.entity.LigneCommande;
import org.bai.produit.GestProd.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LcommandeController {
	@Autowired
	LigneCommandeRepository repository;
	
	@Autowired
	CommandeRepository commandeRepository;

	@GetMapping("/lcommandes")
	public List<LigneCommande> getAllLcomms() {
		System.out.println("Get all Lcomms...");

		List<LigneCommande> Lcomms = new ArrayList<>();
		repository.findAll().forEach(Lcomms::add);

		return Lcomms;
	}

	@GetMapping("/lcommandes/{id}")
	  public List<LigneCommande> getAllByIdCommande(@PathVariable(value = "id") Long id) {
	    System.out.println("Get all Lcomms...");
	 
	    Optional<Commande> c = commandeRepository.findById(id);
	    List<LigneCommande> Lcomms = new ArrayList<>();
	    repository.findAllByCommande(c).forEach(Lcomms::add);
	 
	    return Lcomms;
	  }

	@GetMapping("/lcommandess/{id}")
	public ResponseEntity<LigneCommande> getLcommById(@PathVariable(value = "id") Long LcommId)
			throws ResourceNotFoundException {
		LigneCommande LigneCommande = repository.findById(LcommId)
				.orElseThrow(() -> new ResourceNotFoundException("LigneCommande not found for this id :: " + LcommId));
		return ResponseEntity.ok().body(LigneCommande);
	}

	@PostMapping("/lcommande")
	public @Valid LigneCommande createLcomm(@Valid @RequestBody LigneCommande LigneCommande) {

		return repository.save(LigneCommande);

	}

	@DeleteMapping("/lcommande/{id}")
	public Map<String, Boolean> deleteLcomm(@PathVariable(value = "id") Long LcommId) throws ResourceNotFoundException {
		LigneCommande LigneCommande = repository.findById(LcommId)
				.orElseThrow(() -> new ResourceNotFoundException("LigneCommande not found  id :: " + LcommId));

		repository.delete(LigneCommande);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/lcommande/{id}")
	public ResponseEntity<LigneCommande> updateLcomm(@PathVariable("id") long id,
			@RequestBody LigneCommande LigneCommande) {
		System.out.println("Update LigneCommande with ID = " + id + "...");

		Optional<LigneCommande> LcommInfo = repository.findById(id);

		if (LcommInfo.isPresent()) {
			LigneCommande lcomm = LcommInfo.get();
			lcomm.setLibart(LigneCommande.getLibart());

			return new ResponseEntity<>(repository.save(LigneCommande), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
