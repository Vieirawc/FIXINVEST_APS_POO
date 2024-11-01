package Negocio;

public class Empresa {
	
	private int codEmp;
	private Administrador administrador;
	private String empresa;
	private float rendafixa;
	private float fundoimobiliario;
	private float acao;
    
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	public int getCodEmp() {
		return codEmp;
	}
	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public float getRendafixa() {
		return rendafixa;
	}
	public void setRendafixa(float rendafixa) {
		this.rendafixa = rendafixa;
	}
	public float getFundoimobiliario() {
		return fundoimobiliario;
	}
	public void setFundoimobiliario(float fundoimobiliario) {
		this.fundoimobiliario = fundoimobiliario;
	}
	public float getAcao() {
		return acao;
	}
	public void setAcao(float acao) {
		this.acao = acao;
	}
    
}
