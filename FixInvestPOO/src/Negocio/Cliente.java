package Negocio;

public class Cliente {
	
	private int codCliente;
	private String nomeC;
	private String dataNas;
	private String cpf;
    private float salario;
    private float aporteIni;
    private float dispInv;
    private float ganhoEsp;
   
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public String getNomeC() {
		return nomeC;
	}
	public void setNomeC(String nomeC) {
		this.nomeC = nomeC;
	}
	public String getDataNas() {
		return dataNas;
	}
	public void setDataNas(String dataNas) {
		this.dataNas = dataNas;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public float getAporteIni() {
		return aporteIni;
	}
	public void setAporteIni(float aporteIni) {
		this.aporteIni = aporteIni;
	}
	public float getDispInv() {
		return dispInv;
	}
	public void setDispInv(float dispInv) {
		this.dispInv = dispInv;
	}
	public float getGanhoEsp() {
		return ganhoEsp;
	}
	public void setGanhoEsp(float ganhoEsp) {
		this.ganhoEsp = ganhoEsp;
	}
	

}
