package com.prog.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Informazioni extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;													//variabile per rendere unico il frame

	/**
	 * Creazione del pannello
	 */
	public Informazioni() {																				//firma del costruttore
		setBounds(0,0,Finestra.base,Finestra.altezza);													//modifica delle dimensioni
		setLayout(null);																				//modifica del layout
		
		/*
		 * TextArea con un testo di informazione
		 */
		JTextArea txtrQuestoProgramma = new JTextArea();												//creazione del textArea															
		txtrQuestoProgramma.setEditable(false);															//lo rendo non modificabile
		txtrQuestoProgramma.setBackground(UIManager.getColor("Panel.background"));						//modifico il colore del background
		txtrQuestoProgramma.setFont(new Font("Monospaced", Font.ITALIC, 15));							//modifico il font
		txtrQuestoProgramma.setText("Questo programma \u00E8 stato eseguito per il progetto di informatica.\r\nEsso consisteva nel fare un programma utilizzando la grafica Java.");		//inserisco il testo
		txtrQuestoProgramma.setBounds(90, 80, 650, 500);												//modifca delle dimensione della textarea
		add(txtrQuestoProgramma);																		//aggiunta al pannello
		
		/*
		 * LAbel
		 */
		JLabel lblInformazioni = new JLabel("Informazioni");											//creazione della label
		lblInformazioni.setHorizontalAlignment(SwingConstants.CENTER);									//modifica dell'orientramento del testo
		lblInformazioni.setFont(new Font("Tahoma", Font.BOLD, 14));										//modifica del font
		lblInformazioni.setBounds(90, 40, 650, 40);														//modifica delle dimensioni
		add(lblInformazioni);																			//aggiunta al pannello

	}
}
