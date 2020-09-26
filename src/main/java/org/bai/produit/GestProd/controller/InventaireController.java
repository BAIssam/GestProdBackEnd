package org.bai.produit.GestProd.controller;

import java.util.List;

import org.bai.produit.GestProd.dao.InventaireRepository;
import org.bai.produit.GestProd.entity.Inventaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class InventaireController {

	@Autowired
	InventaireRepository inventaireRepository;

	@GetMapping("/inventaire")
	public List<Inventaire> getInventaire() {

		return inventaireRepository.findAll();
	}

}
