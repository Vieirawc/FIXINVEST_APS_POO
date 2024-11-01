package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Negocio.Cliente;
import Negocio.Empresa;
import Negocio.Administrador;
import Negocio.Investimento;

public class DaoInvestimento implements DaoBasico {

	public DaoInvestimento() {
		
        String inst = "CREATE TABLE IF NOT EXISTS Investimento"
                + " (CodEmp INT NOT NULL"
                + ", CodCliente INT NOT NULL"
                + ", CodAdm INT NOT NULL"
                + ", TipoInvestimento VARCHAR(45) NOT NULL"
                + ", PRIMARY KEY (CodEmp, CodCliente)"
                + ", KEY CodEmp (CodEmp)"
                + ", KEY CodCliente (CodCliente)"
                + ", KEY CodAdm (CodAdm)" 
                + ", CONSTRAINT CodEmpE FOREIGN KEY (CodEmp) REFERENCES Empresa (CodEmp)"
                + ", CONSTRAINT CodClienteE FOREIGN KEY (CodCliente) REFERENCES Cliente (CodCliente)"
                + ", CONSTRAINT CodAdmE FOREIGN KEY (CodAdm) REFERENCES Administrador (CodAdm)"
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
		Investimento cO = (Investimento) o;
		String inst = "Insert Into Investimento";
		inst += "(CodEmp, CodCliente, CodAdm, TipoInvestimento) ";
		inst += "values(?, ?, ?, ?)";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setInt(1, cO.getEmpresa().getCodEmp());
				pS.setInt(2, cO.getCliente().getCodCliente());
				pS.setInt(3, cO.getAdministrador().getCodAdm());
				pS.setString(4, cO.getTipoInvestimento());
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
		Investimento cO = (Investimento) o;
		String inst = "Update Investimento set CodAdm = ?, ";
		inst += "TipoInvestimento = ? ";
		inst += "where CodEmp = ? and CodCliente = ?";

		try {

			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {

				pS.setInt(1, cO.getAdministrador().getCodAdm());
				pS.setString(2, cO.getTipoInvestimento());
				pS.setInt(3, cO.getEmpresa().getCodEmp());
				pS.setInt(4, cO.getCliente().getCodCliente());
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
		Investimento cO = (Investimento) o;
		
		String inst = "Delete From Investimento where CodEmp = ? and CodCliente = ?";
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, cO.getEmpresa().getCodEmp());
				pS.setInt(2, cO.getCliente().getCodCliente());
				
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
	public Object busca(int CodCliente, int nada) {
		String inst = "Select * from Investimento where CodCliente = ? ";
		Investimento cO = null;
		ResultSet rS;
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, CodCliente);

				rS = pS.executeQuery();
				DaoConexao.getInstancia().setCon(con);
				if (rS.next()) {
					cO = new Investimento();
					Object o; 
					DaoAdministrador dF = new DaoAdministrador();
					DaoCliente dC = new DaoCliente();
					DaoEmpresa dL = new DaoEmpresa();
					
					o = dL.busca(rS.getInt("CodEmp"), 0);
					Empresa l = (Empresa) o;
					cO.setEmpresa(l);
					
					o = dC.busca(rS.getInt("CodCliente"), 0);
					Cliente c = (Cliente) o;
					cO.setCliente(c);
					
                    o = dF.busca(rS.getInt("CodAdm"), 0);
					Administrador a = (Administrador) o;
					cO.setAdministrador(a);
					
					cO.setTipoInvestimento(rS.getString("TipoInvestimento"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return (cO);
	}

	@Override
	public List<Object> carrega() {
		String inst = "Select * From Investimento order by TipoInvestimento";
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
						o = busca(rS.getInt("CodCliente"), 0);
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