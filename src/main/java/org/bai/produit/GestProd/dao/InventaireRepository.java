package org.bai.produit.GestProd.dao;

import org.bai.produit.GestProd.entity.Inventaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventaireRepository extends JpaRepository<Inventaire, String>{
	
	Inventaire findBycodeArticle(String code);

}
