package com.meli.mutant.service;

import com.meli.mutant.model.Dna;
import com.meli.mutant.model.Stat;
import com.meli.mutant.model.entity.Human;
import com.meli.mutant.repository.impl.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServicesImpl implements IMutantService{

	@Autowired
	private HumanRepository repository;

	@Override
	public Boolean isMutant(Dna dna) {
		Human human= new Human();
		human.setDna(dna.toString());

		String adn[][] = new String[6][6];

		for (int column = 0; column < dna.getDna().size(); column++) {
			String[] letters = dna.getDna().get(column).split("");
			for (int row = 0; row < letters.length; row++) {
				adn[column][row] = letters[row];
			}
		}
		if( diagonalRight(adn)){
			human.setMutant(true);
			repository.saveDNA(human);
			return true;
		}
		human.setMutant(false);
		repository.saveDNA(human);

		return false;
	}

	@Override
	public Stat getStats() {

		// TODO: 3/10/2021 Eliminar
		double count_mutant = 40;
		double count_human = 100;

		return Stat.builder()
				.countMutantDna(count_mutant)
				.countHumanDna(count_human)
				.ratio(count_mutant / count_human)
				.build();
	}

	private Boolean validateHorizontal(String[] dna){
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

	// TODO: 3/10/2021 Var name in english
	private Boolean validateVertical(String matriz[][]){
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

	// TODO: 3/10/2021 Var name in english
	private Boolean diagonalLeft(String matriz[][]) {
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

	private Boolean diagonalRight(String matriz[][]) {
		int altura = matriz.length, anchura = matriz[0].length;
		return false;
	}


}
