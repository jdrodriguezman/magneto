package com.meli.mutant.service;

import com.meli.mutant.model.Dna;
import com.meli.mutant.model.Stat;
import com.meli.mutant.model.entity.Human;
import com.meli.mutant.repository.impl.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MutantServicesImpl implements IMutantService{

	private final HumanRepository repository;

	@Override
	public Boolean isMutant(Dna dna) {
		Human human= new Human();
		human.setDna(dna.toString());

		String dnaSec[][] = new String[dna.getDna().size()][];

		for (int column = 0; column < dna.getDna().size(); column++) {
			String[] letters = dna.getDna().get(column).split("");
			for (int row = 0; row < letters.length; row++) {
				dnaSec[column][row] = letters[row];
			}
		}
		if( validateHorizontal(dnaSec) || validateVertical(dnaSec) || diagonal(dnaSec)){
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
		double count_mutant = repository.countMutant();
		double count_human = repository.countHuman();

		return Stat.builder()
				.countMutantDna(count_mutant)
				.countHumanDna(count_human)
				.ratio(count_mutant / count_human)
				.build();
	}

	private Boolean validateHorizontal(String dna[][]){
		for(int i = 0; i<dna.length; i++){
			int sec = 1;
			for(int j = 0; j<dna[i].length-1; j++){
				if(dna[i][j].equals(dna[i][j+1])){
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

	private Boolean validateVertical(String dna[][]){
		String previousLetter = "";
		for (int x = 0; x < dna[0].length; x++) {
			int sec = 1;
			for (int y = 0; y < dna.length; y++) {
				if(previousLetter.equals(dna[y][x])){
					sec++;
					if(sec == 4){
						return true;
					}
				}else{
					sec = 1;
				}
				previousLetter = dna[y][x];
			}
			previousLetter = "";
		}
		return false;
	}

	private Boolean diagonal(String dna[][]) {
		int height = dna.length, width = dna[0].length;
		String previousLetter = "";
		for (int diagonal = 1 - width; diagonal < height; diagonal++) {
			int sec = 1;
			for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
				 vertical < height && horizontal < width; vertical++, horizontal++) {
				if(previousLetter.equals(dna[vertical][horizontal])){
					sec++;
					if(sec == 4){
						return true;
					}
				}else{
					sec = 1;
				}
				previousLetter = dna[vertical][horizontal];
			}
			previousLetter = "";
		}
		return false;
	}


}
