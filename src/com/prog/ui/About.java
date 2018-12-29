package com.prog.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class About extends JPanel implements ActionListener,MouseListener{					//Classe about che estende JPanel e che implementa degli ascoltatori

	private static final long serialVersionUID = 1L;										//valore che permette di render il panel unico
	private int green;																		//variabile int che conterra il valore del verde
	private int red;																		//variabile int che conterra il valore del rosso
	private int blu;																		//variabile int che conterra il valore del blu
	private Random gen = new Random();														//variabile random che ci permetterà il cambio del background a random
	
	/*
	 * Costruttore della classe il quale crea il panel about contenente 
	 * 		le informazioni di chi ha eseguito questo progetto
	 */
	
	public About() {			
		setLayout(null);																	//rimuovo il layout
		addMouseListener(this);																//imposto come ascoltatori del pannello quelli dichiarati nella classe
		
		JButton next = new JButton("Indietro");												//Creo un pulsante che ci permettera di tornare indietro
		next.addActionListener(this);														//aggiungo gli action listener dichiarati nella stessa classe 
		next.setToolTipText("Indietro");													//imposto come testo di hover "indietro"
		next.setBounds(280, 210, 130, 25);													//modifico le dimensione del pulsante
		add(next);																			//lo aggiungo al panel
		
	}

	
	/*
	 * Metodo che permette di disegnare sul panel
	 */
	
	public void paintComponent(Graphics g) {												//firma del metodo
		super.paintComponent(g);															//richiamo il metodo della classe JPanel
			disegnaSchermata(g);															//richiamo il metodo disegna schermata
	}

	
	
	/*
	 * Metodo disegna schermata: Ci permette di disegnare sul panel
	 * 							 i nomi delle persone utilizzando g.drawString();
	 */
	
	public void disegnaSchermata(Graphics g) {												//firma del metodo 
		g.setFont(new Font("Tahoma", Font.BOLD, 13));										//cambio del font
		g.drawString("Programma creato da: ", 100, 100);									//disegno delle stringhe a coordinate diverse
		g.drawString("Leonardo Drici", 170, 100 + 20);
		g.drawString("Steve Franco", 170, 100 + 40);
		g.drawString("Stefani Filippo", 170, 100 + 60);
		g.drawString("Romanini Daniele", 170, 100 + 80);
	}


	@Override
	/*
	 * Metodo action performed: ci permette di tornare alla finestra principale 
	 * 	
	 */
	public void actionPerformed(ActionEvent e) {                                         	//firma del metodo  
		Finestra.cambia(1);																	//richiamo del metodo che permette di cambiare il pannello della finestra
	}

	/*
	 * Metodo mouseClicked: ci permette di cambiare il background del JPanel
	 */
	public void mouseClicked(MouseEvent e) {												//firma del metodo
		green = gen.nextInt(256);															//creo un random per il colore verde
		red = gen.nextInt(256);																//creo un random per il colore rosso
		blu = gen.nextInt(256);																//creo un random per il colore blu
		setBackground(new Color(red,green,blu));											//cambio il background del Jpanel con i tre valori RGB 
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
