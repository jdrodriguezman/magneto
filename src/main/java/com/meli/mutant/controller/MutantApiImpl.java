package com.meli.mutant.controller;


import com.meli.mutant.model.Dna;
import com.meli.mutant.service.IMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/")
public class MutantApiImpl implements IMutantApi {

	@Autowired
	private IMutantService mutantService;

	@Override
	@PostMapping("mutant/")
	public ResponseEntity isMutant(@RequestBody @Valid Dna dna) {
		return ResponseEntity
				.status(mutantService.isMutant(dna) ? HttpStatus.OK : HttpStatus.FORBIDDEN).build();
	}

	@Override
	@GetMapping("stats/")
	public ResponseEntity getStats() {
		return ResponseEntity.ok(mutantService.getStats());
	}
}
