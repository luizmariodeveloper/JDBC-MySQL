package br.edu.LuizMario.jdbc.Excption;

public class ValidacaoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidacaoException (String msg, Exception exercao)
	{super (msg, exercao);}

	public ValidacaoException (String msg)
	{super (msg);}


}
