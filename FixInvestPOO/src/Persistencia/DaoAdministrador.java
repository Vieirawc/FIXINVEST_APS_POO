package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Administrador;

public class DaoAdministrador implements DaoBasico {

	public DaoAdministrador() {
		String inst = "CREATE TABLE IF NOT EXISTS Administrador"
				+ "(CodAdm INT NOT NULL" + ", NomeA VARCHAR(45) NOT NULL"  + ", Cpf VARCHAR(14) NOT NULL"  + ", Tel VARCHAR(15) NOT NULL"  + ", Cep VARCHAR(9) NOT NULL"
				+ ", PRIMARY KEY (CodAdm)"
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
	public boolean incluir(Object b) {

		boolean result = true;
		Administrador bA = (Administrador) b;
		String inst = "Insert Into Administrador";
		inst += "(CodAdm, NomeA, Cpf, Tel, Cep) ";
		inst += "values(?, ?, ?, ?, ?)";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setInt(1, bA.getCodAdm());
				pS.setString(2, bA.getNomeA());
				pS.setString(3, bA.getCpf());
				pS.setString(4, bA.getTel());
				pS.setString(5, bA.getCep());
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
	public boolean alterar(Object b) {

		boolean result = true;
		Administrador bA = (Administrador) b;
		String inst = "Update Administrador set NomeA = ?, ";
		inst += "Cpf = ?, Tel = ?, Cep = ? ";
		inst += "where CodAdm = ?";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setString(1, bA.getNomeA());
				pS.setString(2, bA.getCpf());
				pS.setString(3, bA.getTel());
				pS.setString(4, bA.getCep());
				pS.setInt(5, bA.getCodAdm());
				
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
	public boolean excluir(Object b) {

		boolean result = true;
		Administrador bA = (Administrador) b;
		String inst = "Delete From Administrador where CodAdm = ?";
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, bA.getCodAdm());
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
	public Object busca(int codigo, int nada) {
		String inst = "Select * from Administrador where CodAdm = ?";
		Administrador bA = null;
		ResultSet rS;
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, codigo);
				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(con);
				if (rS.next()) {
					bA = new Administrador();
					bA.setCodAdm(rS.getInt("CodAdm"));
					bA.setNomeA(rS.getString("NomeA"));
					bA.setCpf(rS.getString("Cpf"));
					bA.setTel(rS.getString("Tel"));
					bA.setCep(rS.getString("Cep"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return (bA);
	}

	@Override
	public List<Object> carrega() {
		String inst = "Select * From Administrador order by NomeA";
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
						o = busca(rS.getInt("CodAdm"), 0);
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