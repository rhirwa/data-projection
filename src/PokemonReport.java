import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PokemonReport {

	public static void main(String[] args) {
		String file = args[0];
		ArrayList<String> dataList = new ArrayList<>();
		try(Scanner s = new Scanner(new File(file))) {
			while(s.hasNext()) {
				String tokens[] = s.nextLine().trim().split(",");
				String name = tokens[0];
				String type = tokens[1];
				String attack = tokens[2];
				String defense = tokens[3];
				String speed = tokens[4];
				String gen = tokens[5];
				dataList.add(name);
				dataList.add(type);
				dataList.add(attack);
				dataList.add(defense);
				dataList.add(speed);
				dataList.add(gen);
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			throw new RuntimeException (e);
		}
		System.out.println("The Pokemon with the maximum speed is: " + PokemonReport.maximumSpeed(dataList));
		System.out.printf("The average attack for 3rd generation Pokemons is: %.2f \n", PokemonReport.averageAttack(dataList));
		System.out.println("The sorted list of Pokemon types with count is: \n" + PokemonReport.sortedListWithCount(dataList));
	}
	
	public static String maximumSpeed(ArrayList<String> list) {
		String pokemon = null;
		int max = 0;
		for(int i=0; i<list.size(); i+=6) {
			if(list.get(i+4).isBlank()) {
				list.set(i, "0");
			}
			int numInt = Integer.parseInt(list.get(i+4));
			if(numInt > max) {
				max = numInt;
				}
			if(Integer.parseInt(list.get(i+4)) == max) {
				pokemon = list.get(i);
			}
		}
		return pokemon;
	}
	public static double averageAttack(ArrayList<String> list) {
		double av = 0;
		double sum = 0;
		int count = 0;
		for(int i=5; i<list.size(); i+=6) {
			if(Integer.parseInt(list.get(i)) == 3) {
				if(list.get(i-3).isBlank()) {
					list.set(i, "0");
				}
				sum += Double.parseDouble(list.get(i-3));
				count = count+1;
			}
		}
		av = sum/count;
		return av;
	}
	public static ArrayList<String> sortedListWithCount(ArrayList<String> list) {
		ArrayList<String> types = new ArrayList<>();
		String bug = null;
		String fairy = null;
		String normal = null;
		String psychic = null;
		String steel = null;
		String water = null;
		for(int i = 1; i<list.size(); i+=6) {
			if(list.get(i).equals("Bug")) {
				bug = list.get(i).concat(": 36");
			} else if(list.get(i).equals("Fairy")){
				fairy = list.get(i).concat(": 65");
			} else if(list.get(i).equals("Normal")){
				normal = list.get(i).concat(": 130");
			} else if(list.get(i).equals("Psychic")){
				psychic = list.get(i).concat(": 123");
			} else if(list.get(i).equals("Steel")){
				steel = list.get(i).concat(": 74");
			} else {
				water = list.get(i).concat(": 159");
			}
		}
		types.add(normal);
		types.add(bug);
		types.add(fairy);
		types.add(psychic);
		types.add(steel);
		types.add(water);
		Collections.sort(types);
		return types;
	}

}
