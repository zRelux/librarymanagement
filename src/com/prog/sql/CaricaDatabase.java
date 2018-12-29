package com.prog.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.prog.ui.Finestra;
import com.prog.ui.PannelloPrincipale;

public class CaricaDatabase {								
	
	private String pathFile;																				//varibile che conterrà il path del file scelto
	private PreparedStatement pst;																			//variabile pst di tipo Prepered Statement che ci permette di eseguire un query sul database
	
	/*
	 * Costruttore che ci permette di scegliere il file da importare
	 */
	
	
	public CaricaDatabase() {																				//costruttore della classe
		super();																							//richiamo del costruttore della classe Object
	}
	
	/*
	 * 
	 */
	public void sceltaFile() {
		JFileChooser chooser = new  JFileChooser();															//creo un file chooser che permette di creare la schermata di scelta di un file
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Csv File", "csv"));						//impongo come estensioni da scegliere solo i file csv
		chooser.setAcceptAllFileFilterUsed(false);															//non permetto di utilizzare altre estensioni
		chooser.showOpenDialog(null);																		//faccio visualizzare il file chooser
		try {
			File f = chooser.getSelectedFile();																//Creo una variabile f di tipo file che sara uguale al file scelto																								//try
			pathFile = f.getAbsolutePath();																	//ugualio pathFile al path del file scelto
		}catch(NullPointerException e) {																	//catturo un eccezione causata nel caso non si sceglie un file
			e.printStackTrace();																			//printo lo stack del'eccezione
		}
	}
	/*
	 * Metodo importa: ci permette di importare un file .csv 
	 * 				   nel database sqlite, solo se i campi sono 
	 * 				   uguali a 5 altrimenti ci mostra un errore 
	 */
	public void importa(String tabella) {															//Firma del metodo		
		sceltaFile();
		try {																						//try che ci controlla in caso ci fosse un eccezione
			BufferedReader br = new BufferedReader(new FileReader(pathFile));						//creo un buffered reader che ci permette di leggere dal file
			String line;																			//variabile di appoggio chiamata line
			while((line=br.readLine()) != null) {													//eseguo un cilo affinche il file contiene righe da leggere
				String[] riga = line.split(";");													//splitto la rigo dal campo separatore;
				if(riga.length != 5) {																//se i campi di una riga sono minori di 5 visualizzo un errore
					Finestra.log("Valori per riga diverso da 5", "Errore", 2);						//Richiamo del metodo log della classe Finestra
					break;
				}else {
					//creo la query da par eseguire per aggiornare la tabella	
					String query = "insert into " + tabella +" (Titolo,Autore,Data,Genere,Disponibile) values ('" + riga[0] + "','" + riga[1] + "','" + riga[2] + "','" + riga[3] + "','" + riga[4] + "')";
					pst = PannelloPrincipale.con.prepareStatement(query);												//preparo il query
					pst.executeUpdate();															//eseguo il query
				}
			}
			br.close();																				//se il ciclo è finito chiudo il buffer
		} catch (IOException | SQLException e) {													//catch che ci permette di catturare due eccezioni 
			e.printStackTrace();																	//printo lo stack del eccezione
		}
	}	
	
	/*
	 * Metodo importa: ci permette di importare un file .csv 
	 * 				   nel database sqlite, solo se i campi sono 
	 * 				   uguali a 5 altrimenti ci mostra un errore 
	 */
	public void importaIscritti(String tabella) {													//Firma del metodo	
		sceltaFile();
		try {																						//try che ci controlla in caso ci fosse un eccezione
			BufferedReader br = new BufferedReader(new FileReader(pathFile));						//creo un buffered reader che ci permette di leggere dal file
			String line;																			//variabile di appoggio chiamata line
			while((line=br.readLine()) != null) {													//eseguo un cilo affinche il file contiene righe da leggere
				String[] riga = line.split(";");													//splitto la rigo dal campo separatore;
				if(riga.length != 9) {																//se i campi di una riga sono minori di 5 visualizzo un errore
					Finestra.log("Valori per riga diverso da 9", "Errore", 2);						//Richiamo del metodo log della classe Finestra
					break;
				}else {
					//creo la query da par eseguire per aggiornare la tabella	
					String query = "insert into " + tabella +" (Nome,Cognome,Email,Restituire,Telefono,Sesso,Via,Registrazione,Libro) values ('" + riga[0] + "','" + riga[1] + "','" + riga[2] + "','" + riga[3] + "','" + riga[4] + "','" + riga[5] + "','" + riga[6] + "','" + riga[7] + "','" + riga[8] + "')";
					pst = PannelloPrincipale.con.prepareStatement(query);												//preparo il query
					pst.executeUpdate();															//eseguo il query
				}
			}
			br.close();																				//se il ciclo è finito chiudo il buffer
		} catch (IOException | SQLException e) {													//catch che ci permette di catturare due eccezioni 
			e.printStackTrace();																	//printo lo stack del eccezione
		}
	}	
}
