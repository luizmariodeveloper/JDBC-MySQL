package br.edu.LuizMario.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.edu.LuizMario.jdbc.ConexaoUtil;
import br.edu.LuizMario.jdbc.DTO.LoginDTO;
import br.edu.LuizMario.jdbc.Excption.PersitenciaExecption;

public class LoginDAO implements GernericDAO<LoginDTO> {

	public boolean logar(LoginDTO loginDTO) throws PersitenciaExecption{
		boolean result = false;
		
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String SQL = "SELECT * FROM LOGIN WHERE Usuario = ? AND Senha = ? ";
			
			PreparedStatement statement = connection.prepareStatement(SQL);
			
			statement.setString(1, loginDTO.getLogin());
			statement.setString(2, loginDTO.getSenha());	
		
			ResultSet resultSet = statement.executeQuery();
			result = resultSet.next();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersitenciaExecption(e.getMessage(), e);}
	return result;
	}
	
	public void inserir(LoginDTO Obj) throws PersitenciaExecption {

	}

	public void atualizar(LoginDTO obj, Integer idPessoa) throws PersitenciaExecption {
	}

	public void delete(Integer id) throws PersitenciaExecption {

	}

	public List<LoginDTO> listarTodos() throws PersitenciaExecption {
		return null;
	}

	public List<LoginDTO> buscarPor(Integer id) throws PersitenciaExecption {
		return null;
	}
	
	public List<LoginDTO> buscarPor(String nome) throws PersitenciaExecption {
		return null;
	}

}
