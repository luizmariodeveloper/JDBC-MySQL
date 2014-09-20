package br.edu.LuizMario.jdbc.Excption;

public class LoginException extends Exception {

	/*Class Serializada Precisa do SerialVersionID*/
	private static final long serialVersionUID = 1L;

	public LoginException (String msg, Exception exercao)
		{super (msg, exercao);}
	
	public LoginException (String msg)
		{super (msg);}

}
