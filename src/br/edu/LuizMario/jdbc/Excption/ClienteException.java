package br.edu.LuizMario.jdbc.Excption;

public class ClienteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteException (String msg, Exception exercao)
	{super (msg, exercao);}

	public ClienteException (String msg)
	{super (msg);}


}
