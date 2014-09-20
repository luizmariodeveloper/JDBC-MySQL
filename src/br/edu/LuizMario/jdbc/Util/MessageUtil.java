package br.edu.LuizMario.jdbc.Util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MessageUtil {
	
	public static void addMesg (Component component, String msg) {
		
		JOptionPane.showMessageDialog(component, msg);
		
	}

}