package br.edu.LuizMario.jdbc.DAO;

import java.util.List;

import br.edu.LuizMario.jdbc.Excption.PersitenciaExecption;

public interface GernericDAO <T> {

	void inserir (T Obj) throws PersitenciaExecption;
	
	void atualizar (T obj, Integer idPessoa)throws PersitenciaExecption;
	
	void delete (Integer id)throws PersitenciaExecption;
	
	List<T> listarTodos ()throws PersitenciaExecption;
	
	List<T> buscarPor (Integer id)throws PersitenciaExecption;
	
    List<T> buscarPor (String nome)throws PersitenciaExecption;
	
}
