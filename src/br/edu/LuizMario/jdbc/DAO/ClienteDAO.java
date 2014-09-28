package br.edu.LuizMario.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.LuizMario.jdbc.ConexaoUtil;
import br.edu.LuizMario.jdbc.DTO.ClienteDTO;
import br.edu.LuizMario.jdbc.Excption.PersitenciaExecption;

public class ClienteDAO implements GernericDAO<ClienteDTO> {
	
	private ClienteDTO clienteDTO;

	public void inserir(ClienteDTO clienteDTO) throws PersitenciaExecption {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String SQL = " INSERT INTO cliente (nomeCliente,CPF,Endereco, Observacao) " + 
						 " VALUES (?,?,?,?) ";	
			PreparedStatement statment = connection.prepareStatement(SQL);
			
			statment.setString(1, clienteDTO.getNomeCliente());
			statment.setString(2, clienteDTO.getCPF());
			statment.setString(3, clienteDTO.getEndereco());
			statment.setString(4, clienteDTO.getObservacao());
			
			statment.execute();
			connection.close();
	} catch (Exception e) {
		e.printStackTrace();
		throw new PersitenciaExecption(e.getMessage(), e);}
	}

	public void atualizar(ClienteDTO clienteDTO, Integer idPessoa) throws PersitenciaExecption {
		
		try {
			
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String SQL = " UPDATE cliente "+
						 " SET nomeCliente = ?,"+
						 "	   CPF         = ?,"+
						 "     Endereco    = ?,"+
						 "     Observacao  = ?"+
						 " WHERE IDCliente = ?";
			
			PreparedStatement statment = connection.prepareStatement(SQL);
			
			statment.setString(1, clienteDTO.getNomeCliente());
			statment.setString(2, clienteDTO.getCPF());
			statment.setString(3, clienteDTO.getEndereco());
			statment.setString(4, clienteDTO.getObservacao());
			statment.setInt(5, idPessoa);
			
			statment.execute();
			connection.close();		
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersitenciaExecption(e.getMessage(), e);
		} 
	}

	public void delete(Integer idPessoa) throws PersitenciaExecption {
		
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();			
			String SQL = " DELETE FROM CLIENTE "+
						 " WHERE IDCliente = ? ";
			PreparedStatement statment = connection.prepareStatement(SQL);
			statment.setInt(1, idPessoa);
			statment.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersitenciaExecption(e.getMessage(), e);
		} 
	}


	public List<ClienteDTO> listarTodos() throws PersitenciaExecption {
	
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			List<ClienteDTO> aClienteDTO = new ArrayList<ClienteDTO>();
			String sql;
			sql = "SELECT * FROM CLIENTE";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) { 
				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO.setIDCliente(resultSet.getInt("IDCliente"));
				clienteDTO.setNomeCliente(resultSet.getString("nomeCliente"));
				clienteDTO.setEndereco(resultSet.getString("endereco"));
				clienteDTO.setObservacao(resultSet.getString("observacao"));
				clienteDTO.setCPF(resultSet.getString("CPF"));
				aClienteDTO.add(clienteDTO);}
			connection.close();
			return aClienteDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersitenciaExecption(e.getMessage(), e);}
	}

	
	
	public List<ClienteDTO> buscarPor(String nome) throws PersitenciaExecption{
		
		List<ClienteDTO> retorno = new ArrayList<ClienteDTO>();
			try {
				Connection connection = ConexaoUtil.getInstance().getConnection();
				
				String SQL = " SELECT * FROM CLIENTE";
				
				@SuppressWarnings("unused")
				PreparedStatement statement = connection.prepareStatement(SQL);
				
				ResultSet resultSet = null;
				
				resultSet = montarClausuraWhere(nome, connection, SQL,resultSet);
				
				trazerDoBanco(retorno, connection, resultSet);
				}catch (Exception e) {
				e.printStackTrace();
				throw new PersitenciaExecption(e.getMessage(), e);}
		return retorno;

	}

	private void trazerDoBanco(List<ClienteDTO> retorno, Connection connection,ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIDCliente  (resultSet.getInt    ("IDCliente") );
			clienteDTO.setNomeCliente(resultSet.getString("nomeCliente"));
			clienteDTO.setEndereco   (resultSet.getString("endereco")   );
			clienteDTO.setObservacao (resultSet.getString("observacao") );
			clienteDTO.setCPF        (resultSet.getString("CPF")        );
			retorno.add(clienteDTO);
			connection.close();}
	}

	private ResultSet montarClausuraWhere(String nome, Connection connection, String SQL, ResultSet resultSet) throws SQLException {
		PreparedStatement statement;
		if (nome != null && !(nome.equals(""))){
			SQL += " where nomeCliente = ? ";
			statement = connection.prepareStatement(SQL);
			statement.setString(1,nome);
			resultSet = statement.executeQuery();}
		return resultSet;
	}

	public ClienteDTO buscarPor(Integer id) throws PersitenciaExecption {
		clienteDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String SQL = " SELECT * FROM CLIENTE "
					   + " WHERE IDCliente = ? ";
			
			PreparedStatement statment = connection.prepareStatement(SQL);
			
			statment.setInt(1, id);
			
			ResultSet resultSet = statment.executeQuery();
		
			if (resultSet.next()) {
				clienteDTO = new ClienteDTO();
				clienteDTO.setIDCliente  (resultSet.getInt    ("IDCliente") );
				clienteDTO.setNomeCliente(resultSet.getString("nomeCliente"));
				clienteDTO.setEndereco   (resultSet.getString("endereco")   );
				clienteDTO.setObservacao (resultSet.getString("observacao") );
				clienteDTO.setCPF        (resultSet.getString("CPF")        );
				connection.close();}
			
		} catch (Exception e) {
			e.getMessage();
			throw new PersitenciaExecption(e.getMessage(), e); 
		}
		
		return clienteDTO;
		
	}
}
