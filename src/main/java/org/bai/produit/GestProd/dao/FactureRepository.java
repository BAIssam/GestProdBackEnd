package org.bai.produit.GestProd.dao;

import javax.transaction.Transactional;

import org.bai.produit.GestProd.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
	
	@Transactional
	@Query("select max(numero) from Facture")
	String getBigestNumberFacture();

}
