/**
 * @author Luiz Mário
 * 
 * Class de Regra de Negócio
 * 
 * */

package br.edu.LuizMario.jdbc.BO;

import br.edu.LuizMario.jdbc.DAO.LoginDAO;
import br.edu.LuizMario.jdbc.Excption.LoginException;
import br.edu.LuizMario.jdbc.DTO.LoginDTO;


public class LoginBO {

	public boolean logar(LoginDTO loginDTO) throws LoginException{
		boolean resultado = false;
		LoginDAO loginDAO = new LoginDAO ();
		
		try {
			if (loginDTO.getLogin() == null || "".equals(loginDTO.getLogin()) ) {
				throw new LoginException("Login Obrigatório!");
			} else if (loginDTO.getSenha() == null || "".equals(loginDTO.getSenha()) ) {
				throw new LoginException("Senha Obrigatória!");
			}else{
				resultado = loginDAO.logar(loginDTO);	
			}
			
		} catch (Exception e) {
			throw new LoginException (e.getMessage());
		}
		return resultado;
	}
}
