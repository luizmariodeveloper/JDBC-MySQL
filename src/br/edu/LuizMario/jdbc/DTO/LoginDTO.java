package br.edu.LuizMario.jdbc.DTO;

public class LoginDTO {
	
	private int Id;
	
	private String login;
	
	private String senha;
	

	public int getId() {
		return this.Id;}

	public void setId(int id) {
		this.Id = id;}

	public String getLogin() {
		return login;}

	public void setLogin(String login) {
		this.login = login;}

	public String getSenha() {
		return senha;}

	public void setSenha(String senha) {
		this.senha = senha;}

}
