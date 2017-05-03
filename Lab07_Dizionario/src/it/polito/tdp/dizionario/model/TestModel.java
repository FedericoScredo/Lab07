package it.polito.tdp.dizionario.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		System.out.println(String.format("**Grafo creato** - Trovate #%d parole di lunghezza 3\n",  model.createGraph(3).size()));
		
		List<String> vicini = model.displayNeighbours("dai");
		System.out.println("Vicini di dai: " + vicini);
		
		List<String> neighbours = model.displayAllNeighbours("dai");
		System.out.println(neighbours);
		System.out.println(neighbours.size());
	}

}
