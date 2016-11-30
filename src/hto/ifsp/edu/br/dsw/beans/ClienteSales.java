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
public class ClienteSales {

	private String data;
	private String ultimos_produtos;
	private String valor;
	
	@ManagedProperty(value = "#{cliente}")
	private Cliente cliente;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getUltimos_produtos() {
		return ultimos_produtos;
	}
	public void setUltimos_produtos(String ultimos_produtos) {
		this.ultimos_produtos = ultimos_produtos;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public void save() {
		int clienteId = cliente.save();
		boolean success = false;
		String errorMsg = "";
		if (clienteId != -1) {
			java.sql.Connection con = new ConnectionFactory().getConnection();
			java.sql.PreparedStatement ps = null;
			try {
				ps = con.prepareStatement("insert into cliente_comercial values (?,?,?,?)");
				ps.setInt(1, clienteId);
				ps.setString(2, ultimos_produtos);
				ps.setString(3, valor);
				ps.setString(4, data);
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
