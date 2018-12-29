package com.prog.ui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class Notifiche extends JPanel {

	
	private static final long serialVersionUID = 1L;									//variabile che rende il Jpanel unico
	private static JTable table;														//Tabella 

	/**
	 * Create the panel.
	 */
	public Notifiche() {
		setBounds(0,0,Finestra.base,Finestra.altezza);									//modifica delle dimensioni del panel
		
		inizialize();																	//richiamo il metodo per costruire il panel
	}

	/*
	 * Metodo inizialize: crea tutti gli elementi del JPanel
	 */
	private void inizialize() {															//firma del metodo
		
		/*
		 * Creazione della label
		 */
		JLabel lblConsegneDiOggi = new JLabel("Consegne di oggi");						//creazione della label
		lblConsegneDiOggi.setHorizontalAlignment(SwingConstants.CENTER);				//metto l'orizzontamento centrale
		lblConsegneDiOggi.setFont(new Font("Tahoma", Font.BOLD, 14));					//modifico il font
		
		
		/*
		 * Dichiarazione della tabella
		 */
		JScrollPane scrollPane = new JScrollPane();					//cerazione scrollpane
		table = new JTable();										//creazione della tabella
		scrollPane.setViewportView(table);							//metto la tabella nello scrollpane
		
		
		/*
		 * Layout del JPanel
		*/
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblConsegneDiOggi, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblConsegneDiOggi, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addGap(84))
		);
		
		
		setLayout(groupLayout);
		
	}
	
	/*
	 * Metodo caricaTabella: ci permette di caricare sulla tabella solo le persone che nella data odierna devono restituire un libro
	 */
	public static void caricaTabella() {														//firma del metodo
		String restitutire;																		//creazione della stringa
		Calendar cal = Calendar.getInstance();													//creazione del calendario

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");										//creo un formato di date
		restitutire = df.format(cal.getTime());													//e copio nella stringa la data di oggi formattata
		System.out.println(restitutire);														//la stampo a schermo
		
		String query = "select * from Libreria where Restituire = ?";							//creo la query da eseguire

		try {
			PreparedStatement pst = PannelloPrincipale.con.prepareStatement(query);				//preparo lo statement
			
			pst.setString(1, restitutire);														//imposto che il primo valore sia appunto la data
			ResultSet rs = pst.executeQuery();													//eseguo la query
			
			table.setModel(DbUtils.resultSetToTableModel(rs));									//la carico sulla tabella
		} catch (SQLException e) {													
			e.printStackTrace();																//printo lo stack dell'eccezione se presente
		}
	}
}
