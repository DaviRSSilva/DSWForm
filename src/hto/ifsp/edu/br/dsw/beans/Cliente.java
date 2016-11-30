package hto.ifsp.edu.br.dsw.beans;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.mysql.jdbc.ResultSet;

import hto.ifsp.edu.br.dsw.ConnectionFactory;

@ManagedBean
@RequestScoped
public class Cliente {

	protected long id;
	protected String nome;
	protected String cpf;
	protected String telefone;
	protected String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int save() {
		java.sql.Connection con = new ConnectionFactory().getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into cliente(cpf,email,telefone,nome) values (?,?,?,?)");
			ps.setString(1, cpf);
			ps.setString(2, email);
			ps.setString(3, telefone);
			ps.setString(4, nome);
			ps.execute();	
			
			ps = con.prepareStatement("select id from cliente where cpf = ?");
			ps.setString(1, cpf);
			java.sql.ResultSet rs = ps.executeQuery();
			rs.first();
			return rs.getInt("id");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
