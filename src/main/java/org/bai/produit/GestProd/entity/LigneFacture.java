package org.bai.produit.GestProd.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class LigneFacture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String code;
	private String libelle;
	private float pv;
	private float qte;
	private int tva;
	private float remise;
	private float totht;
	private float tottva;
	private float totttc;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Facture facture;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public float getPv() {
		return pv;
	}

	public void setPv(float pv) {
		this.pv = pv;
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

	public float getRemise() {
		return remise;
	}

	public void setRemise(float remise) {
		this.remise = remise;
	}
	
	public float getTotht() {
		return totht;
	}

	public void setTotht(float totht) {
		this.totht = totht;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public LigneFacture(long id, String code, String libelle, float pv, float qte, int tva, float remise,
			float totht, float tottva, float totttc, Facture facture) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.pv = pv;
		this.qte = qte;
		this.tva = tva;
		this.remise = remise;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.facture = facture;
	}

	public LigneFacture() {
		super();
	}

	@Override
	public String toString() {
		return "LigneFacture [id=" + id + ", code=" + code + ", libelle=" + libelle
				+ ", pv=" + pv + ", qte=" + qte + ", tva=" + tva + ", remise=" + remise + ", totht="
				+ totht + ", tottva=" + tottva + ", totttc=" + totttc + ", facture=" + facture + "]";
	}

}
