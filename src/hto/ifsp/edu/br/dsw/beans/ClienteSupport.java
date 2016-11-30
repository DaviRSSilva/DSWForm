package hto.ifsp.edu.br.dsw.beans;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import hto.ifsp.edu.br.dsw.ConnectionFactory;

@ManagedBean
@SessionScoped
public class ClienteSupport {
	
	
	private String lista_produtos;
	@ManagedProperty(value = "#{cliente}")
	private Cliente cliente;

	public String getLista_produtos() {
		return lista_produtos;
	}

	public void setLista_produtos(String lista_produtos) {
		this.lista_produtos = lista_produtos;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void save() {
		int clienteId = cliente.save();
		boolean success = false;
		String errorMsg = "";
		if (clienteId != -1) {
			java.sql.Connection con = new ConnectionFactory().getConnection();
			java.sql.PreparedStatement ps = null;
			try {
				ps = con.prepareStatement("insert into cliente_suporte values (?,?)");
				ps.setInt(1, clienteId);
				ps.setString(2, lista_produtos);
				success = ps.execute();

			} catch (SQLException e) {
				errorMsg = "Falha ao inserir dados no banco.";
				success = false;
			}
		}
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String url = ec.getRequestContextPath() + "/faces/hello.xhtml?success=" + success+"&msg="+errorMsg;
		try {
			ec.redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
