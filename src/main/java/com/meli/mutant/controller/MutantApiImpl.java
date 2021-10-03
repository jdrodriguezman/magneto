package com.meli.mutant.controller;


import com.meli.mutant.service.IMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class MutantApiImpl implements IMutantApi {

	@Autowired
	private IMutantService mutantService;

	@Override
	@PostMapping("mutant/")
	public ResponseEntity isMutant() {
		String[] dna = new String[]{"ACCCCA", "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "TCACTG"};
		mutantService.isMutant(dna);
		return null;
	}

	@Override
	@GetMapping("stats/")
	public ResponseEntity getStats() {
		Optional opt = mutantService.getStats();
		return null;
	}
}
