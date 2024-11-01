package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Administrador;
import Negocio.Empresa;

public class DaoEmpresa implements DaoBasico {

	public DaoEmpresa() {
		String inst = "CREATE TABLE IF NOT EXISTS Empresa"
				+ "(CodEmp INT NOT NULL"
				+ ", CodAdm INT NOT NULL" 
				+ ", NomeEmp VARCHAR(45) NOT NULL"
				+ ", RendaFixa FLOAT NOT NULL"
				+ ", FundoImobiliario FLOAT NOT NULL"
				+ ", Acao FLOAT NOT NULL"
				+ ", PRIMARY KEY (CodEmp)"
				+ ", CONSTRAINT CodAdm FOREIGN KEY(CodAdm) REFERENCES Administrador(CodAdm)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";


		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.execute();
			}
			DaoConexao.getInstancia().setCon(con);

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean incluir(Object o) {

		boolean result = true;
		Empresa emp = (Empresa) o;
		String inst = "Insert Into Empresa";
		inst += "(CodEmp, CodAdm, NomeEmp, RendaFixa, FundoImobiliario, Acao) ";
		inst += "values(?, ?, ?, ?, ?, ?)";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setInt(1, emp.getCodEmp());
				pS.setInt(2, emp.getAdministrador().getCodAdm());
				pS.setString(3, emp.getEmpresa());
				pS.setFloat(4, emp.getRendafixa());
				pS.setFloat(5, emp.getFundoimobiliario());
				pS.setFloat(6, emp.getAcao());
				pS.execute();
			}

			DaoConexao.getInstancia().setCon(con);
		} catch (SQLException e) {

			result = false;
			throw new RuntimeException(e.getMessage());
		}
		return (result);

	}

	@Override
	public boolean alterar(Object o) {
	    boolean result = true;
	    Empresa emp = (Empresa) o;
	    String inst = "Update Empresa set CodAdm = ?, NomeEmp = ?, RendaFixa = ?, FundoImobiliario = ?, Acao = ? where CodEmp = ?";

	    try {
	        Connection con = DaoConexao.getInstancia().getCon();
	        try (PreparedStatement pS = con.prepareStatement(inst)) {
	            pS.setInt(1, emp.getAdministrador().getCodAdm());
	            pS.setString(2, emp.getEmpresa());
	            pS.setFloat(3, emp.getRendafixa());
	            pS.setFloat(4, emp.getFundoimobiliario());
	            pS.setFloat(5, emp.getAcao());
	            pS.setInt(6, emp.getCodEmp());
	            pS.execute();
	        }
	        DaoConexao.getInstancia().setCon(con);
	    } catch (SQLException e) {
	        result = false;
	        throw new RuntimeException(e.getMessage());
	    }
	    return (result);
	}

	@Override
	public boolean excluir(Object o) {

		boolean result = true;
		Empresa emp = (Empresa) o;
		String inst = "Delete From Empresa where CodEmp = ?";
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, emp.getCodEmp());
				pS.execute();
			}
			DaoConexao.getInstancia().setCon(con);
		} catch (SQLException e) {
			result = false;
			throw new RuntimeException(e.getMessage());
		}
		return (result);
	}

	@Override
	public Object busca(int numero, int nada) {
		String inst = "Select * from Empresa where CodEmp = ?";
		Empresa emp = null;
		ResultSet rS;
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, numero);
				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(con);
				if (rS.next()) {
					emp = new Empresa();
					DaoAdministrador dA = new DaoAdministrador();
					emp.setCodEmp(rS.getInt("CodEmp"));
					Object o = dA.busca(rS.getInt("CodAdm"), 0);
					Administrador a = (Administrador) o;
					emp.setAdministrador(a);
					emp.setEmpresa(rS.getString("NomeEmp"));
					emp.setRendafixa(rS.getFloat("RendaFixa"));
		            emp.setFundoimobiliario(rS.getFloat("FundoImobiliario"));
		            emp.setAcao(rS.getFloat("Acao"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return (emp);
	}

	@Override
	public List<Object> carrega() {
		String inst = "Select * From Empresa order by NomeEmp";
		List<Object> lista = new ArrayList<>();
		ResultSet rS;
		Object o;

		try {
			try (PreparedStatement pS = DaoConexao.getInstancia().getCon()
					.prepareStatement(inst)) {
				rS = pS.executeQuery(inst);
				DaoConexao.getInstancia().setCon(
						DaoConexao.getInstancia().getCon());
				if (rS != null)
					while (rS.next()) {
						o = busca(rS.getInt("CodEmp"), 0);
						lista.add(o);
					}
				pS.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return (lista);
	}
}