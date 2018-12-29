package com.prog.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class PannelloPrincipale extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;											//variabile che rende unico il frame
	//path delle immagini
	private String iconAggiungi = "\\src\\immagini\\imagePersona.png", iconLibro = "\\src\\immagini\\imageLibro.png",	iconDatabase = "\\src\\immagini\\imageDatabase.png", iconNotifiche = "\\src\\immagini\\imageNotifiche.png";
	private static JButton btnDisconnetti;														//creazione del pulsante  per connettersi
	private static JButton btnConnectToDatabase;												//creazione del pulsante per disconnettersi
	public static Connection con;																//creazione della variabile per la connessione
	
	/**
	 * Create the panel.
	 */
	public PannelloPrincipale() {
		setBounds(0, 0, Finestra.base, Finestra.altezza);										//modifico i lati del pannello

		inizialize();																			//richiamo il metodo per inizializzare il pannello

	}

	private void inizialize() {

		/*
		 * Pulsante per l'aggiunta di una persona
		 * 
		 */
		JButton btnAggiungiPersona = new JButton("");											//creazione del pulsante	
		btnAggiungiPersona.setBackground(new Color(255, 255, 255));								//modifica del background
		btnAggiungiPersona.setMnemonic('A');													//aggiungo la combinazione di tasti alt+A al pulsante
		btnAggiungiPersona.setToolTipText("Aggiungi una persona");								//aggiungo il testo di hover
		btnAggiungiPersona.setIcon(new ImageIcon(System.getProperty("user.dir") + iconAggiungi));		//modifico l'icona del pulsante	
		btnAggiungiPersona.addActionListener(new ActionListener() {								//aggiungo l'azione del pulsante
			public void actionPerformed(ActionEvent e) {										//aggiunta del metodo action performed
				Finestra.cambia(2);																//che ci permette di cambiare il pannello

			}
		});

		/*
		 * Pulsante per il prestito di un libro
		 */
		JButton btnPrestaLibro = new JButton("");												//creazione del pulsante per prestare il libro
		btnPrestaLibro.setBackground(new Color(255, 255, 255));									//modifica del background
		btnPrestaLibro.setIcon(new ImageIcon(System.getProperty("user.dir") + iconLibro));				//modifica dell'icona del pulsante
		btnPrestaLibro.setMnemonic('P');														//aggiungo la combinazione alt+P
		btnPrestaLibro.setToolTipText("Presta un libro");										//modifico il testo di hover 
		btnPrestaLibro.addActionListener(new ActionListener() {									//aggiungo il metodo action performed 
			public void actionPerformed(ActionEvent arg0) {										//firma del metodo
					Finestra.cambia(6);															//cambio il pannello corrente
			}
		});
		
		/*
		 * Pulsante per visualizzare i database
		 */
		JButton btnVisualizza = new JButton("");												//creazione del pulsante per visualizzare i database
		btnVisualizza.setBackground(new Color(255, 255, 255));									//cambio del background
		btnVisualizza.setIcon(new ImageIcon(System.getProperty("user.dir") + iconDatabase));				//cambio del'icona del pulsante
		btnVisualizza.addActionListener(new ActionListener() {									//aggiunta del action listener
			public void actionPerformed(ActionEvent arg0) {	
				if (DatabaseForm.isConnected()) {												//se si fosse connessi al database 
					Finestra.cambia(5);															//si cambia pannello della finestra 
					Database.cambia(1);															//si imposta la prima schermata del database
					DatabaseIscritti.refresh();													//e si refresha la tabella
				} else {
					Finestra.log("Ti devi connettere al database", "Errore", 2);				//in caso non si fosse connessi visualizzo un messaggio di errore
				}
			}
		});
		btnVisualizza.setMnemonic('V');															//aggiungo al pulsante la combinazione alt+V
		btnVisualizza.setToolTipText("Visualizza database");									//imposto il testo di hover

		/*
		 * Pulsante per connettersi al database
		 */
		btnConnectToDatabase = new JButton("Connettiti al database");							//creo il pulsante per connettersi al database
		btnConnectToDatabase.setToolTipText("Connettiti al database");							//imposto il testo di hover
		btnConnectToDatabase.setMnemonic('C');													//aggiungo la combinazione di tasti alt+C
		btnConnectToDatabase.addActionListener(new ActionListener() {							//aggiunta del action performed
			public void actionPerformed(ActionEvent e) {										
				Finestra.cambia(5);																//cambio del pannello principale
				Database.cambia(3);																//imposto la schermata del login
			}
		});
		

		/*
		 * Pulsante per la disconnessione dal database
		 */
		btnDisconnetti = new JButton("Disconnetti");											//creazione del pulsante per la disconnesione
		btnDisconnetti.setToolTipText("Disconnettiti dal database");							//modifica del testo di hover
		btnDisconnetti.setMnemonic('D');														//aggiunta della combinazione alt+D
		btnDisconnetti.setEnabled(false);														//lo disattivo
		btnDisconnetti.addActionListener(new ActionListener() {									//aggiunta del action performed
			public void actionPerformed(ActionEvent arg0) {
				logNonEseguito();																//richiamo del metodo per la disconnessione dal database
				DatabaseForm.disconetti();														//e la effetiva disconnessione

			}
		});
		

		
		/*
		 * Pulsante per la visualizzazione di chi dovrebbe consegnare un libro
		 */
		JButton btnNotifiche = new JButton("");													//creazione del pulsante per visualizzare le notifiche
		btnNotifiche.setToolTipText("Notifiche");												//modifica del testo di hover
		btnNotifiche.setMnemonic('N');															//aggiunta della combinazione alt+N
		btnNotifiche.setIcon(new ImageIcon(System.getProperty("user.dir") + iconNotifiche));				//aggiunta del'icon
		btnNotifiche.addActionListener(new ActionListener() {									//aggiunta del action performed
			public void actionPerformed(ActionEvent arg0) {	
				if(DatabaseForm.isConnected()) {
					Finestra.cambia(7);																//cambio della schermata
					Notifiche.caricaTabella();														//carico la tabella delle notifiche
				}else {
					Finestra.log("Non connesso al database", "Errore", 2);
				}
			}
		});


		
		/*
		 * Label per la registrazione
		 */
		JLabel lblRegistra = new JLabel("Registra");											//creazione della label
		lblRegistra.setFont(new Font("Tahoma", Font.BOLD, 14));									//modifica del font
		lblRegistra.setHorizontalAlignment(SwingConstants.CENTER);								//modifica della sua posizione verticale

		
		/*
		 * Label per la visualizzazione
		 */
		JLabel lblVisualizzaDatabase = new JLabel("Visualizza Database");						//creazione della label
		lblVisualizzaDatabase.setHorizontalAlignment(SwingConstants.CENTER);					//modifica dell'allineamento orizzontale
		lblVisualizzaDatabase.setFont(new Font("Tahoma", Font.BOLD, 14));						//modifica del font

		
		/*
		 * Label per prestare un libro
		 */
		JLabel lblPrestaUnLibro = new JLabel("Presta un libro");								//creazione del label
		lblPrestaUnLibro.setHorizontalAlignment(SwingConstants.CENTER);							//modifica dell'allineamento orizzontale		
		lblPrestaUnLibro.setFont(new Font("Tahoma", Font.BOLD, 14));							//modifica del font

		
		/*
		 * Layout del panel
		 */
		
		JLabel lblNotifiche = new JLabel("Notifiche");
		lblNotifiche.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(101)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDisconnetti, Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblRegistra, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
										.addComponent(btnAggiungiPersona, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPrestaUnLibro, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
										.addComponent(btnPrestaLibro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblVisualizzaDatabase, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(btnConnectToDatabase, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(btnVisualizza, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(629, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNotifiche, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNotifiche, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(256))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(lblNotifiche, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNotifiche)
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblPrestaUnLibro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblRegistra, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVisualizzaDatabase, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnVisualizza, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPrestaLibro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAggiungiPersona, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConnectToDatabase)
						.addComponent(btnDisconnetti))
					.addGap(176))
		);
		setLayout(groupLayout);

	}

	/*
	 * Metodo logEseguito: ci permette di connettersi al database da qualsiasi
	 * classe
	 */
	public static void logEseguito() { // firma del metodo
		btnConnectToDatabase.setText("Connesso"); 			// cambia il testo del pulsante per la connesione
		btnConnectToDatabase.setToolTipText("Connesso");	//cambio del test di hover
		btnConnectToDatabase.setEnabled(false); 			// lo disattivo
		btnDisconnetti.setEnabled(true); 					// e attivo quello per la disconnessione
	}

	/*
	 * Metodo logNonEseguito: ci permette di disconnettersi al database da
	 * qualsiasi classe
	 */
	public static void logNonEseguito() { // firma del metodo
		btnConnectToDatabase.setText("Connettiti al database"); 			// cambio il testo del pulsante per la connessione
		btnConnectToDatabase.setEnabled(true); 								// lo attivo
		btnDisconnetti.setEnabled(false); 									// e disattivo quello per la connessione
	}
}
