package org.bai.produit.GestProd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventaire {
	
	@Id
	private String codeArticle;
	private String libart;
	private float valeurEntree;
	private float valeurSortie;
	private float valeurStock;
	private float qteEntree;
	private float qteSortie;
	private float stockFinal;
	
	public Inventaire() {
		super();
	}

	public Inventaire(String codeArticle, String libart, float valeurEntree, float valeurSortie, float valeurStock,
			float qteEntree, float qteSortie, float stockFinal) {
		super();
		this.codeArticle = codeArticle;
		this.libart = libart;
		this.valeurEntree = valeurEntree;
		this.valeurSortie = valeurSortie;
		this.valeurStock = valeurStock;
		this.qteEntree = qteEntree;
		this.qteSortie = qteSortie;
		this.stockFinal = stockFinal;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getLibart() {
		return libart;
	}

	public void setLibart(String libart) {
		this.libart = libart;
	}

	public float getQteEntree() {
		return qteEntree;
	}

	public void setQteEntree(float qteEntree) {
		this.qteEntree = qteEntree;
	}

	public float getQteSortie() {
		return qteSortie;
	}

	public void setQteSortie(float qteSortie) {
		this.qteSortie = qteSortie;
	}
	
	public float getValeurEntree() {
		return valeurEntree;
	}

	public void setValeurEntree(float valeurEntree) {
		this.valeurEntree = valeurEntree;
	}

	public float getValeurSortie() {
		return valeurSortie;
	}

	public void setValeurSortie(float valeurSortie) {
		this.valeurSortie = valeurSortie;
	}
	
	public float getValeurStock() {
		return valeurStock;
	}

	public void setValeurStock(float valeurStock) {
		this.valeurStock = valeurStock;
	}

	public float getStockFinal() {
		return stockFinal;
	}

	public void setStockFinal(float stockFinal) {
		this.stockFinal = stockFinal;
	}

	@Override
	public String toString() {
		return "Inventaire [codeArticle=" + codeArticle + ", libart=" + libart + ", valeurEntree=" + valeurEntree
				+ ", valeurSortie=" + valeurSortie + ", valeurStock=" + valeurStock + ", qteEntree=" + qteEntree
				+ ", qteSortie=" + qteSortie + ", stockFinal=" + stockFinal + "]";
	}

}
