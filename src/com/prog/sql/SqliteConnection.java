package com.prog.sql;

import java.sql.*;

import com.prog.ui.Finestra;

final public class SqliteConnection {

	
	private static Connection conn = null;																//creo la variabile con di tipo connection
	
	public SqliteConnection() {																			//costruttore della classe SQliteConnection
		super();																						//richiamo al costruttore della classe object
	}

	/*
	 * Metodo dbConnector:
	 * 			Ci permette di connetterci al database tramite JDBC
	 * 			ci visulizza un messaggio in per qualsiasi caso
	 */
	public static Connection dbConnector() {															//firma del metodo dbConnector
		try {																							//try per catturare un eccezzione
			Class.forName("org.sqlite.JDBC");															//imposto come mezzo per la conessione la classe JDBC 
			conn = DriverManager.getConnection("jdbc:sqlite:Database/Libreria.sqlite");					//eseguo la connessione al file Libreria.sqlite
			Finestra.log("Connessione al database avvenuta con successo","Informazioni",1);				//messaggio di conferma
			return conn;																				//restituisco la variabile con per eseguire modifiche al database da altre classi
		} catch (ClassNotFoundException | SQLException e) {												//cattura di eccezioni
			Finestra.log("Connessione al database fallita", "Informazioni", 1);							//messaggio di errore 	
			return null;
		}			
	}
	
	/*
	 * Metodo DBConnector:
	 * 			uguale al metodo dbConnetor solo che non visulaizza il messaggio di connesione 
	 * 			al database
	 */
	public static Connection DBConnector() {															//firma del metodo dbConnector
		try {																							//try per catturare un eccezzione
			Class.forName("org.sqlite.JDBC");															//imposto come mezzo per la conessione la classe JDBC 
			conn = DriverManager.getConnection("jdbc:sqlite:Database/Libreria.sqlite");					//eseguo la connessione al file Libreria.sqlite
			return conn;																				//restituisco la variabile con per eseguire modifiche al database da altre classi
		} catch (ClassNotFoundException | SQLException e) {												//cattura di eccezioni
			Finestra.log("Connessione al database fallita", "Informazioni", 1);							//messaggio di errore 	
			return null;
		}			
	}
	
}
