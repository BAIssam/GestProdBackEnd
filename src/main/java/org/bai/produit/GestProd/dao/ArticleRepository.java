package org.bai.produit.GestProd.dao;

import java.util.Optional;

import org.bai.produit.GestProd.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	
	Article findByCode(String code);
	Optional<Article> findByCodeBarre(long codeBarre);
	
	@Query("SELECT a FROM Article a WHERE a.code LIKE :x")
	Optional<Article> findArticleByCode(@Param("x") String code);

}
