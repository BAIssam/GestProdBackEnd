package org.bai.produit.GestProd.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.bai.produit.GestProd.entity.Commande;
import org.bai.produit.GestProd.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

	Iterable<LigneCommande> findAllByCommande(Optional<Commande> c);
	
//	@Transactional
//	@Query("select code_article, libart, pu, sum(qte) as entree, sum(totht), sum(totttc) from LigneCommande group by code_article")
//	Iterable<LigneCommande> getEntries();

	@Modifying
	@Transactional
	@Query("delete from LigneCommande lc where lc.commande.numCommande = ?1")
	void deleteByNumero(int numero);

}
