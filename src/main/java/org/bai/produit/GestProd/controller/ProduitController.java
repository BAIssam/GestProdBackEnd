package org.bai.produit.GestProd.controller;

import java.util.List;

import org.bai.produit.GestProd.dao.ArticleRepository;
import org.bai.produit.GestProd.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ProduitController {
	@Autowired
	public ArticleRepository produitController;
	
	@RequestMapping(value="/GestProd/api/produit", method=RequestMethod.GET)
	public List<Article> getProduits(){
		return produitController.findAll();
	}
	
	@RequestMapping(value="/GestProd/api/produit", method=RequestMethod.POST)
	public Article saveProduit(@RequestBody Article p) {
		return produitController.save(p);
	}
	
	//@RequestMapping(value="/api/produit/{id}", method=RequestMethod.PUT)
	//public Produit updateProduit(@PathVariable Long id, @RequestBody Produit p) {
	@RequestMapping(value="/GestProd/api/produit", method=RequestMethod.PUT)
	public Article updateProduit(@RequestBody Article p) {
		//p.setId(id);
		return produitController.save(p);
	}
	
	@RequestMapping(value="/GestProd/api/produit/{id}", method=RequestMethod.DELETE)
	public boolean deleteProduit(@PathVariable Long id) {
		produitController.deleteById(id);
		return true;
	}

}

