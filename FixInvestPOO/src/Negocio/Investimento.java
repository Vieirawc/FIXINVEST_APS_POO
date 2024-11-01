package Negocio;

import Util.Diversos;

public class Investimento{

    private Administrador administrador;
    private Cliente cliente;
    private Empresa empresa;
    private String TipoInvestimento;

	public String getTipoInvestimento() {
		return TipoInvestimento;
	}


	public void setTipoInvestimento(String tipoInvestimento) {
		TipoInvestimento = tipoInvestimento;
	}


	public Administrador getAdministrador() {
		return administrador;
	}


	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public String relatorio() {

		String resp = "";

		resp+= "Empresa: "+ empresa.getEmpresa();
		resp+=" | Administrador: " + administrador.getNomeA();
		resp+=" | Cliente: " + cliente.getNomeC();
		resp+="\nSalário: " + Diversos.doisDigitos(1).format(cliente.getSalario());
		resp+=" | Aporte Inicial: " + Diversos.doisDigitos(1).format(cliente.getAporteIni());
		resp+=" | Disponibilidade de Investimento: " + Diversos.doisDigitos(1).format(cliente.getDispInv());
		resp+="\nTipo Investimento: "+ TipoInvestimento;
	
		return resp;
	}
}
