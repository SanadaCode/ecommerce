package com.sanada.model;

public enum StateOfOrder {
	
	CARRELLO("crl"),
	CONFERMATO("cnf"),
	SPEDITO("sdt"),
	NOT_AVAILEABLE("nbl"),
	COMPLETO("cpt"),
	ANNULATO("anlt");
	
	public String cod;

	private StateOfOrder(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}
	
	
}
