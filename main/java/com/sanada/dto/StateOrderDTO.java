package com.sanada.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StateOrderDTO {
	    
	    private String cod;
	    
	    private String desc;

		public StateOrderDTO(String cod, String desc) {
			this.cod = cod;
			this.desc = desc;
		}

		public String getCod() {
			return cod;
		}

		public void setCod(String cod) {
			this.cod = cod;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	    
	    

	    
}
