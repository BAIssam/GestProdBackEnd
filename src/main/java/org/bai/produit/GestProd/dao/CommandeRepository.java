package org.bai.produit.GestProd.dao;

import org.bai.produit.GestProd.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	Commande findByNumCommande(int num);

}
