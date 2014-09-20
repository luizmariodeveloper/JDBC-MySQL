package br.edu.LuizMario.jdbc.DTO;

public class ClienteDTO {

	private Integer IDCliente;	
	private String nomeCliente;	
	private String CPF;
	private String Endereco;	
	private String Observacao;
	
	
	public Integer getIDCliente() {
		return IDCliente;}

	public void setIDCliente(Integer iDCliente) {
		this.IDCliente = iDCliente;}

	public String getNomeCliente() {
		return this.nomeCliente;}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;}

	public String getCPF() {
		return this.CPF;}

	public void setCPF(String CPF) {
		this.CPF = CPF;}

	public String getEndereco() {
		return this.Endereco;}

	public void setEndereco(String endereco) {
		this.Endereco = endereco;}

	public String getObservacao() {
		return this.Observacao;}

	public void setObservacao(String observacao) {
		this.Observacao = observacao;}
}
