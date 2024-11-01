package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Cliente;
import Util.Diversos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;

public class JFrmCliente extends JFrame {

	private JPanel contentPane;
	private JLabel lblMatricula;
	private JLabel lblCodigo;
	private ControleBasico bA;
	private final String titulo;
	private JButton jBtnIncluir;
	private JButton jBtnAlterar;
	private JButton jBtnExcluir;
	private JTextField jTxtCodCli;
	private JTextField jTxtNome;
	private JTextField jTxtSalario;
	private JTextField jTxtAporteIni;
	private JTextField jTxtDispInv;
	private JLabel lblGanhoEsperado;
	private JTextField jTxtGanhoEsp;
	private JFormattedTextField jTxtCpf;
	private JFormattedTextField jFtdTxtDataNas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrmCliente frame = new JFrmCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public JFrmCliente() {
		setBackground(Color.LIGHT_GRAY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtNome.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar Clientes";
		this.bA = new ControleGeral(2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 438);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jTxtCodCli = new JTextField();
		jTxtCodCli.setForeground(Color.WHITE);
		jTxtCodCli.setBackground(Color.LIGHT_GRAY);
		jTxtCodCli.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				 pesquisa();
			}
		});
		jTxtCodCli.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCodCli
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCodCli.setColumns(10);
		jTxtCodCli.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCodCli.setBounds(127, 11, 306, 25);
		contentPane.add(jTxtCodCli);

		lblMatricula = new JLabel("Cod. Cliente: ");
		lblMatricula.setForeground(Color.WHITE);
		lblMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricula.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblMatricula.setBounds(20, 16, 99, 15);
		contentPane.add(lblMatricula);

		lblCodigo = new JLabel("Nome: ");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCodigo.setBounds(66, 42, 53, 15);
		contentPane.add(lblCodigo);

		jTxtNome = new JTextField();
		jTxtNome.setForeground(Color.WHITE);
		jTxtNome.setBackground(Color.LIGHT_GRAY);
		jTxtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtNome.setText(jTxtNome.getText().toUpperCase());
			}
		});
		jTxtNome.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC,
				13));
		jTxtNome.setColumns(10);
		jTxtNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtNome.setBounds(127, 37, 306, 25);
		contentPane.add(jTxtNome);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(29, 275, 414, 103);
		contentPane.add(panel);

		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('I');
			}
		});
		jBtnIncluir
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(24, 18, 84, 25);
		panel.add(jBtnIncluir);

		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(93, 67, 90, 25);
		panel.add(jBtnLimpar);

		JButton jBtnSair = new JButton("Sair");
		jBtnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja finalizar", titulo))
					dispose();
			}
		});
		jBtnSair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnSair.setBounds(237, 67, 90, 25);
		panel.add(jBtnSair);

		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja alterar?", titulo))
				cadastrarDados('A');
			}
		});
		jBtnAlterar
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(176, 18, 84, 25);
		panel.add(jBtnAlterar);

		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja excluir?", titulo))
					cadastrarDados('E');
			}
		});
		setResizable(false);
		setLocationRelativeTo(null);
		jBtnExcluir
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(306, 18, 84, 25);
		panel.add(jBtnExcluir);
		
		JLabel lblCpf = new JLabel("Cpf: ");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCpf.setBounds(29, 68, 90, 15);
		contentPane.add(lblCpf);
		
		jTxtCpf = new JFormattedTextField();
		jTxtCpf.setForeground(Color.WHITE);
		jTxtCpf.setBackground(Color.LIGHT_GRAY);
		jTxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCpf.setColumns(10);
		jTxtCpf.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCpf.setBounds(127, 63, 306, 25);
		contentPane.add(jTxtCpf);
		jTxtCpf.setFormatterFactory(Diversos.FormatoMascara(titulo, 3));

		
		JLabel lblTel = new JLabel("Salário:");
		lblTel.setForeground(Color.WHITE);
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblTel.setBounds(42, 147, 77, 15);
		contentPane.add(lblTel);
		
		jTxtSalario = new JTextField();
		jTxtSalario.setForeground(Color.WHITE);
		jTxtSalario.setBackground(Color.LIGHT_GRAY);
		jTxtSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtSalario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtSalario.setColumns(10);
		jTxtSalario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtSalario.setBounds(127, 142, 309, 25);
		contentPane.add(jTxtSalario);
		
		JLabel lblCep = new JLabel("Aporte Inicial:");
		lblCep.setForeground(Color.WHITE);
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCep.setBounds(20, 176, 99, 15);
		contentPane.add(lblCep);
		
		jTxtAporteIni = new JTextField();
		jTxtAporteIni.setForeground(Color.WHITE);
		jTxtAporteIni.setBackground(Color.LIGHT_GRAY);
		jTxtAporteIni.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtAporteIni.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtAporteIni.setColumns(10);
		jTxtAporteIni.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtAporteIni.setBounds(127, 171, 309, 25);
		contentPane.add(jTxtAporteIni);
		
		JLabel lblDispInvest = new JLabel("Disponibilidade de Investimento:");
		lblDispInvest.setForeground(Color.WHITE);
		lblDispInvest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDispInvest.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblDispInvest.setBounds(30, 202, 206, 25);
		contentPane.add(lblDispInvest);
		
		jTxtDispInv = new JTextField();
		jTxtDispInv.setForeground(Color.WHITE);
		jTxtDispInv.setBackground(Color.LIGHT_GRAY);
		jTxtDispInv.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtDispInv.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jTxtDispInv.setColumns(10);
		jTxtDispInv.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtDispInv.setBounds(240, 202, 196, 25);
		contentPane.add(jTxtDispInv);
		
		lblGanhoEsperado = new JLabel("Ganho Esperado:\r\n");
		lblGanhoEsperado.setForeground(Color.WHITE);
		lblGanhoEsperado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGanhoEsperado.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblGanhoEsperado.setBounds(20, 230, 113, 25);
		contentPane.add(lblGanhoEsperado);
		
		jTxtGanhoEsp = new JTextField();
		jTxtGanhoEsp.setForeground(Color.WHITE);
		jTxtGanhoEsp.setBackground(Color.LIGHT_GRAY);
		jTxtGanhoEsp.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtGanhoEsp.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jTxtGanhoEsp.setColumns(10);
		jTxtGanhoEsp.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtGanhoEsp.setBounds(136, 230, 300, 25);
		contentPane.add(jTxtGanhoEsp);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento:");
		lblDataNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataNascimento.setForeground(Color.WHITE);
		lblDataNascimento.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblDataNascimento.setBounds(10, 95, 109, 15);
		contentPane.add(lblDataNascimento);
		
        jFtdTxtDataNas = new JFormattedTextField();
		jFtdTxtDataNas.setForeground(Color.WHITE);
		jFtdTxtDataNas.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtDataNas.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtDataNas.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtDataNas.setBackground(Color.LIGHT_GRAY);
		jFtdTxtDataNas.setBounds(127, 93, 91, 25);
		contentPane.add(jFtdTxtDataNas);
		jFtdTxtDataNas.setFormatterFactory(Diversos.FormatoMascara(titulo, 0));
	}

	private void cadastrarDados(char opcao) {
		String resp = "";
		if (jTxtCodCli.getText().isEmpty() || jTxtNome.getText().isEmpty() || jTxtCpf.getText().isEmpty() || jTxtAporteIni.getText().isEmpty()||jTxtSalario.getText().isEmpty()
				||jTxtDispInv.getText().isEmpty()||jTxtDispInv.getText().isEmpty()||jFtdTxtDataNas.getText().isEmpty())
			resp = "Favor digitar os dados";
		else {
			Cliente c = new Cliente();
			c.setCodCliente(Integer.parseInt(jTxtCodCli.getText()));
			c.setNomeC(jTxtNome.getText());
			c.setCpf(jTxtCpf.getText());
            c.setDataNas(jFtdTxtDataNas.getText());
            c.setSalario(Float.parseFloat(Diversos.converterValor(jTxtSalario.getText())));
			c.setAporteIni(Float.parseFloat(Diversos.converterValor(jTxtAporteIni.getText())));
			c.setDispInv(Float.parseFloat(Diversos.converterValor(jTxtDispInv.getText())));
			c.setGanhoEsp(Float.parseFloat(Diversos.converterValor(jTxtGanhoEsp.getText())));
			
			if (!bA.setManipular(c, opcao)) {
				resp = "Problemas ao "
						+ (opcao == 'A' ? "atualizar"
								: opcao == 'E' ? "remover" : " inserir")
						+ " os dados do Admnistraodr " + c.getNomeC();
				jBtnAlterar.setEnabled(false);
				jBtnExcluir.setEnabled(false);
				jBtnIncluir.setEnabled(false);
			} else {
				resp = "O Cliente " + c.getNomeC();
				switch (opcao) {
				case 'A':
					resp += "\nfoi atualizado(a)";
					break;
				case 'E':
					resp += "\nfoi removido(a)";
					limpar();
					jBtnAlterar.setEnabled(false);
					jBtnExcluir.setEnabled(false);
					break;
				case 'I':
					resp += "\nfoi inserido(a)";
					jBtnAlterar.setEnabled(true);
					jBtnExcluir.setEnabled(true);
					jBtnIncluir.setEnabled(false);
				}
				resp += " com sucesso";

			}
		}
		Diversos.mostrarDados(resp, titulo,
				(resp.charAt(0) != 'F' && resp.charAt(0) != 'P'));
	}

	private void limpar() {
		JTextField txt[] = {jTxtCodCli,jTxtNome,jTxtSalario,jTxtAporteIni,jTxtDispInv,jTxtGanhoEsp};
		for (JTextField t : txt)
			t.setText("");
		jTxtCodCli.setEditable(true);
		JButton jBtn[] = { jBtnAlterar, jBtnExcluir, jBtnIncluir };
		for (JButton btn : jBtn)
			btn.setEnabled(false);
		jFtdTxtDataNas.setText("");
		jTxtCpf.setText("");
		jTxtNome.requestFocusInWindow();
	}
	
	private void carregaObjetos(Cliente b) {
		jTxtCodCli.setText(String.valueOf(b.getCodCliente()));
		jTxtNome.setText(b.getNomeC());
		jTxtCpf.setText(String.valueOf(b.getCpf()));
		jFtdTxtDataNas.setText(String.valueOf(b.getDataNas()));     
		jTxtSalario.setText(Diversos.doisDigitos(1).format(b.getSalario()));
		jTxtAporteIni.setText(Diversos.doisDigitos(1).format(b.getAporteIni()));
		jTxtDispInv.setText(Diversos.doisDigitos(1).format(b.getDispInv()));
		jTxtGanhoEsp.setText(Diversos.doisDigitos(1).format(b.getGanhoEsp()));

	}
	
	private void pesquisa() {
		int codigo;
        Cliente b;
        if (!Diversos.testaNum(jTxtCodCli.getText(), titulo))
            jTxtCodCli.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtCodCli.getText()), 0 ,0 , titulo))
                 jTxtCodCli.setText(""); //testar se   maior que zero
             else {
                   codigo = Integer.parseInt(jTxtCodCli.getText());
                   Object o = bA.getBusca(codigo,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Cliente  " + codigo + " inexistente", titulo, true);
                   }    
                   else {
                        b = (Cliente) o;
                        carregaObjetos(b); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtCodCli.setEditable(false);
            }
	}
}
