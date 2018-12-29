package com.prog.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class DatabaseIscritti extends JPanel {

	private static final long serialVersionUID = -1L;											//variabile per rendere unico il panel
	private static JTable table;																//tabella di dati
	private JTextField txtFiltra;																//JTextField che filtra gli elementi della tabella
	private String immIndietro = "\\src\\immagini\\imageIndietro.png";									//path dell'immagine indietro

	/**
	 * Costruttore del pannello
	 */
	public DatabaseIscritti() {																	//firma del metodo
		setBounds(0, 0, Finestra.base, Finestra.altezza);										//modifico le dimensione del pannello

		inizialize();																			//richiamo il metodo che inizializza il pannello	

	}

	/*
	 * Metodo inizialize: ci permette di inizalizzare il JPanel con tutti i componenti
	 */
	private void inizialize() {																	//firma del metodo		
		JScrollPane scrollPane = new JScrollPane();												//creazione dello scroll pane

		table = new JTable();																	//creazione della tabella
		scrollPane.setViewportView(table);														//aggiunta della tabella allo scroll pane
		
		
		/*
		 * Pulsante che fa il refresh degli elementi della tabella
		 */
		JButton btnRefreshDatabase = new JButton("Refresh Database");							//creazione del pulsante refresh
		btnRefreshDatabase.addActionListener(new ActionListener() {								//aggiunta dell'action listener
			public void actionPerformed(ActionEvent evt) {
				refresh();																		//richiama il metodo refresh
			}
		});

		JLabel lblCearca = new JLabel("Cerca:");												//creazione della label

		/*
		 * Text field che ci permette di filtrare gli elementi della tabella
		 */
		txtFiltra = new JTextField();															//creazione della textfield per il filtraggio
		txtFiltra.addKeyListener(new KeyAdapter() {												//aggiunta dell'evento keyrelesed
			@Override
			public void keyReleased(KeyEvent arg0) {
				filtra(txtFiltra.getText());													//richima il metodo filtra
			}
		});
			
		txtFiltra.setToolTipText("Cerca Iscritti");												//imposto il testo di hover																
		
		
		/*
		 * Pulsante che cambia il database visibile 
		 */
		JButton btnCambia = new JButton("Cambia");												//creazione del pulsante cambia
		btnCambia.addActionListener(new ActionListener() {										//aggiunta dell'action listener
			public void actionPerformed(ActionEvent e) {
				Database.cambia(2);																//cambia il database visibile
				DatabaseLibri.refresh();														//e refresha la tabella
			}
		});
		btnCambia.setToolTipText("Cambia Database");											//modifica del testo di hover
		
		JLabel lblDatabaseIscritti = new JLabel("Database Iscritti");							//creazione della label
		lblDatabaseIscritti.setHorizontalAlignment(SwingConstants.CENTER);						//modifica della posizione del testo
		lblDatabaseIscritti.setFont(new Font("Tahoma", Font.BOLD, 14));							//modifica del font
			
		
		/*
		 * Pulsante per tornare al panel principale 
		 */
		JButton btnIndietro = new JButton("Indietro");											//creazione del pulsante indietro
		btnIndietro.setIcon(new ImageIcon(System.getProperty("user.dir") + immIndietro));				//aggiunta dell'icona
		btnIndietro.addActionListener(new ActionListener() {									//aggiunta dell'action listener
			public void actionPerformed(ActionEvent e) {
				Finestra.cambia(1);																//richiama il metodo per tornare al menù principale
			}
		});
		
		/*
		 * Layout del JPanel
		 */
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDatabaseIscritti, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCearca, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFiltra, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
							.addComponent(btnIndietro)
							.addGap(18)
							.addComponent(btnCambia)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRefreshDatabase, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
					.addGap(48))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatabaseIscritti, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnRefreshDatabase)
							.addComponent(btnCambia)
							.addComponent(btnIndietro, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblCearca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtFiltra, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		setLayout(groupLayout);

	}

	
	/*
	 * Metodo filtra: ci permette di filtrare gli elementi nella tabella
	 */
	public void filtra(String text) {
		//creo un nuovo modello di ordinazione per la tabella
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
		
		sorter.setRowFilter(RowFilter.regexFilter(text));								//cerco imposto l'elemento come linea della tabella

		table.setRowSorter(sorter);														//aggiungo il modello alla tabella

	}
	
	/*
	 * Metodo refresh: ci permette di fare il refresh degli elementi della tabella
	 */
	public static void refresh() {
		String query = "select * from Libreria";										//creo la query da eseguire

		try {
			PreparedStatement pst = PannelloPrincipale.con.prepareStatement(query);		//preparo la query
			ResultSet rs = pst.executeQuery();											//la eseguo
			table.setModel(DbUtils.resultSetToTableModel(rs));							//e carico i risultati sulla tabella
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
