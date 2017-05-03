package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	WordDAO dao=new WordDAO();
	UndirectedGraph <String,DefaultEdge> grafo=new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
	int numeroL;
	List<String> tutti=new ArrayList<String>();

	public List<String> createGraph(int numeroLettere) {	
		
		numeroL=numeroLettere;
		
		for (String s: dao.getAllWordsFixedLength(numeroLettere)){
			grafo.addVertex(s);
		}
		
		for (String v1: grafo.vertexSet()){
			for(String s: dao.getAllSimilarWords(v1,numeroL)){
				if (v1.compareTo(s)!=0){
					grafo.addEdge(v1, s);
				}
			}
		}
		
		return dao.getAllWordsFixedLength(numeroLettere);
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		List<String> vicini=new ArrayList<String>();
		
//		for (DefaultEdge e: grafo.edgeSet()){
//			if (grafo.getEdgeSource(e).compareTo(parolaInserita)==0)
//				vicini.add(grafo.getEdgeTarget(e));
//		}
		
		vicini.addAll(Graphs.neighborListOf(grafo,parolaInserita));
		
		return vicini;
	}

	public List<String> displayAllNeighbours(String parolaInserita) {
		
		List<String> collegati=new ArrayList<String>();

		BreadthFirstIterator<String,DefaultEdge> bfi= new BreadthFirstIterator<>(grafo,parolaInserita);
		
		while(bfi.hasNext()){
			collegati.add(bfi.next());
		}
		
		return collegati;
	}
}
