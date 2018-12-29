package com.prog.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.prog.sql.CaricaDatabase;


public class Menu implements ActionListener {

	/*
	 * Stringhe contenenti il path delle immagini necessarie
	 */
	private String immVediDatabase = "\\src\\immagini\\imageDatabaseMiniatura.png";
	private String immChiudi = "\\src\\immagini\\imageClose.png";
	private String immConnect = "\\src\\immagini\\imageConnect.png";
	private String immDisconnect = "\\src\\immagini\\imageDisconnect.png";
	private String immInfo = "\\src\\immagini\\imageInformazioni.png";
	private String immNuovo = "\\src\\immagini\\imagePersonaMiniatura.png";
	private String immPresta = "\\src\\immagini\\imageLibroMiniatura.png";
	private String immIndietro = "\\src\\immagini\\imageIndietroMiniatura.png";

	

	/*
	 * Metodo createMenuBar: che permette di creare la
	 * 						 barra del menu del frame
	 */
	public JMenuBar createMenuBar() {									//firma del metodo												
        JMenuBar menuBar;												//dichiarazione della barra del menù
        JMenu fileMenu, helpMenu, dataBaseMenu; 						//dichiarazione dei menu 
        //dichiarazione dei sotto menù di ogni menù
        JMenuItem nuovoItem, chiudiItem, indietroItem, prestaItem, aboutItem, infoItem, connectItem, disconnectItem , vediItem, importaFile, importaIscritti;		
        
        System.out.println(System.getProperty("user.dir") + immNuovo);
        //CREO LA BARRA DEL MENU
        menuBar = new JMenuBar();

        //COSTRUZIONE DEL MENU FILE
        fileMenu = new JMenu("File");																	//creazione del menu chiamato file
        fileMenu.setMnemonic(KeyEvent.VK_F);															//aggiunta della combinazione alt+F
        fileMenu.getAccessibleContext().setAccessibleDescription("Menù del file");						//Modifica della descrizione del menu		
        menuBar.add(fileMenu);																			//aggiunta del menu alla barra

        //CREO IL SOTTO MENU DI FILE
        
        nuovoItem = new JMenuItem("Nuovo Iscritto");													//creazione del sotto menù chiamato nuovo iscritto
        nuovoItem.setMnemonic(KeyEvent.VK_N);															//aggiunta della combinazione alt+N
        nuovoItem.getAccessibleContext().setAccessibleDescription("Crea un nuovo iscritto");			//modifica della descrizione
        nuovoItem.addActionListener(this);																//aggiunta degli ascoltatori 
        nuovoItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immNuovo));								//aggiunta di un icona al sotto menù
        fileMenu.add(nuovoItem);																		//aggiunta al menu "File"
        
        
        prestaItem = new JMenuItem("Presta un libro");													//creazione del sotto menù chiamato presta libro
        prestaItem.setMnemonic(KeyEvent.VK_P);															//aggiunta della combinazione alt+P
        prestaItem.getAccessibleContext().setAccessibleDescription("Presta un libro");					//modifica della descrizione
        prestaItem.addActionListener(this);																//aggiunta degli ascoltatori 
        prestaItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immPresta));							//aggiunta di un icona al sotto menù
        fileMenu.add(prestaItem);																		//aggiunta al menu "File"
        
        
        indietroItem = new JMenuItem("Indietro");														//creazione del sotto menù chiamato indietro
        indietroItem.setMnemonic(KeyEvent.VK_I);														//aggiunta della combinazione alt+N
        indietroItem.getAccessibleContext().setAccessibleDescription("Indietro");						//modifica della descrizione
        indietroItem.addActionListener(this);															//aggiunta degli ascoltatori 
        indietroItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immIndietro));						//aggiunta di un icona al sotto menù
        fileMenu.add(indietroItem);																		//aggiunta al menu "File"
        		
        
        chiudiItem = new JMenuItem("Chiudi");															//creazione del sotto menù chiamato chiudi
        chiudiItem.setMnemonic(KeyEvent.VK_C);															//aggiunta della combinazione alt+N
        chiudiItem.getAccessibleContext().setAccessibleDescription("Chiudi");							//modifica della descrizione
        chiudiItem.addActionListener(this);																//aggiunta degli ascoltatori 
        chiudiItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immChiudi));							//aggiunta di un icona al sotto menù
        fileMenu.add(chiudiItem);																		//aggiunta al menu "File"
        
        
        //Costruisco il menu del database
        dataBaseMenu = new JMenu("Database");															//creazione del menu chiamato file
        dataBaseMenu.setMnemonic(KeyEvent.VK_F);														//aggiunta della combinazione alt+F
        dataBaseMenu.getAccessibleContext().setAccessibleDescription("Menù del Database");				//Modifica della descrizione del menu		
        menuBar.add(dataBaseMenu);																		//aggiunta del menu alla barra

        
        connectItem = new JMenuItem("Connetti");														//creazione del sotto menù chiamato chiudi
        connectItem.setMnemonic(KeyEvent.VK_C);															//aggiunta della combinazione alt+N
        connectItem.getAccessibleContext().setAccessibleDescription("Connetti");						//modifica della descrizione
        connectItem.addActionListener(this);															//aggiunta degli ascoltatori 
        connectItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immConnect));							//aggiunta di un icona al sotto menù
        dataBaseMenu.add(connectItem);																	//aggiunta al menu "Database"
        
        
        disconnectItem = new JMenuItem("Disconnetti");													//creazione del sotto menù chiamato chiudi
        disconnectItem.setMnemonic(KeyEvent.VK_D);														//aggiunta della combinazione alt+N
        disconnectItem.getAccessibleContext().setAccessibleDescription("Disconnetti");					//modifica della descrizione
        disconnectItem.addActionListener(this);															//aggiunta degli ascoltatori 
        disconnectItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immDisconnect));					//aggiunta di un icona al sotto menù
        dataBaseMenu.add(disconnectItem);																//aggiunta al menu "Database"
        
        
        importaFile = new JMenuItem("Importa Libri");													//creazione del sotto menù chiamato importa libri
        importaFile.setMnemonic(KeyEvent.VK_L);															//aggiunta della combinazione alt+I
        importaFile.getAccessibleContext().setAccessibleDescription("Importa Libri");					//modifica della descrizione
        importaFile.addActionListener(this);															//aggiunta degli ascoltatori 
        dataBaseMenu.add(importaFile);																	//aggiunta al menu "Database"
        
        importaIscritti = new JMenuItem("Importa Iscritti");											//creazione del sotto menù chiamato importa iscritti
        importaIscritti.setMnemonic(KeyEvent.VK_I);														//aggiunta della combinazione alt+I
        importaIscritti.getAccessibleContext().setAccessibleDescription("Importa Iscritti");			//modifica della descrizione
        importaIscritti.addActionListener(this);														//aggiunta degli ascoltatori 
        dataBaseMenu.add(importaIscritti);																//aggiunta al menu "Database"
        
        
        vediItem = new JMenuItem("Vedi Database");														//creazione del sotto menù chiamato vedi database
        vediItem.setMnemonic(KeyEvent.VK_I);															//aggiunta della combinazione alt+I
        vediItem.getAccessibleContext().setAccessibleDescription("Vedi Database");						//modifica della descrizione
        vediItem.addActionListener(this);																//aggiunta degli ascoltatori 
        vediItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immVediDatabase));						//aggiunta di un immagine
        dataBaseMenu.add(vediItem);																		//aggiunta al menu "Database"    
        
        
        //Costruisco il menu del Help/Aiuto.
        helpMenu = new JMenu("Aiuto");																	//creazione del menu chiamato Aiuto
        helpMenu.setMnemonic(KeyEvent.VK_H);															//aggiunta della combinazione alt+H
        helpMenu.getAccessibleContext().setAccessibleDescription("Menù del Database");					//Modifica della descrizione del menu		
        menuBar.add(helpMenu);																			//aggiunta del menu alla barra
        
        aboutItem = new JMenuItem("About");																//creazione del sotto menù chiamato about
        aboutItem.setMnemonic(KeyEvent.VK_A);															//aggiunta della combinazione alt+A
        aboutItem.getAccessibleContext().setAccessibleDescription("About");								//modifica della descrizione
        aboutItem.addActionListener(this);																//aggiunta degli ascoltatori 
        helpMenu.add(aboutItem);																		//aggiunta al menu "Aiuto"    
        
        infoItem = new JMenuItem("Informazioni");														//creazione del sotto menù chiamato vedi database
        infoItem.setMnemonic(KeyEvent.VK_I);															//aggiunta della combinazione alt+I
        infoItem.getAccessibleContext().setAccessibleDescription("Informazioni");						//modifica della descrizione
        infoItem.addActionListener(this);																//aggiunta degli ascoltatori 
        infoItem.setIcon(new ImageIcon(System.getProperty("user.dir") + immInfo));								//aggiunta di un immagine
        helpMenu.add(infoItem);																			//aggiunta al menu "Aiuto" 
        
        
        return menuBar;																					//restituisco la barra per aggiungerla al menù
    }

  
    
	/*
	 * Metodo action perfomed
	 */
    @Override
    public void actionPerformed(ActionEvent e) {

        JMenuItem jmi = (JMenuItem)e.getSource();														//prima ottengo il menu premuto
        System.out.println("Menu " + jmi.getText());													//stampo il menu
        
        if (jmi.getText().equalsIgnoreCase("chiudi")) {													//se il menu è uguale a chiudi
            System.exit(0);																				//termino il processo
        }
        
        if (jmi.getText().equalsIgnoreCase("nuovo iscritto")) {											//se il menù è uguale a nuovo iscritto
        	Finestra.cambia(2);																			//cambio il pannello della finestra 
        }
        
        if (jmi.getText().equalsIgnoreCase("indietro")) {												//se il menù è uguale a indietro
        	Finestra.cambia(1);																			//torno al menù principale
        }
        
        if (jmi.getText().equalsIgnoreCase("presta libro")) {											//se il menù è uguale a presta libro
        	Finestra.cambia(6);																			//cambio il pannello della finestra
        }
        
        if (jmi.getText().equalsIgnoreCase("about")) {													//se il menù è uguale a nuovo iscritto
        	Finestra.cambia(4);																			//cambio il pannello della finestra
        }
        
        if (jmi.getText().equalsIgnoreCase("informazioni")) {											//se il menù è uguale a informazioni
        	Finestra.cambia(3);																			//cambio il pannello della finestra
        }
        
        if (jmi.getText().equalsIgnoreCase("connetti")) {												//se il menù è uguale a connetti
        	if(!DatabaseForm.isConnected()) {															//se il database non è connesso
        		PannelloPrincipale.logEseguito();														//si esegue l'accesso
        		Finestra.cambia(5);																		//cambio il pannello della finestra
        		Database.cambia(3);																		//cambio il pannello del database a quello di accesso
        	}else {
        		Finestra.log("Già connesso al database", "Informazioni", 1);							//altrimenti mostro un messaggio di informazione
        	}
        	
        }
        
        if (jmi.getText().equalsIgnoreCase("disconnetti")) {											//se il menù è uguale a disconnetti
        	if(DatabaseForm.isConnected()) {															//se il database è connesso
        		Finestra.log("Disconnesso dal database", "Informazione", 1);							//mostro un messaggio di informazione
        		PannelloPrincipale.logNonEseguito();													//e mi disconnetto dal database
        		DatabaseForm.disconetti();																
        	}else {
        		Finestra.log("Già disconnesso dal database", "Informazione", 1);						//altrimenti mostro un messaggio che sono gia disconnesso
        	}
        																
        }
        
        if (jmi.getText().equalsIgnoreCase("vedi database")) {											//se il menù è uguale a vedi database
        	if(DatabaseForm.isConnected()) {															//se sono connesso al database
        		Finestra.cambia(5);																		//cambio il pannello della finestra
        		Database.cambia(1);																		//cambio il pannello del database
        		DatabaseIscritti.refresh();																//faccio il refresh della tabella
        	}else 
        		Finestra.log("Non connesso al database", "Errore", 2);									//altrimenti mostro un errore
        }
        
        if (jmi.getText().equalsIgnoreCase("importa libri")) {											//se il menù è uguale a importa libri
        	new CaricaDatabase().importa("Libri");														//richiamo il metodo importa di carica database
        }
        
        if (jmi.getText().equalsIgnoreCase("importa iscritti")) {										//se il menù è uguale a importa iscritti
        	new CaricaDatabase().importaIscritti("Libreria");											//richiamo il metodo importa iscritti di carica database
        }
     
    }
}