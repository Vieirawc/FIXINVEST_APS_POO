package Controle;

import java.util.List;
import Persistencia.DaoBasico;
import Persistencia.DaoCliente;
import Persistencia.DaoEmpresa;
import Persistencia.DaoInvestimento;
import Persistencia.DaoAdministrador;

/*import Persistencia.DaoEmprestimo;*/

public class ControleGeral implements ControleBasico {

	private DaoBasico dG;

	public ControleGeral(int op) {
			if (op == 1)
				this.dG = new DaoAdministrador();
			else if (op==2)
				this.dG = new DaoCliente();	
			else if (op==3)
				this.dG = new DaoEmpresa();
			else if(op==4)
				this.dG = new DaoInvestimento();
	}

	@Override
	public boolean setManipular(Object o, char tarefa) {
		boolean oK = false;
		if (dG instanceof DaoBasico)
			switch (tarefa) {
			case 'A':
				oK = (dG.alterar(o));
				break;
			case 'E':
				oK = (dG.excluir(o));
				break;
			case 'I':
				oK = (dG.incluir(o));
			}
		return (oK);
	}

	@Override
	public Object getBusca(int CodEmp, int CodCliente) {
		Object o = null;
		if (dG instanceof DaoBasico)
			o = dG.busca(CodEmp, CodCliente);
		return (o);
	}

	@Override
	public List<Object> lista() {
		List<Object> lista;
		lista = null;
		if (dG instanceof DaoBasico)
			lista = dG.carrega();
		return lista;
	}
}
