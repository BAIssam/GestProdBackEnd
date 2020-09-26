package org.bai.produit.GestProd.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class LigneCommande {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String code_article;
	private String Libart;
	private float pu;
	private float qte;
	private int tva;
	private float totht;
	private float tottva;
	private float totttc;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Commande commande;

	public LigneCommande() {
		super();
	}

	public LigneCommande(long id, String code_article, String libart, float pu, float qte, int tva, float totht,
			float tottva, float totttc, Commande commande) {
		super();
		this.id = id;
		this.code_article = code_article;
		Libart = libart;
		this.pu = pu;
		this.qte = qte;
		this.tva = tva;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.commande = commande;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode_article() {
		return code_article;
	}

	public void setCode_article(String code_article) {
		this.code_article = code_article;
	}

	public String getLibart() {
		return Libart;
	}

	public void setLibart(String libart) {
		Libart = libart;
	}

	public float getPu() {
		return pu;
	}

	public void setPu(float pu) {
		this.pu = pu;
	}

	public float getQte() {
		return qte;
	}

	public void setQte(float qte) {
		this.qte = qte;
	}

	public int getTva() {
		return tva;
	}

	public void setTva(int tva) {
		this.tva = tva;
	}

	public float getTotht() {
		return totht;
	}

	public void setTotht(float totht) {
		this.totht = totht;
	}

	public float getTottva() {
		return tottva;
	}

	public void setTottva(float tottva) {
		this.tottva = tottva;
	}

	public float getTotttc() {
		return totttc;
	}

	public void setTotttc(float totttc) {
		this.totttc = totttc;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", code_article=" + code_article + ", Libart=" + Libart + ", pu=" + pu
				+ ", qte=" + qte + ", tva=" + tva + ", totht=" + totht + ", tottva=" + tottva + ", totttc=" + totttc
				+ ", commande=" + commande + "]";
	}

}
