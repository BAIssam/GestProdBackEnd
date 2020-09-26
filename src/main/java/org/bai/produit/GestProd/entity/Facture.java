package org.bai.produit.GestProd.entity;

import java.time.LocalDate;
import java.util.ArrayList;
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
public class Facture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int numero;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private LocalDate dateFacture;
	private float totht;
	private float totrem;
	private float tottva;
	private float timbre;
	private float totttc;
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "facture", fetch = FetchType.EAGER)
	@Valid
	private List<LigneFacture> lignesFactures = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getTotht() {
		return totht;
	}

	public void setTotht(float totht) {
		this.totht = totht;
	}

	public float getTotrem() {
		return totrem;
	}

	public void setTotrem(float totrem) {
		this.totrem = totrem;
	}

	public float getTottva() {
		return tottva;
	}

	public void setTottva(float tottva) {
		this.tottva = tottva;
	}

	public float getTimbre() {
		return timbre;
	}

	public void setTimbre(float timbre) {
		this.timbre = timbre;
	}

	public float getTotttc() {
		return totttc;
	}

	public void setTotttc(float totttc) {
		this.totttc = totttc;
	}

	public LocalDate getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(LocalDate dateFacture) {
		this.dateFacture = dateFacture;
	}

	public List<LigneFacture> getLignesFactures() {
		return lignesFactures;
	}

	public void setLignesFactures(List<LigneFacture> lignesFactures) {
		this.lignesFactures = lignesFactures;
	}

	public Facture(long id, int numero, LocalDate dateFacture, float totht, float totrem, float tottva, float timbre,
			float totttc, @Valid List<LigneFacture> lignesFactures) {
		super();
		this.id = id;
		this.numero = numero;
		this.dateFacture = dateFacture;
		this.totht = totht;
		this.totrem = totrem;
		this.tottva = tottva;
		this.timbre = timbre;
		this.totttc = totttc;
		this.lignesFactures = lignesFactures;
	}

	public Facture() {
		super();
	}

	@Override
	public String toString() {
		return "Facture [id=" + id + ", numero=" + numero + ", dateFacture=" + dateFacture + ", totht=" + totht
				+ ", totrem=" + totrem + ", tottva=" + tottva + ", timbre=" + timbre + ", totttc=" + totttc
				+ ", lignesFactures=" + lignesFactures + "]";
	}

}
