package br.edu.LuizMario.jdbc.Excption;

public class PersitenciaExecption extends Exception {

	/*Class Serializada Precisa do SerialVersionID*/
	private static final long serialVersionUID = -2151273107901458860L;

	public PersitenciaExecption (String msg, Exception exercao)
		{super (msg, exercao);}
	
	public PersitenciaExecption (String msg)
		{super (msg);}
}
