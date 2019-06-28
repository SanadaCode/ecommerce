package com.sanada.model;

public enum MessageEnum {
	
	SUCCES("l'operazione è avvenuta con successo"),
	PRODUCT_NOT_FOUND("il prodotto non è stato trovato"),
	NOT_AUTHORIZED("Non sei autorizzato"),
	PRODUCT_NOT_ENOUGH("Prodotto non disponibile"),
	PRODUCT_ALREADY_IN_CART("Proddo già nel carrello!"),
	NOT_EVERY_PRODUCT_AVALAIBLE("Non tutti i prodotti erano disponibili"),
	IMPOSSIBLE_TO_DELETE("Non è possibile cancellare l'ordine"),
	IMPOSSIBLE_TO_SEND("Non è possibile spedire l'ordine"),
	CART_EMPTY("Il carrello è vuoto!"),
	GENERIC("Qualcosa non ha funzionato"),
	PRODUCT_ALREADY_EXIST("Il prodotto esiste già"),
	USER_NOT_FOUND("Utente non trovato!");
	
	
	 private final String message;
	 
	 private  MessageEnum(String message) {
		 this.message=message;
	 }
	 
	 public String getMessage() {
		 return this.message;
	 }

}
