package com.sanada.model;

public enum RoleCod {
      CLIENTE("clt"),
      VENDITORE("vdt");
 
	 public final String cod;
	 
	 private  RoleCod(String cod) {
		 this.cod=cod;
	 }
	 
	 public String getCod() {
		 return this.cod;
	 }
}


