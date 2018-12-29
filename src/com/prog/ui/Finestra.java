package com.prog.ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;


public class Finestra extends JFrame {
	
	/**
	 * Parametri della classe
	 */
	private static final long serialVersionUID = 1L;										//variabile per rendere unico il frame
	public static final int base = 800;														//base ed altezza del frame
	public static final int altezza = 600;													//altezza del frame
	private static JPanel cards = new JPanel(new CardLayout());								//pannello contenitore degli altri con un card layout che ce lo permette	
	private String immFrame = "\\src\\immagini\\imageFrame.png";
	
	
	public Finestra(String title) {
		super(title);																	// modifica del suo titolo
		initialize();																	// richiamo del metodo inizialize
	}

	/**
	 * Inizializzazione del frame
	 */
	private void initialize() {
		/*
		 * Setup del JFrame
		 */	
		setBounds(100, 100, base, altezza); 													// modifico le sue dimensioni
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 										// aggiungo l'opzione che mi permettere di terminare il processo con la chusura del JFrame
		setJMenuBar(new Menu().createMenuBar());												//imposto la barra delle frame
		setIconImage(new ImageIcon(System.getProperty("user.dir") + immFrame).getImage());				//imposto l'icona del frame
		cards.add(new PannelloPrincipale(), "1");												//aggiungo ad il pannello contenitore cards i vari pannelli con un etichetta definita dalla stringa "1"
		cards.add(new About(),"4");																//"2"
		cards.add(new Informazioni(), "3");														//"3"
		cards.add(new Registrazione(), "2");													//"4"	
		cards.add(new Database().panel(),"5");													//"5"
		cards.add(new PrestaLibro(), "6");														//"6"
		cards.add(new Notifiche(), "7");														//"7"
		
		setContentPane(cards);														//imposto come pannello principale cards
																			
	}

	/*
	 * Metodo setVisible: ci permette di rendere il JFrame visibile
	 */
	public void setVisible() {														//metodo per rendere visibile il frame 
		setVisible(true);															//chiamo il metodo per rendere visibile il frame
	}
	
	/*
	 * Metodo cambia: ci permette di cambiare il pannello visibile all'interno del JFrame
	 */
	public static void cambia(int n) {												//metodo che mi permette di cambiare il panel corrente da qualsiasi pannello
		CardLayout cl = (CardLayout)(cards.getLayout());							//creo una variabile CardLayout e la euguali a quella del panel cards
		cl.show(cards, String.valueOf(n));											//mosto con il metodo show il pannello con il numero passato per parametro
	}
	
	
	/*
	 *Metodo log: ci permette di visualizzare un messaggio di errore o di informazione tramite il JOptionPane
	 */
	public static int log(String messaggio,String titolo,int tipo) {                //firma del metodo log 
																					//passo per parametro il messaggio il titolo del frame del messaggio ed il tipo del messaggio
		if(tipo == 1) {																//se il tipo è uguale ad uno il messaggio è di informazione
			tipo = JOptionPane.INFORMATION_MESSAGE;									//tipo diventa uguale al valore del messaggio di informazione
		}else 																		//altrimenti
			tipo = JOptionPane.ERROR_MESSAGE;										//tipo diventa uguale al valore del messaggio di errore
		
		 return JOptionPane.showOptionDialog(null, messaggio, titolo,				//restituisco il valore e mostro il pannello in modo da gestire il pulsante scelto 
				JOptionPane.OK_CANCEL_OPTION, tipo, null, null, null);		
	}
	
}
