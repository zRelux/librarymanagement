package com.prog.ui;

import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Database {

	/**
	 * 
	 */
	private static JPanel cards;													//pannello contenitore

	public Database() {

		cards = new JPanel(new CardLayout());										//creazione del panello e come layout un cardlayout
		cards.add(new DatabaseIscritti(), "1");										//aggiunta del pannello database studenti con l'identificativo 1
		cards.add(new DatabaseLibri(), "2");										//aggiunta del pannello database libri con l'identificativo 2
		cards.add(new DatabaseForm(), "3");											//aggiunta del pannello database from con l'identificativo 3
	
	}
	
	public JComponent panel() {
		return cards;
	}
	
	public static void cambia(int n) {												//metodo che mi permette di cambiare il panel corrente da qualsiasi pannello
		CardLayout cl = (CardLayout)(cards.getLayout());							//creo una variabile CardLayout e la euguali a quella del panel cards
		cl.show(cards, String.valueOf(n));											//mosto con il metodo show il pannello con il numero passato per parametro
	}
}
