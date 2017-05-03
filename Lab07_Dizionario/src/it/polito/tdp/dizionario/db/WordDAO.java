package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {
	
	List<String> parole = new ArrayList<String>();

	/*
	 * Ritorna tutte le parole di una data lunghezza che differiscono per un solo carattere
	 */
	public List<String> getAllSimilarWords(String parola, int numeroLettere) {
		
//		List<String> modific=new ArrayList<String>();
//		for (int x=0;x<parola.length();x++){
//			modific.add(parola.replace(parola.charAt(x),'_'));
//		}
//		System.out.println(modific);
//		
//		List<String> parole = new ArrayList<String>();
//		
//		for (String s: modific){
//			
//			String sql = "SELECT nome FROM parola WHERE nome LIKE '?'";
//			Connection conn = DBConnect.getInstance().getConnection();
//			PreparedStatement st;
//
//			try {
//
//				st = conn.prepareStatement(sql);
//				st.setString(1, s);
//				ResultSet res = st.executeQuery();
//				conn.close();
//
//				while (res.next())
//					parole.add(res.getString("nome"));
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.out.println("Errore connessione al database");
//				throw new RuntimeException("Error Connection Database");
//			}
//			
//		}
				
		List<String> parolesimili = new ArrayList<String>();
		
		for(String s:parole){
			if (differenze(parola,s)==true)
				parolesimili.add(s);
		}
		
		return parolesimili;
	}
	
	private boolean differenze(String s1, String s2){
		int n=0;
		for (int x=0;x<s1.length();x++){
			if (s1.charAt(x)!=s2.charAt(x)){
				n++;
			}
		}
		if (n>1){
			return false;
		}
		else
			return true;
	}

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int numeroLettere) {

		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		PreparedStatement st;

		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, numeroLettere);
			ResultSet res = st.executeQuery();	

			while (res.next())
				parole.add(res.getString("nome"));

			conn.close();
			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
		
	}

}
