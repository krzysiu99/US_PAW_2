package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
//public class KredytBB implements Serializable {
public class KredytBB {
	//private static final long serialVersionUID = -5801556047172890492L;
	
	private String kwota;
	private String lat;
	private String procent;
	private Double wynik;

	@Inject
	FacesContext ctx;
	
	public String getKwota() {
		return this.kwota;
	}
	public void setKwota(String kwota) {
		this.kwota = kwota;
	}
	public String getLat() {
		return this.lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getProcent() {
		return this.procent;
	}
	public void setProcent(String procent) {
		this.procent = procent;
	}
	public Double getWynik() {
		return wynik;
	}
	
	public String oblicz(){
		try {
			double kwota = Double.parseDouble(this.kwota);
			double lat = Double.parseDouble(this.lat);
			double procent = Double.parseDouble(this.procent);

			kwota += procent / 100 * kwota;
			wynik = kwota / (lat*12);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return wynik.toString();
			
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return null;
		}
	}


	public String wykonaj() {
		if(oblicz() != null)
			return "wynik";
		else return null;
	}
	
	public String wykonajAJAX() {
		if(oblicz() != null)
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata miesiêczna: "+wynik, null));
		return null;
	}
}
