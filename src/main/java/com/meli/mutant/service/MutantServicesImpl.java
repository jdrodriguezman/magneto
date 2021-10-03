package com.meli.mutant.service;

import com.meli.mutant.repository.IMutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MutantServicesImpl implements IMutantService{

	@Autowired
	private IMutantRepository repository;

	@Override
	public Boolean isMutant(String[] dna) {
		String matriz[][] = new String[6][6];
		for (int column = 0; column < dna.length; column++) {
			String[] letters = dna[column].split("");
			for (int row = 0; row < letters.length; row++) {
				matriz[column][row] = letters[row];
			}
		}
		if( DiagonalRigt(matriz)){
			repository.saveDNA(true,dna);
			return true;
		}
		repository.saveDNA(false, dna);
		return false;
	}


	public Boolean validateHorizontal(String[] dna){
		for(int i = 0; i<dna.length; i++){
			int sec = 1;
			String[] letters = dna[i].split("");
			for(int j = 0; j<letters.length-1; j++){
				if(letters[j].equals(letters[j+1])){
					sec++;
					if(sec == 4){
						return true;
					}
				}else{
					sec = 1;
				}
			}
		}
		return false;
	}

	public Boolean validateVertical(String[] dna){
		String letterAfter = "";
		int cont = 0;
		for(int i = 0; i<dna.length; i++){
			int sec = 1;
			String[] letters = dna[i].split("");
			for(int j = 0; j<=i; j++){
				if(letterAfter.equals(letters[j])){
					sec++;
					if(sec == 4){
						return true;
					}
				}else{
					sec = 1;
				}

				letterAfter = letters[j];
			}
		}
		return false;
	}
	public Boolean validateVerticalM(String matriz[][]){
		String letterAfter = "";
		for (int x = 0; x < matriz[0].length; x++) {
			int sec = 1;
			for (int y = 0; y < matriz.length; y++) {
				if(letterAfter.equals(matriz[y][x])){
					sec++;
					if(sec == 4){
						return true;
					}
				}else{
					sec = 1;
				}
				letterAfter = matriz[y][x];
			}
			letterAfter = "";
		}
		return false;
	}

	public Boolean DiagonalLeft(String matriz[][]) {
		int altura = matriz.length, anchura = matriz[0].length;
		String letterAfter = "";
		for (int diagonal = 1 - anchura; diagonal < altura; diagonal++) {
			int sec = 1;
			for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
				 vertical < altura && horizontal < anchura; vertical++, horizontal++) {
				if(letterAfter.equals(matriz[vertical][horizontal])){
					sec++;
					if(sec == 4){
						return true;
					}
				}else{
					sec = 1;
				}
				letterAfter = matriz[vertical][horizontal];
				System.out.println(matriz[vertical][horizontal]);
			}
			letterAfter = "";
		}
		return false;
	}

	public Boolean DiagonalRigt(String matriz[][]) {
		int altura = matriz.length, anchura = matriz[0].length;
		for (int diagonal = 1 - anchura; diagonal <= altura - 1; // Mientras no llegue a la última diagonal.
			 diagonal += 1 // Avanza hasta el comienzo de la siguiente diagonal.
		) {
			for (
				// Recorre cada una de las diagonales a partir del extremo superior izquierdo.
					Integer vertical = Math.min(0, diagonal), horizontal = -Math.max(0, diagonal);
					vertical < altura && horizontal < anchura; // Mientras no excedan los límites.
					vertical += 1, horizontal += 1 // Avanza en diagonal incrementando ambos ejes.
			) {

				System.out.println(matriz[vertical][horizontal]);

			}
		}
		return false;
	}

	@Override
	public Optional getStats() {
		return Optional.empty();
	}
}
