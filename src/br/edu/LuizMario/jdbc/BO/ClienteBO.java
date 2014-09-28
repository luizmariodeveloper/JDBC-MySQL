package br.edu.LuizMario.jdbc.BO;

import java.util.List;

import br.edu.LuizMario.jdbc.DAO.ClienteDAO;
import br.edu.LuizMario.jdbc.DTO.ClienteDTO;
import br.edu.LuizMario.jdbc.Excption.ClienteException;
import br.edu.LuizMario.jdbc.Excption.PersitenciaExecption;
import br.edu.LuizMario.jdbc.Excption.ValidacaoException;

public class ClienteBO {

	public void Inserir (ClienteDTO clienteDTO) throws ClienteException{
		ClienteDAO clienteDAO = new ClienteDAO();
		String msg = null;
		try {
			if (clienteDTO.getNomeCliente() == null || "".equals(clienteDTO.getNomeCliente())){				
				msg = "Nome Obrigatório";
			} else if (clienteDTO.getCPF() == null || "".equals(clienteDTO.getCPF())){
				msg = "CPF Obrigatório";
				throw new ClienteException("Campo(s) Obrigatório(s) não preenchido(s)\n"+msg);
			}
			clienteDAO.inserir(clienteDTO);
		} catch (Exception e) {
			throw new ClienteException(e.getMessage(), e);}	
	}
	
	public String[][] listagem(List<Integer> idPessoas) throws PersitenciaExecption {
		String[][] listaRetorno = null;
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<ClienteDTO> lista = clienteDAO.listarTodos();
			listaRetorno = new String[lista.size()][7];
			
			preencherCliente(listaRetorno, lista, idPessoas);
		
		} catch (PersitenciaExecption e) {
			throw new PersitenciaExecption(e.getMessage(), e);}
		return listaRetorno;
	}
	
	public boolean validarCadastro (ClienteDTO clienteDTO) throws ValidacaoException{
		
		boolean retorno = true; 
		
		String msg = null;
		String nomeCliente = clienteDTO.getNomeCliente();
		String cpf         = clienteDTO.getCPF();
		String endereco    = clienteDTO.getEndereco();
	
		retorno = validarCampos(clienteDTO, retorno, msg, nomeCliente, cpf,endereco);
		
		return retorno;
	}

	private boolean validarCampos(ClienteDTO clienteDTO, boolean retorno, String msg, String nomeCliente, String cpf, String endereco) throws ValidacaoException {
		if (clienteDTO.getNomeCliente() == null || "".equals(clienteDTO.getNomeCliente())) {
			retorno = false;
			msg = ".Nome do Cliente\n";
		} else if (nomeCliente.length() > 60 ){
				retorno = false;
				msg = ".Nome do Cliente deve ter até 60 letras\n";
			}
		if (clienteDTO.getCPF() == null || "".equals(clienteDTO.getCPF())) {
			msg = msg + ".CPF\n";
		} else if (cpf.length() != 11) {
			retorno = false;
			msg = msg + ".CPF deve ter 11 digito\n";			
		} else {
			boolean teste = false;
			char[] digitos = cpf.toCharArray();
			
			for (char digito : digitos) {
				if (!(Character.isDigit(digito))) {
					teste = true;}
			}
			if (teste) {
			msg = msg + ".CPF só pode ter digitos\n"; }
		}
		if (clienteDTO.getEndereco() == null || "".equals(clienteDTO.getEndereco())) {
			msg = msg + ".Endereço";} else if (endereco.length() > 100) {
				retorno = false;
				msg = msg + ".Endereço do Cliente deve ter até 100 letras\n";
			}
		if  (msg != null )  {
			retorno = false;
			throw new ValidacaoException("Campo(s) Obrigatório(s) não preenchido(s)\n"+ msg);
		}
		return retorno;
	}
	
	public String[][] buscarPor(String nome) throws PersitenciaExecption {
		String[][] listaRetorno = null; 
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<ClienteDTO> lista = clienteDAO.buscarPor(nome);
			listaRetorno = new String[lista.size()][5];
			preencherCliente(listaRetorno, lista, null);
		
		} catch (PersitenciaExecption e) {
			throw new PersitenciaExecption(e.getMessage(), e);}
		return listaRetorno;
	}

	private void preencherCliente(String[][] listaRetorno, List<ClienteDTO> lista, List<Integer> idPessoas) {
		
		for (int i= 0; i < lista.size() ; i++) {
			ClienteDTO clienteDTO = lista.get(i);
			listaRetorno[i][0] = clienteDTO.getIDCliente().toString();
			idPessoas.add(clienteDTO.getIDCliente());
			listaRetorno[i][1] = clienteDTO.getNomeCliente();
			listaRetorno[i][2] = clienteDTO.getCPF();
			listaRetorno[i][3] = clienteDTO.getEndereco();
			listaRetorno[i][4] = clienteDTO.getObservacao();
			listaRetorno[i][5] = "Editar";
			listaRetorno[i][6] = "Deletar";
			}
	}
	
	public void removerCliente(int idPessoa) throws PersitenciaExecption{
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.delete(idPessoa);
		}catch (PersitenciaExecption e ){
			e.printStackTrace();
		}
	}
	
	public ClienteDTO buscarEdicao (int idCliente) throws ClienteException{
		ClienteDTO clienteDTO = null;
		
		try {	
			ClienteDAO clienteDAO = new ClienteDAO ();
			clienteDTO = clienteDAO.buscarPor(idCliente);
		} catch (PersitenciaExecption e) {
			e.printStackTrace();
			throw new ClienteException(e.getMessage(), e);
		}
	
		return clienteDTO;
	}
	
	public void alterarCliente (ClienteDTO clienteDTO, int idPessoa) throws ClienteException{
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.atualizar(clienteDTO, idPessoa);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClienteException(e.getMessage(), e);
		}
	}
}	