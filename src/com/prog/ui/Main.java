package com.prog.ui;

import java.awt.EventQueue;
import javax.swing.UIManager;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {													//main thread
		try {																					//try
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());				//metodo che permette di modificare la grafica di base e utilizzare quella del sistema
		} catch (Exception e) {																	//catturo un eccezione in caso ci fosse
			e.printStackTrace();      															//printo lo stack delle eccezione
		}
		EventQueue.invokeLater(new Runnable() {													//eseguo il main creando un nuovo oggetto runnable
			public void run() {																	//dichiaro il metodo run
				try {																			//try
					Finestra window = new Finestra("Libreria");									//creo un nuova finetra chiamata libreria
					window.setVisible();														//e la rendo visible
				} catch (Exception e) {															//catturo una possible eccezione
					e.printStackTrace();														//printo lo stack delle eccezione	
				}
			}
		});
	}

}
