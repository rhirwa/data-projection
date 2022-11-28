import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PokemonReport {
	/**
	 * this method takes in an array list of speed recordings, and returns the maximum speed
	 * @param list
	 * @return
	 */
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
	
	public static int maxSpeedNum(ArrayList<String> list) {
		int max = 0;
		for(int i=0; i<list.size(); i+=6) {
			if(list.get(i+4).isBlank()) {
				list.set(i, "0");
			}
			int numInt = Integer.parseInt(list.get(i+4));
			if(numInt > max) {
				max = numInt;
				}
		}
		return max;
	}
	/**
	 * This method takes in a list of Pokemon's attack recordings and returns their average
	 * @param list
	 * @return
	 */
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
	
	/**
	 * This method takes in a list of Pokemon's types and returns their sorted list along with how often they appear
	 * @param list
	 * @return
	 */
	public static ArrayList<String> sortedListWithCount(ArrayList<String> list) {
		ArrayList<String> types = new ArrayList<>();
		String bugCount = null;
		String bug = null;
		String fairyCount = null;
		String normal = null;
		String normalCount = null;
		String fairy = null;
		String psychicCount = null;
		String psychic = null;
		String steelCount = null;
		String steel = null;
		String waterCount = null;
		String water = null;
		for(int i = 1; i<list.size(); i+=6) {
			if(list.get(i).equals("Bug")) {
				bugCount = String.valueOf(Collections.frequency(list, list.get(i)));
				bug = list.get(i).concat("             " + bugCount);
			} else if(list.get(i).equals("Fairy")){
				fairyCount = String.valueOf(Collections.frequency(list, list.get(i)));
				fairy = list.get(i).concat("             " + fairyCount);
			} else if(list.get(i).equals("Normal")){
				normalCount = String.valueOf(Collections.frequency(list, list.get(i)));
				normal = list.get(i).concat("             " + normalCount);
			} else if(list.get(i).equals("Psychic")){
				psychicCount = String.valueOf(Collections.frequency(list, list.get(i)));
				psychic = list.get(i).concat("             " + psychicCount);
			} else if(list.get(i).equals("Steel")){
				steelCount = String.valueOf(Collections.frequency(list, list.get(i)));
				steel = list.get(i).concat("             " + steelCount);
			} else {
				waterCount = String.valueOf(Collections.frequency(list, list.get(i)));
				water = list.get(i).concat("             " + waterCount);
			}
		}
		types.add(bug);
		types.add(normal);
		types.add(fairy);
		types.add(psychic);
		types.add(steel);
		types.add(water);
		Collections.sort(types);
		return types;
	}
	
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
		System.out.println("========================");
		System.out.println(maximumSpeed(dataList) + " has speed of " + maxSpeedNum(dataList));
		System.out.println("========================");
		System.out.printf("Average attack for 3rd generation Pokemon's is %.2f \n", averageAttack(dataList));
		System.out.println("========================");
		System.out.println("                        ");
		System.out.println("Type           Count");
		for(int i= 0; i<sortedListWithCount(dataList).size(); i++) {
			System.out.printf("%s \n", sortedListWithCount(dataList).get(i));
		}
	}
}
