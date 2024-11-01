package Interface;

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
import javax.swing.JFormattedTextField;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Administrador;
import Util.Diversos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JFrmAdministrador extends JFrame {

	private JPanel contentPane;
	private JTextField jTxtCodAdm;
	private JLabel lblMatricula;
	private JLabel lblCodigo;
	private JTextField jTxtNome;
	private ControleBasico bA;
	private final String titulo;
	private JButton jBtnIncluir;
	private JButton jBtnAlterar;
	private JButton jBtnExcluir;
	private JFormattedTextField jTxtCpf;
	private JFormattedTextField jTxtTel;
	private JFormattedTextField jTxtCep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrmAdministrador frame = new JFrmAdministrador();
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
	public JFrmAdministrador() {
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
		titulo = "Cadastrar Administradores";
		this.bA = new ControleGeral(1);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 363);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jTxtCodAdm = new JTextField();
		jTxtCodAdm.setForeground(Color.WHITE);
		jTxtCodAdm.setBackground(Color.LIGHT_GRAY);
		jTxtCodAdm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				 pesquisa();
			}
		});
		jTxtCodAdm.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCodAdm
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCodAdm.setColumns(10);
		jTxtCodAdm.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCodAdm.setBounds(97, 6, 326, 25);
		contentPane.add(jTxtCodAdm);

		lblMatricula = new JLabel("Cod. Adm: ");
		lblMatricula.setForeground(Color.WHITE);
		lblMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricula.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblMatricula.setBounds(-3, 11, 90, 15);
		contentPane.add(lblMatricula);

		lblCodigo = new JLabel("Nome: ");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCodigo.setBounds(34, 42, 53, 15);
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
		jTxtNome.setBounds(97, 37, 326, 25);
		contentPane.add(jTxtNome);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(21, 171, 414, 124);
		contentPane.add(panel);

		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('I');
			}
		});
		jBtnIncluir
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 23, 84, 25);
		panel.add(jBtnIncluir);

		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar
				.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(81, 72, 90, 25);
		panel.add(jBtnLimpar);

		JButton jBtnSair = new JButton("Sair");
		jBtnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja finalisar", titulo))
					dispose();
			}
		});
		jBtnSair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnSair.setBounds(225, 72, 90, 25);
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
		jBtnAlterar.setBounds(164, 23, 84, 25);
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
		jBtnExcluir.setBounds(294, 23, 84, 25);
		panel.add(jBtnExcluir);
		
		JLabel lblCpf = new JLabel("Cpf: ");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCpf.setBounds(10, 70, 77, 15);
		contentPane.add(lblCpf);
		
		jTxtCpf = new JFormattedTextField();  // Substitua JTextField por JFormattedTextField
		jTxtCpf.setForeground(Color.WHITE);
		jTxtCpf.setBackground(Color.LIGHT_GRAY);
		jTxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCpf.setColumns(10);
		jTxtCpf.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCpf.setBounds(97, 65, 326, 25);
		contentPane.add(jTxtCpf);
		jTxtCpf.setFormatterFactory(Diversos.FormatoMascara(titulo, 3));

		
		JLabel lblTel = new JLabel("Telefone: ");
		lblTel.setForeground(Color.WHITE);
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblTel.setBounds(10, 100, 77, 15);
		contentPane.add(lblTel);
		
		jTxtTel = new JFormattedTextField(); 
		jTxtTel.setForeground(Color.WHITE);
		jTxtTel.setBackground(Color.LIGHT_GRAY);
		jTxtTel.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtTel.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtTel.setColumns(10);
		jTxtTel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtTel.setBounds(97, 95, 326, 25);
		contentPane.add(jTxtTel);
		jTxtTel.setFormatterFactory(Diversos.FormatoMascara(titulo, 2));
		
		JLabel lblCep = new JLabel("Cep: ");
		lblCep.setForeground(Color.WHITE);
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCep.setBounds(10, 125, 77, 15);
		contentPane.add(lblCep);
		
		jTxtCep = new JFormattedTextField();  // Substitua JTextField por JFormattedTextField
		jTxtCep.setForeground(Color.WHITE);
		jTxtCep.setBackground(Color.LIGHT_GRAY);
		jTxtCep.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCep.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCep.setColumns(10);
		jTxtCep.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCep.setBounds(97, 125, 326, 25);
		contentPane.add(jTxtCep);
		jTxtCep.setFormatterFactory(Diversos.FormatoMascara(titulo, 4));

	}
	private void limpar() {
		JTextField txt[] = { jTxtCodAdm, jTxtNome};
		for (JTextField t : txt)
			t.setText("");
		jTxtCodAdm.setEditable(true);
		JButton jBtn[] = { jBtnAlterar, jBtnExcluir, jBtnIncluir };
		for (JButton btn : jBtn)
			btn.setEnabled(false);
		jTxtTel.setText("");
		jTxtCep.setText("");
		jTxtCpf.setText("");

		jTxtNome.requestFocusInWindow();
		
	}

	private void cadastrarDados(char opcao) {
		String resp = "";
		if (jTxtCodAdm.getText().isEmpty() || jTxtNome.getText().isEmpty() || jTxtCpf.getText().isEmpty() || jTxtCep.getText().isEmpty()||jTxtTel.getText().isEmpty())
			resp = "Favor digitar os dados";
		else {
			Administrador b = new Administrador();
			b.setCodAdm(Integer.parseInt(jTxtCodAdm.getText()));
			b.setNomeA(jTxtNome.getText());
			b.setCpf(jTxtCpf.getText());
			b.setTel(jTxtTel.getText());
			b.setCep(jTxtCep.getText());

			if (!bA.setManipular(b, opcao)) {
				resp = "Problemas ao "
						+ (opcao == 'A' ? "atualizar"
								: opcao == 'E' ? "remover" : " inserir")
						+ " os dados do Admnistraodr " + b.getNomeA();
				jBtnAlterar.setEnabled(false);
				jBtnExcluir.setEnabled(false);
				jBtnIncluir.setEnabled(false);
			} else {
				resp = "O Administrador " + b.getNomeA();
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
	
	private void carregaObjetos(Administrador b) {
		jTxtCodAdm.setText(String.valueOf(b.getCodAdm()));
		jTxtCep.setText(String.valueOf(b.getCep()));
		jTxtCpf.setText(String.valueOf(b.getCpf()));
        jTxtNome.setText(b.getNomeA()); 
		jTxtTel.setText(String.valueOf((b.getTel())));
	}
	
	private void pesquisa() {
		int codigo;
        Administrador b;
        if (!Diversos.testaNum(jTxtCodAdm.getText(), titulo))
            jTxtCodAdm.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtCodAdm.getText()), 0 ,0 , titulo))
                 jTxtCodAdm.setText(""); //testar se � maior que zero
             else {
                   codigo = Integer.parseInt(jTxtCodAdm.getText());
                   Object o = bA.getBusca(codigo,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Administrador  " + codigo + " inexistente", titulo, true);
                   }    
                   else {
                        b = (Administrador) o;
                        carregaObjetos(b); 
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  } 
                  jTxtCodAdm.setEditable(false);
            }
	}
}
