package com.prog.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Registrazione extends JPanel {

	private static final long serialVersionUID = 1L;							//variabile per rendere unico il frame
	private final int BASE = 130, ALTEZZA = 130;								//base ed altezza del frame
	private JTextField txtNome, txtCognome, txtVia, txtTelefono, txtEmail;		//campi per inserimento dati (nome, cognome, indirizzo, telefon e e-mail)
	private String nome = null, cognome = null, iscrizione = null, sesso = null, via = null, tel = null, email= null, reg= null;		//variabili per dati inseriti
	private JFileChooser fileChooser;																									//componente per scegliere i file
	private JDateChooser dateChooser;																									//componente per scegliere data
	private ButtonGroup bt;																												//gruppo di pulsanti
	private String immAggiungi = "\\src\\immagini\\imagePlus.png", immIndietro = "\\src\\immagini/imageIndietro.png", immPulisci = "\\src\\immagini\\imageRefresh.png" ;	//immagini della GUI

	/**
	 * Create the panel.
	 */
	public Registrazione() {
		setBounds(0, 0, Finestra.base, Finestra.altezza);	//modifico le sue dimensioni
		inizializza();										//richiama funzione di inizializzazione
	}

	public void inizializza() {
		/*
		 * Setup del JFrame
		 */	
		fileChooser = new JFileChooser();		//inizializzazione JFileChooser
		bt = new ButtonGroup();
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Solo immagini", ImageIO.getReaderFileSuffixes()));	//imposta filtro file da visualizzare
		fileChooser.setAcceptAllFileFilterUsed(false);	//nega possibilità di selezionare qualsiasi tipo di file
		JLabel lblNome = new JLabel("Nome: *");		//inizializzazione Label del nome
		
		//inizializzazione TextField del nome
		txtNome = new JTextField();					//inizializza TextField
		txtNome.setToolTipText("Inserisci nome");	//imposta testo
		txtNome.setColumns(10);						//imposta numero colonne

		JLabel lblCognome = new JLabel("Cognome: *");	//inizializzazione Label del cognome
		
		//inizializzazione TextField del cognome
		txtCognome = new JTextField();						//inizializza TextField
		txtCognome.setToolTipText("Inserisci cognome:");	//imposta testo
		txtCognome.setColumns(10);							//imposta numero colonne

		JLabel lblData = new JLabel("Data di iscrizione:");		//inizializzazione Label della data di iscrizione

		dateChooser = new JDateChooser();						//inizializzazione calendario per scegliere data
		dateChooser.setDateFormatString("dd/MM/yyyy");			//imposta formato data

		JLabel image = new JLabel("");							//inizializzazione Label contenente immagine
		image.setToolTipText("Immagine studente");				//imposta consiglio da far apparire

		JButton btnImage = new JButton("Upload");				//inizializzazione bottone per caricare foto
		btnImage.setToolTipText("Carica foto");					//imposta consiglio da far apparire
		
		/*
		 * ActionListener bottone di upload
		 */
		btnImage.addActionListener(new ActionListener() {

			private File selectedFile;	//variabile per file da caricare

			public void actionPerformed(ActionEvent arg0) {
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {	//se viene premuto tasto di conferma
					selectedFile = fileChooser.getSelectedFile();	//selectedFile assume il valore del file selezionato dall'utente
					System.out.println(selectedFile.getName());		//stampa sulla console il nome del file
				Image img = new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(BASE, ALTEZZA,	//ottiene l'immagine selezionata e modifica le dimensioni
						Image.SCALE_DEFAULT);
				image.setIcon(new ImageIcon(img));					//imposta l'immagine come icona
				}
			}
		});

		JButton btnAggiungi = new JButton("Aggiungi");	//crea bottone Aggiungi
		btnAggiungi.setIcon(new ImageIcon(System.getProperty("user.dir") + (immAggiungi)));	//imposta icona del bottone
		btnAggiungi.setToolTipText("Aggiungi studente");							//imposta consiglio da far apparire
		/*
		 * ActionListener bottone Aggiungi
		 */
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome = txtNome.getText();											//imposta nome ottenendo valore dal TextField txtNome
			    cognome = txtCognome.getText();										//imposta cognome ottenendo valore dal TextField txtCognome
			    via = txtVia.getText();												//imposta indirizzo ottenendo valore dal TextField txtVia
			    tel = txtTelefono.getText();										//imposta numero di telefono ottenendo valore dal TextField txtTelefono
			    email = txtEmail.getText();											//imposta e-mail ottenendo valore dal TextField txtEmail		
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 				//imposta formato data
				reg = df.format(new Date());
				Calendar cal = Calendar.getInstance();								//ottiene istanza corrente del calendario
				iscrizione = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();	//ottiene data del iscrizione 
				
				if(iscrizione == null) {	
					iscrizione = df.format(cal.getTime());	//ottiene oggetto Date rappresentante l'orario
					System.out.println(iscrizione);			//stampa valore di iscrizione sulla console
				}
				
				if((nome == null || nome.equals("")) || (cognome == null || cognome.equals("")) || (email.equals("") || email == null) || (!email.contains("@"))) {						//se uno o più campi necessari sono vuoti
					Finestra.log("Campo obbligatorio mancante", "Errore", 2);																					//mostra messaggio di errore
				}else{
					PreparedStatement pst;																														//variabile contenente 
					String query = "insert into Libreria (Nome,Cognome,Email,Restituire,Telefono,Sesso,Via,Registrazione,Libro) values (?,?,?,?,?,?,?,?,?)";	//richiesta al database per aggiunta dati utente
					try {
						pst = PannelloPrincipale.con.prepareStatement(query);	//imposta valore della dichiarazione	
						pst.setString(1, nome);				//inserisci nel primo parametro valore di nome
						pst.setString(2, cognome);			//inserisci nel secondo parametro valore di cognome
						pst.setString(3, email);			//inserisci nel terzo parametro valore di email
						pst.setString(4, iscrizione);			//inserisci nel quarto parametro valore di prestito
						pst.setString(5, tel);				//inserisci nel quinto parametro valore di tel
						pst.setString(6, sesso);			//inserisci nel sesto parametro valore di sesso
						pst.setString(7, via);				//inserisci nel settimo parametro valore di via
						pst.setString(8, reg);				//inserisci nel ottavo parametro valore di reg
						pst.setString(9, "NO");				//inserisci nel nono parametro quale libro ha preso l'utente, il valore predefinito è NO
						pst.execute();
						JOptionPane.showOptionDialog(null, "Studente inserito con successo", "Informazione",	JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);	//mostra finestra che notifica l'avvenuto inserimento dell'utente nel database
						pst.close();	//chiudi lo statement
						pulisci();		//svuota tutti i TextField
						Finestra.cambia(1);		//passa a nuova finestra
					} catch (SQLException e1) {		//cattura eccezioni SQLException
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});

		/*
		 * Pulsante per pulire le textfield
		 */
		JButton btnPulisci = new JButton("Pulisci");							//inizializzazione bottone che richiama funzione pulisci()
		btnPulisci.setIcon(new ImageIcon(System.getProperty("user.dir") + immPulisci));	//imposta icona bottone
		btnPulisci.setToolTipText("Pulisci form");								//imposta consiglio da far apparire				
		btnPulisci.addActionListener(new ActionListener() {
			/*
			 * ActionListener bottone btnPulisci
			 */
			public void actionPerformed(ActionEvent e) {
				pulisci();															//quando cliccato, pulisci i TextField
				
			}
		});

		/*
		 * Pulsante per uscire dal JPanel
		 */
		JButton btnIndietro = new JButton("Indietro");								//bottone per tornare al precedente pannello
		btnIndietro.setIcon(new ImageIcon(System.getProperty("user.dir") + immIndietro));	//imposta icona bottone
		btnIndietro.setToolTipText("Torna indietro");								//imposta consiglio da far apparire	
		/*
		 * ActionListener bottone btnIndietro
		 */
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = Finestra.log("Continuare senza salvare?", "Errore", 2);		//n contiene valore scelto dall'utente dalla finestra di dialogo 

				if (n == JOptionPane.OK_OPTION) {									//se l'utente ha scelto OK
					pulisci();														//si pulisce le textfield
					Finestra.cambia(1);												//torna al pannello precedente
				}
			}
		});

		JLabel lblGenere = new JLabel("Sesso:");	//Label selezione sesso

		/*
		 * Radio button per il sesso maschile
		 */
		JRadioButton rdbtnMaschio = new JRadioButton("Maschio");	//opzione "Maschio"
		/*
		 * ActionListener bottone rdbtnMaschio
		 */
		rdbtnMaschio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sesso = rdbtnMaschio.getText();						//imposta valore di sesso
			}
		});
		bt.add(rdbtnMaschio);	//aggiungi bottone al gruppo
		
		
		/*
		 * Radio button per il sesso femminile
		 */
		JRadioButton rdbtnFemmina = new JRadioButton("Femmina");	//opzione "Femmina"
		/*
		 * ActionListener bottone rdbtnFemmina
		 */
		rdbtnFemmina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sesso = rdbtnFemmina.getText();						//imposta valore di sesso
			}
		});
		bt.add(rdbtnFemmina);	//aggiungi bottone al gruppo
		
		/*
		 * Label
		 */
		JLabel lblEmail = new JLabel("Email: *");	//Label inserimento e-mail
		JLabel lblVia = new JLabel("Via:");			//Label inserimento indirizzo

		/*
		 * Textfield per inserire la via
		 */
		txtVia = new JTextField();					//inizializzazione TextField per inserimento indirizzo
		txtVia.setToolTipText("Inserisci via:");	//imposta consiglio da far apparire	
		txtVia.setColumns(10);						//imposta numero colonne

		/*
		 * Label
		 */
		JLabel lblTelefono = new JLabel("Telefono:");	//Label inserimento numero di telefono

		/*
		 * Textfield per inserire il numero di telefono
		 */
		txtTelefono = new JTextField();								//inizializzazione TextField per inserimento numero di telefono
		txtTelefono.setToolTipText("Inserisci n\u00B0 telefono:");	//imposta consiglio da far apparire	
		txtTelefono.setColumns(10);									//imposta numero colonne

		
		/*
		 * Textfield per inserire l'email
		 */
		txtEmail = new JTextField();								//inizializzazione TextField per inserimento e-mail
		txtEmail.setToolTipText("Inserisci email:");				//imposta consiglio da far apparire	
		txtEmail.setColumns(10);									//imposta numero colonne
		
		
		/*
		 * Label
		 */
		JLabel lblInformazioniNuovoStudente = new JLabel("Informazioni nuovo studente");	//Label contenente informazioni studente
		lblInformazioniNuovoStudente.setHorizontalAlignment(SwingConstants.CENTER);			//centra il Label
		lblInformazioniNuovoStudente.setFont(new Font("Tahoma", Font.BOLD, 15));			//imposta il font utilizzato

		
		
		/*
		 * Layout del JPanel
		 */
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(476, Short.MAX_VALUE)
					.addComponent(btnIndietro)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPulisci, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAggiungi)
					.addGap(64))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(494))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblGenere, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(rdbtnMaschio)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(rdbtnFemmina))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(image, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
										.addGap(124))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnImage)
										.addGap(183)))))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(221)
					.addComponent(lblInformazioniNuovoStudente, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(233, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInformazioniNuovoStudente, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
								.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(lblData, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(image, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGenere, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnMaschio)
								.addComponent(rdbtnFemmina))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVia, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVia, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnImage, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(112)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPulisci, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(35))
		);
		setLayout(groupLayout);
	}
	
	/**
	 * Metodo pulidci: ci permette di pulire tutte le textfield presenti nel JPanel
	 */
	public void pulisci() {			//firma del metodo
		txtCognome.setText("");		//imposta Cognome su ""
		txtNome.setText("");		//imposta Nome su ""
		txtEmail.setText("");		//imposta E-mail su ""
		txtTelefono.setText("");	//imposta Telefono su ""
		txtVia.setText("");			//imposta Indirizzo su ""
		((JTextField)dateChooser.getDateEditor().getUiComponent()).setText("");		//imposta TextField della data su ""
	}
}
