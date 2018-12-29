package com.prog.ui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.prog.sql.SqliteConnection;
import com.prog.ui.Finestra;
import com.prog.ui.PannelloPrincipale;

public class DatabaseForm extends JPanel {

	private static final long serialVersionUID = 1L;		//variabile per rendere unico il frame
	private static JTextField txtUserName;					//TextField per inserimento Username
	private static JPasswordField ptxPassword;				//PasswordField per inserimento Password
	private static String user = "admin";					//nome dell'utente
	private static String psw = "admin";					//password dell'utente
	private static String useAcc = "";						//valore inserito nel TextField txtUserName
	private static String pswAcc = "";						//valore inserito nel TextField txtPassword
	private String immLogin = "\\src\\immagini\\imageLogin.png";	//collegamento all'immagine del login
	/**
	 * Create the panel.
	 */
	public DatabaseForm() {
		setBounds(0,0,Finestra.base,Finestra.altezza);		//modifico le sue dimensioni
		
		inizialize();										//richiama funzione di inizializzazione
	}

	private void inizialize() {
		JLabel lblUsername = new JLabel("Username:");				//inizializzazione Label dello Username
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));		//imposta font utilizzato
		
		txtUserName = new JTextField();		//inizializzazione TextField dello Username
		txtUserName.setColumns(10);			//imposta numero delle colonne
			
		JLabel lblPassword = new JLabel("Password:");				//inizializzazione Label della Password
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));		//imposta font utilizzato
		
		ptxPassword = new JPasswordField();		//inizializzazione PasswordField
		
		JButton btnConnect = new JButton("Connettiti");		//bottone per connessione al database
		btnConnect.setFocusable(true);
		/*
		 * ActionListener bottone di connessione
		 */
		btnConnect.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				useAcc = txtUserName.getText();		//ottieni UserName inserito
				pswAcc = ptxPassword.getText();		//ottieni Password inserita
				
				if(useAcc.equals(user) && pswAcc.equals(psw)) {		//se UserName e Password sono corretti
						PannelloPrincipale.con = SqliteConnection.dbConnector();				//connetti al Database SQL
						PannelloPrincipale.logEseguito();			//cambia aspetto dei componenti di Login al database
						Finestra.cambia(1);							//cambia pannello visualizzato
				}else {
					Finestra.log("Username o Password errati", "Informazioni", 1);	//visualizza avvertimento di Password o UserName errato
				}
			}
		});
		btnConnect.setToolTipText("Connettiti al database");	//imposta consiglio da far apparire

		
		JLabel lblImage = new JLabel("");	//inizializzazione Label contenente immagine
		lblImage.setIcon(new ImageIcon(System.getProperty("user.dir") + immLogin));	//imposta icona Label
		lblImage.setToolTipText("admin , admin");	//imposta consiglio da far apparire
		
		
		
		/*
		 * Layout del JPanel
		 */
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(239)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImage)
						.addComponent(btnConnect)
						.addComponent(ptxPassword, 231, 231, 231)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(220, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(80)
					.addComponent(lblImage)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtUserName)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ptxPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addComponent(btnConnect)
					.addGap(234))
		);
		setLayout(groupLayout);
	}
	
	
	/*
	 * Metodo disconetti: ci permette di disconnetersi dal database
	 */
	public static void disconetti() {	//pulisci i TextFiel e il PasswordField
		useAcc = "";
		pswAcc = "";
		txtUserName.setText("");
		ptxPassword.setText("");
	}
	/*
	 * metodo isConected: ci dice se siamo connessi al database
	 */
	public static boolean isConnected() {	
		if(useAcc.equals(user) && pswAcc.equals(psw)) {		//se Password e UserName inseriti sono corretti
			return true;
		}else return false;
	}
}
