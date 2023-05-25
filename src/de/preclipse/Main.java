package de.preclipse;


import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import de.preclipse.bo.*;

import gui.JanelaPrincipal;

import java.io.IOException;


public class Main {

	public final static String DATA_FOLDER = "data";

	public Main() {
		super();
	}

	public static void main(String[] ignored) throws Exception {
		final Prevayler prevayler = PrevaylerFactory.createPrevayler(
				new PrevalentSystem(), DATA_FOLDER);
		Thread snapShotThread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(50000);//500
						prevayler.takeSnapshot();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		snapShotThread.start();
		//solveFile(prevayler);
		new JanelaPrincipal(prevayler, "Timetabler - Programador de Hor�rios Escolares");
	}

}