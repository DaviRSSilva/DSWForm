package hto.ifsp.edu.br.dsw.beans;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import hto.ifsp.edu.br.dsw.ConnectionFactory;

@ManagedBean
@RequestScoped
public class ClienteFinances {

	@ManagedProperty(value = "#{cliente}")
	private Cliente cliente;
	private String fatura1;
	private String fatura2;
	private String fatura3;

	public String getFatura1() {
		return fatura1;
	}

	public void setFatura1(String fatura1) {
		this.fatura1 = fatura1;
	}

	public String getFatura2() {
		return fatura2;
	}

	public void setFatura2(String fatura2) {
		this.fatura2 = fatura2;
	}

	public String getFatura3() {
		return fatura3;
	}

	public void setFatura3(String fatura3) {
		this.fatura3 = fatura3;
	}

	public void save() {
		int clienteId = cliente.save();
		boolean success = false;
		String errorMsg = "";
		if (clienteId != -1) {
			java.sql.Connection con = new ConnectionFactory().getConnection();
			java.sql.PreparedStatement ps = null;
			try {
				ps = con.prepareStatement("insert into cliente_financeiro values (?,?,?,?)");
				ps.setInt(1, clienteId);
				ps.setString(2, fatura1);
				ps.setString(3, fatura2);
				ps.setString(4, fatura3);
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
