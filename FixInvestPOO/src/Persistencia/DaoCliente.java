package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.Cliente;

public class DaoCliente implements DaoBasico {

	public DaoCliente() {
		String inst = "CREATE TABLE IF NOT EXISTS Cliente"
                + "(CodCliente INT NOT NULL" 
                + ", NomeC VARCHAR(45) NOT NULL"  
                + ", DataNas VARCHAR(10) NOT NULL"  
                + ", Cpf VARCHAR(14) NOT NULL" 
                + ", Salario FLOAT NOT NULL"  
                + ", AporteIni FLOAT NOT NULL"  
                + ", DispInv FLOAT NOT NULL"  
                + ", GanhoEsp FLOAT NOT NULL"  
                + ", PRIMARY KEY (CodCliente)"
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
	    Cliente bA = (Cliente) b;
	    String inst = "Insert Into Cliente";
	    inst += "(CodCliente, NomeC, DataNas, Cpf, Salario, AporteIni, DispInv, GanhoEsp) ";
	    inst += "values(?, ?, ?, ?, ?, ?, ?, ?)";

	    try {

	        Connection con = DaoConexao.getInstancia().getCon();
	        try (PreparedStatement pS = con.prepareStatement(inst)) {

	            pS.setInt(1, bA.getCodCliente());
	            pS.setString(2, bA.getNomeC());
	            pS.setString(3, bA.getDataNas());
	            pS.setString(4, bA.getCpf());
	            pS.setFloat(5, bA.getSalario());
	            pS.setFloat(6, bA.getAporteIni());
	            pS.setFloat(7, bA.getDispInv());
	            pS.setFloat(8, bA.getGanhoEsp());

	            pS.execute();
	        }

	        DaoConexao.getInstancia().setCon(con);
	    } catch (SQLException e) {

	        result = false;
	        throw new RuntimeException(e.getMessage());
	    }
	    return result;
	}


	@Override
	public boolean alterar(Object b) {

	    boolean result = true;
	    Cliente bA = (Cliente) b;
	    String inst = "Update Cliente set NomeC = ?, DataNas = ?, Cpf = ?, Salario = ?, AporteIni = ?, DispInv = ?, GanhoEsp = ? ";
	    inst += "where CodCliente = ?";

	    try {

	        Connection con = DaoConexao.getInstancia().getCon();
	        try (PreparedStatement pS = con.prepareStatement(inst)) {

	            pS.setString(1, bA.getNomeC());
	            pS.setString(2, bA.getDataNas());
	            pS.setString(3, bA.getCpf());
	            pS.setFloat(4, bA.getSalario());
	            pS.setFloat(5, bA.getAporteIni());
	            pS.setFloat(6, bA.getDispInv());
	            pS.setFloat(7, bA.getGanhoEsp());
	            pS.setInt(8, bA.getCodCliente());

	            pS.execute();
	        }

	        DaoConexao.getInstancia().setCon(con);
	    } catch (SQLException e) {

	        result = false;
	        throw new RuntimeException(e.getMessage());
	    }
	    return result;
	}

	@Override
	public boolean excluir(Object b) {

		boolean result = true;
		Cliente bA = (Cliente) b;
		String inst = "Delete From Cliente where CodCliente = ?";
		try {
			Connection con = DaoConexao.getInstancia().getCon();
			try (PreparedStatement pS = con.prepareStatement(inst)) {
				pS.setInt(1, bA.getCodCliente());
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
	    String inst = "Select * from Cliente where CodCliente = ?";
	    Cliente bA = null;
	    ResultSet rS;
	    try {
	        Connection con = DaoConexao.getInstancia().getCon();
	        try (PreparedStatement pS = con.prepareStatement(inst)) {
	            pS.setInt(1, codigo);
	            rS = pS.executeQuery();
	            DaoConexao.getInstancia().setCon(con);
	            if (rS.next()) {
	                bA = new Cliente();
	                bA.setCodCliente(rS.getInt("CodCliente"));
	                bA.setNomeC(rS.getString("NomeC"));
	                bA.setDataNas(rS.getString("DataNas"));
	                bA.setCpf(rS.getString("Cpf"));
	                bA.setSalario(rS.getFloat("Salario"));
	                bA.setAporteIni(rS.getFloat("AporteIni"));
	                bA.setDispInv(rS.getFloat("DispInv"));
	                bA.setGanhoEsp(rS.getFloat("GanhoEsp"));
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e.getMessage());
	    }
	    return bA;
	}

	@Override
	public List<Object> carrega() {
		String inst = "Select * From Cliente order by NomeC";
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