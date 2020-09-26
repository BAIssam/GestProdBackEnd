package org.bai.produit.GestProd.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.bai.produit.GestProd.entity.Facture;
import org.bai.produit.GestProd.entity.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {
	
	Iterable<LigneFacture> findByFacture(Optional<Facture> f);
	
//	@Transactional
//	@Query("code_article, libart, pu, sum(qte) as sortie, sum(totht), sum(totttc) from LigneFacture group by code_article")
//	Iterable<LigneFacture> getSorties();

}
