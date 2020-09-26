package org.bai.produit.GestProd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	private long codeBarre;
	private String libelle;
	private float pa;
	private float pv;
	private float remise;
	private int tva;
	private float stock;
	private float stkinit;

	public Article() {
		super();
	}

	public Article(long id, String code, long codeBarre, String libelle, float pa, float pv, float remise, int tva,
			float stock, float stkinit) {
		super();
		this.id = id;
		this.code = code;
		this.codeBarre = codeBarre;
		this.libelle = libelle;
		this.pa = pa;
		this.pv = pv;
		this.remise = remise;
		this.tva = tva;
		this.stock = stock;
		this.stkinit = stkinit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public long getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(long codeBarre) {
		this.codeBarre = codeBarre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public float getPa() {
		return pa;
	}

	public void setPa(float pa) {
		this.pa = pa;
	}

	public float getPv() {
		return pv;
	}

	public void setPv(float pv) {
		this.pv = pv;
	}
	
	public float getRemise() {
		return remise;
	}

	public void setRemise(float remise) {
		this.remise = remise;
	}

	public int getTva() {
		return tva;
	}

	public void setTva(int tva) {
		this.tva = tva;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public float getStkinit() {
		return stkinit;
	}

	public void setStkinit(float stkinit) {
		this.stkinit = stkinit;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", code=" + code + ", codeBarre=" + codeBarre + ", libelle=" + libelle + ", pa="
				+ pa + ", pv=" + pv + ", remise=" + remise + ", tva=" + tva + ", stock=" + stock
				+ ", stkinit=" + stkinit + "]";
	}

}
