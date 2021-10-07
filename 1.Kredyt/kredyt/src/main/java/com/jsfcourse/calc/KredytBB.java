package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kwota;
	private String lat;
	private String procent;
	private Double wynik;

	@Inject
	FacesContext ctx;
	
	public String getKwota() {
		return kwota;
	}
	public void setKwota(String kwota) {
		this.kwota = kwota;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getProcent() {
		return procent;
	}
	public void setProcent(String procent) {
		this.procent = procent;
	}
	public Double getWynik() {
		return wynik;
	}


	public String oblicz() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double lat = Double.parseDouble(this.lat);
			int procent = Integer.parseInt(this.procent);
			double procent2;
			
			switch(procent) {
				case 1: procent2 = 3.5; break;
				case 2: procent2 = 5.0; break;
				default: procent2 = 8.0;
			}

			wynik = kwota / (lat*12);
			wynik += (procent * wynik / 100);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: rata miesiêczna = "+wynik, null));
			
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
		}
		
		return null;
	}
}
