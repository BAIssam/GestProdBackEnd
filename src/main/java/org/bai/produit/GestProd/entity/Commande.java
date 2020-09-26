package org.bai.produit.GestProd.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numCommande;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Date dateCommande;
	private int annee;
	private double totht;
	private double tottva;
	private double totttc;
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "commande", fetch = FetchType.EAGER)
	@Valid
	private List<LigneCommande> lignesCommande = new ArrayList<>();

	public Commande() {
		super();
	}

	public Commande(Long id, int numCommande, Date dateCommande, int annee, double totht, double tottva,
			double totttc, @Valid List<LigneCommande> lignesCommande) {
		super();
		this.id = id;
		this.numCommande = numCommande;
		this.dateCommande = dateCommande;
		this.annee = annee;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.lignesCommande = lignesCommande;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(int numCommande) {
		this.numCommande = numCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public double getTotht() {
		return totht;
	}

	public void setTotht(double totht) {
		this.totht = totht;
	}

	public double getTottva() {
		return tottva;
	}

	public void setTottva(double tottva) {
		this.tottva = tottva;
	}

	public double getTotttc() {
		return totttc;
	}

	public void setTotttc(double totttc) {
		this.totttc = totttc;
	}

	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}

	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", numCommande=" + numCommande + ", dateCommande=" + dateCommande + ", annee="
				+ annee + ", totht=" + totht + ", tottva=" + tottva + ", totttc=" + totttc
				+ ", lignesCommande=" + lignesCommande + "]";
	}
	
}
