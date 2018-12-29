package com.prog.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class PrestaLibro extends JPanel {

	private static final long serialVersionUID = 1L;												//variabile che rende unico il frame
	private String autore;																			//varibile contente l'autore
	private String titolo;																			//varibile contente il titolo
	private boolean trovato;																		//varibile che ci dice se si è trovato l'oggetto da cercare
	private String nome;																			//varibile contente il nome
	private String cognome;																			//varibile contente il cognome
	private String prestito = null;																	//varibile contente la data da restiruire
	private JTextField txtTitolo;																	//TextField per il titolo
	private JTextField txtAutore;																	//TextField per l'autore
	private JTextField txtNomeIscritti;																//TextField per il nome
	private JTextField txtCognomeIscritti;															//TextField per il cognome

	/**
	 * Costruttore della classe
	 */
	public PrestaLibro() {																			//firma del costruttore
		setBounds(0, 0, Finestra.base, Finestra.altezza);											//modifica delle dimensione del JPanel

		inizialize();																				//richiamo del metodo inzialize
	}

	/*
	 * Metodo inizialize: ci permette di inizalizzare i componenti del JPanel
	 */
	private void inizialize() {																		//firma del metodo
		
		/*
		 * Pulsante che ci permette di visualizzare i database
		 */
		JButton btnVisualizzaDatabase = new JButton("Visualizza Database");							//creazione del pulsante		
		btnVisualizzaDatabase.addActionListener(new ActionListener() {								//aggiunta dell' action listener
			public void actionPerformed(ActionEvent arg0) {
				if (DatabaseForm.isConnected()) {
					Finestra.cambia(5);
					Database.cambia(1);
					DatabaseIscritti.refresh();
				} else {
					Finestra.log("Non connesso al database", "Errore", 2);
				}
			}
		});

		JLabel lblNome = new JLabel("Titolo:");

		txtTitolo = new JTextField();
		txtTitolo.setToolTipText("Inserisci nome libro");

		JLabel lblAutore = new JLabel("Autore:");

		txtAutore = new JTextField();
		txtAutore.setToolTipText("Inserisci nome libro");
		
		JButton btnControllaDisponibilit = new JButton("Controlla disponibilit\u00E0");				//creazione del pulsante		
		btnControllaDisponibilit.addActionListener(new ActionListener() {							//aggiunta dell' action listener
			
			public void actionPerformed(ActionEvent arg0) {
				if (controllaLibro())																//controllo che i valori siano corretti
					controllaDatabase("Libri", "Titolo", "Autore");									//se si cerco il libro nel database
			}
		});

		/*
		 * Label
		 */
		JLabel lblInformazioniLibro = new JLabel("Informazioni Libro"); 							//creazione della label
		lblInformazioniLibro.setHorizontalAlignment(SwingConstants.CENTER);							//modifica della posizione del testo
		lblInformazioniLibro.setFont(new Font("Tahoma", Font.BOLD, 14));							//modifica del font

		/*
		 * Label
		 */
		JLabel lblNomeStudente = new JLabel("Nome:");												//crezione della label
		
		/*
		 * Label
		 */
		JLabel lblCognome = new JLabel("Cognome:");													//creazione della label

		/*
		 * TextField per il nome
		 */
		txtNomeIscritti = new JTextField();															//creazione della textfield
		txtNomeIscritti.setToolTipText("Inserisci nome studente");									//modifica del testo di hover

		/*
		 * TextField per il cognome
		 */
		txtCognomeIscritti = new JTextField();														//creazione della textfield
		txtCognomeIscritti.setToolTipText("Inserisci cognome studente");							//modifica del testo di hover

		/*
		 * Label
		 */
		JLabel lblInformazioniStudente = new JLabel("Informazioni Studente");                       //creazione della label
		lblInformazioniStudente.setHorizontalAlignment(SwingConstants.CENTER);						//modifica della posizione
		lblInformazioniStudente.setFont(new Font("Tahoma", Font.BOLD, 14));							//modifica del font

		/*
		 * Puslsante che ci permette di controllare l'esistenza di una persona nel database
		 */
		JButton btnControllaPresenza = new JButton("Controlla presenza");							//creazione del pulsante		
		btnControllaPresenza.addActionListener(new ActionListener() {								//aggiunta dell' action listener

			public void actionPerformed(ActionEvent arg0) {
				if (controllaIscritto())															//se i valori sono inseriti correttamente
					controllaDatabase("Libreria", "Nome", "Cognome");								//si cerca la persona nel database

			}
		});
		btnControllaPresenza.setToolTipText("Controlla presenza dell'iscritto");					//modifica del testo di hover
		
		/*
		 * Label
		 */
		JLabel lblMesiInPrestito = new JLabel("Mesi in prestito");									//creazione della label
		lblMesiInPrestito.setFont(new Font("Tahoma", Font.BOLD, 11));								//modifica del font
		
		/*
		 * Spinner che ci permette di scegliere il numero di mesi in prestito
		 */
		JSpinner spinner = new JSpinner();															//creazione dello spinner
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));										//aggiunta di un modello allo spinner con valore minimo 1 valore iniziale 1 valore massimo 5 valore di ogni click 1
		
		/*
		 * Pulsante che ci permette di prestare un libro
		 */
		JButton btnPrestaLibro = new JButton("Presta Libro");										//creazione del pulsante		
		btnPrestaLibro.addActionListener(new ActionListener() {										//aggiunta dell' action listener
			private PreparedStatement pst;															//dichiarazione di una variabile
			private PreparedStatement psa;															//dichiarazione di una variabile
			private Calendar cal = Calendar.getInstance();											//dichiarazione del calendario

			public void actionPerformed(ActionEvent arg0) {								
																				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");									//creo un dateformat che ci permette di formattare una data
				cal.add(Calendar.MONTH, (int) spinner.getValue());									//aggiungo il numero di mesi alla data corrente
				prestito = df.format(cal.getTime());												//metto il valore della data in prestito
				System.out.println(prestito);														//stampo nella console
				
				if (controllaLibro() && controllaIscritto()) {										//se i valori sono inseriti correttamente
					String query = "update Libreria set Restituire = ?, Libro = ? where Nome = ? and cognome = ? and Libro = ?";							//preparo la query
					try {
						pst = PannelloPrincipale.con.prepareStatement(query);						//eseguo la query
						pst.setString(1, prestito);													//imposto i valori ? della query con la variabile associata
						pst.setString(2, titolo);
						pst.setString(3, nome);
						pst.setString(4, cognome);
						pst.setString(5, "NO");
						pst.execute();																//eseguo la query
						pst.close();																//chiudo lo statement
						Finestra.log("Libro prestato", "Informazioni", 1);
					} catch (SQLException e) {
						e.printStackTrace();														//printo la stack di una possibile eccezione
					}

					String sql = "update Libri set Disponibile = ? where Titolo = ? and Autore = ?";	//preparo la query per la tabella dei libri
					try {
						psa = PannelloPrincipale.con.prepareStatement(sql);								//eseguo la query
						psa.setString(1, "NO");															//imposto i valori ? della query con la variabile associata									
						psa.setString(2, titolo);	
						psa.setString(3, autore);
						psa.execute();																	//eseguo la query
						psa.close();																	//chiudo lo statement
					} catch (SQLException e) {
						e.printStackTrace();															//printo la stack di una possibile eccezione
					}

				}
			}
		});
		
		
		/*
		 * Layout del JPanel
		 */
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(112)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInformazioniLibro, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(lblInformazioniStudente, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNomeStudente, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtNomeIscritti, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtTitolo, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAutore, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnControllaDisponibilit)
								.addComponent(txtAutore, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnControllaPresenza, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCognomeIscritti, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(lblMesiInPrestito)
					.addGap(18)
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVisualizzaDatabase, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPrestaLibro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(52))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInformazioniLibro)
						.addComponent(btnVisualizzaDatabase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtTitolo)
						.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAutore, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAutore, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnControllaDisponibilit)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInformazioniStudente, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMesiInPrestito, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPrestaLibro))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeStudente, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNomeIscritti, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCognomeIscritti, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnControllaPresenza)
					.addGap(183))
		);

		setLayout(groupLayout);

	}
	
	/*
	 * Metodo controllaDatabase: ci permette di controllare se l'elemento che si cerca è presente in esso
	 */
	public void controllaDatabase(String tabella, String campo1, String campo2) {									//firma del metodo
		boolean uno;																								//creazione della variabile che ci permette di trovate 
		trovato = false;																							//inizializzazione della varibile trovato
		String query = "SELECT * FROM " + tabella + " WHERE (" + campo1 + "," + campo2 + ") = (?,?)";				//crezione della query da eseguire

		PreparedStatement st;																						//dichiarazione della variabile per eseguire la query
		try {	
			st = PannelloPrincipale.con.prepareStatement(query);													//ugualio st al varole processato della query
			if (campo1.equalsIgnoreCase("Nome")) {																	//se sto cercando una persona
				st.setString(1, nome);																				//impongo come primo valore il nome
				st.setString(2, cognome);																			//impongo come secondo valore il cognome
				System.out.println(nome + " " + cognome);															//printo cosa sto cercando
				uno = true;																							//e dico che l'eseguizione da pare è la prima
			} else {
				st.setString(1, titolo);																			//altirmenti sto cercando un libro e impongo come primo valore il titolo
				st.setString(2, autore);																			//e il secondo l'autore
				System.out.println(titolo + " " + autore);															//printo alla console cosa sto cercando
				uno = false;																						//impongo cheil ciclo è il secondo
			}

			ResultSet res = st.executeQuery();																		//eseguo la query e metto i risultati in un resultset

			while (res.next()) {																					//eseguo un ciclo per ogni valore
				if (uno == true) {																					//se è una persona
					if ((res.getString("Nome").toLowerCase().equalsIgnoreCase(nome)) && (res.getString("Cognome").toLowerCase().equalsIgnoreCase(cognome))) {				//controllo che i valori sia uguali
						if (res.getString("Libro").equals("NO")) {																											//se la persona non ha in perstito un libro 
							Finestra.log("Iscritto presente e può prendere un libro", "Informazioni", 1);																	//messaggio di informazioni
							trovato = true;																																	//trovato lo ugualio a true
							break;																																			//ed interrompo il ciclo
						} else {
							Finestra.log("Iscritto presente ma NON può prendere un libro ha già in prestito "																//altrimenti se non può prendere un libro printo un messaggio 
									+ res.getString("Libro"), "Informazioni", 1);																		
							trovato = true;																																	//impongo che lo trovato
							break;																																			//interronpo il ciclo
						}
					}
				} else {
					if ((res.getString("Titolo").toLowerCase().equalsIgnoreCase(titolo)) && (res.getString("Autore").toLowerCase().equalsIgnoreCase(autore))) {				//controllo che i valori del libro siano uguali
						if (!res.getString("Disponibile").equals("NO")) {																									//se il libro non è preso in prestito 
							Finestra.log("Libro presente e può essere preso in prestito", "Informazioni", 1);																//mostro un messaggio ed ipongo che lo trovato
							trovato = true;																																				
							break;																																			//termino il ciclo
						} else {
							Finestra.log("Libro presente ma NON può essere preso in prestito", "Informazioni", 1);															//altrimenti se il libro è gia preso
							trovato = true;																																	//printo un messaggio ed impongo che ljo trovato
							break;																																			//termino il ciclo
						}
					}

				}

			}
			if (trovato == false) {																																			//nel caso non avessi trovato nulla
				Finestra.log("Non presente nel database", "Errore", 2);																										//mostro un messaggio di errore
			}
		} catch (SQLException e) {																																			//catturo un eventuale eccezione
			e.printStackTrace();																																			//preinto lo tsack dell'eccezione
		}
	}

	/*
	 * Metodo controlla Libro: ci permette di controllare che i campi siano inseriti
	 */
	public boolean controllaLibro() {																																		//firma del metodo
		titolo = txtTitolo.getText();																																		//ugualio il valore della texfield al titolo
		autore = txtAutore.getText();																																		//ugualio il valore della texfield all' autore
		if ((titolo == null) || (titolo.equals("")) || (autore == null) || (autore.equals(""))) {																			//se i valori inseriti sono sbagliati
			Finestra.log("Campo mancante", "Errore", 2);																													//mostro un messaggio di errore
			return false;																																					//e restutisco che non sono inseriti
		} else {
			return true;																																					//altrimenti restituisco che sono inseriti
		}
	}
	
	/*
	 * Metodo controlla Iscritto: ci permette di controllare che i campi siano inseriti
	 */
	public boolean controllaIscritto() {																																		//firma del metodo
		nome = txtNomeIscritti.getText();																																		//ugualio il valore della texfield al nome
		cognome = txtCognomeIscritti.getText();																																	//ugualio il valore della texfield al cognome
		if ((nome == null) || (nome.equals("")) || (cognome == null) || (cognome.equals(""))) {																					//se i valori inseriti sono sbagliati
			Finestra.log("Campo mancante", "Errore", 2);																														//mostro un messaggio di errore
			return false;																																						//e restutisco che non sono inseriti
		} else {
			return true;																																						//altrimenti restituisco che sono inseriti
		}
	}

}
