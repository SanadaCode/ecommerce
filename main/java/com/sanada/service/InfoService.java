package com.sanada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanada.repository.InfoRepository;

@Service
public class InfoService {

	private InfoRepository infoRepository;

	@Autowired
	public InfoService(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}
	
}
