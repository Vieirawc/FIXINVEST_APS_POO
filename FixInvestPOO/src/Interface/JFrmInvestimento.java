package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Investimento;
import Negocio.Cliente;
import Negocio.Empresa;
import Negocio.Administrador;
import Util.Diversos;
import javax.swing.JComboBox;

public class JFrmInvestimento extends JFrame {
	
	
    double auxDispInv,taxaRetorno,taxaRendaFixa,taxaFundoImobiliario,taxaAcao,valorEsperado,aporteInicial;


	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JComboBox<String> jCmbEmpresa;
	private JComboBox<String> jCmbNomeC;
	private JComboBox<String> jCmbNomeA;
	private JComboBox<String> jCmbTipoInvestimento;

    private JLabel jLblCodEmp;
    private JLabel jLblCodAdm;
    private JLabel jLblCodCliente;
    
    private JLabel jLblRendaFixa;
    private JLabel jLblFundoImobiliario;
    private JLabel jLblAcao;
    
    private JLabel jLblSalario;
    private JLabel jLblAporteInicial;
    private JLabel jLblDisponibilidadeInvestimento;
    private JLabel jLblGanhoEsperado;
    
    private JLabel jLblRendaFixaC;
    private JLabel jLblFundoImobiliarioC;
    private JLabel jLblAcaoC;

    
    private JTextField jTxtTitulo;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    
    private JTextArea jTxtARelatorio;
    private BasicComboBoxRenderer.UIResource uIResourceA;
	private BasicComboBoxRenderer.UIResource uIResourceS;
	private BasicComboBoxRenderer.UIResource uIResourceF;
    
    private final String titulo;
    private ControleBasico cA, cC, cL, cE;  
    private List<Object> listaA, listaC, listaL, listaE;
    private JTextField jTxtNomeC;
    private JLabel jLblTN;
    private JLabel jLblTempo;
    private JLabel jLblRF_1;
    private JLabel jLblFI_1;
    private JLabel jLblA_1;
	
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmInvestimento frame = new JFrmInvestimento();
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
	public JFrmInvestimento() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jCmbTipoInvestimento.requestFocusInWindow();
			}
		});
		titulo = "Sistema de Investimento (FixInvest)";
		this.cA = new ControleGeral(1);
		this.cC = new ControleGeral(2);
		this.cL = new ControleGeral(3);
		this.cE = new ControleGeral(4);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		centralizarTitulo();
		//setTitle(titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1030, 584);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(Color.LIGHT_GRAY);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setToolTipText("");
		jPnlBotoes.setBackground(Color.GRAY);
		jPnlBotoes.setBounds(168, 350, 696, 65);
		jPnlBotoes.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jPnlBotoes);
		jPnlBotoes.setLayout(null);
		
		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDados('I');			
			}
		});
		jBtnIncluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 23, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(467, 23, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnRetornar = new JButton("Sair");
		jBtnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja retornar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(580, 23, 106, 25);
		jPnlBotoes.add(jBtnRetornar);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(121, 23, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					cadastrarDados('E');
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(226, 23, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		JButton jBtnRelatorio = new JButton("Relat\u00F3rio");
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  relatorioGeral();	
			}
		});
		jBtnRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(332, 23, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JScrollPane jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBounds(168, 421, 696, 100);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		jTxtARelatorio.setBackground(Color.GRAY);
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		JLabel jLblEmpresa = new JLabel("Empresa");
		jLblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		jLblEmpresa.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblEmpresa.setBounds(27, 17, 91, 25);
		jContentPane.add(jLblEmpresa);
		
		jCmbEmpresa = new JComboBox<String>();
		jCmbEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaChave(0);
			}
		});
		jCmbEmpresa.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jCmbEmpresa.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbEmpresa.setBackground(Color.LIGHT_GRAY);
		jCmbEmpresa.setBounds(114, 17, 140, 25);
		uIResourceA = new BasicComboBoxRenderer.UIResource();
		uIResourceA.setHorizontalAlignment(SwingConstants.CENTER);
		jCmbEmpresa.setRenderer(uIResourceA);
		jContentPane.add(jCmbEmpresa);
				 
		jLblCodAdm = new JLabel("");
		jLblCodAdm.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblCodAdm.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodAdm.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCodAdm.setBounds(433, 304, 49, 23);
		jContentPane.add(jLblCodAdm);
		 
		JLabel jLblCC = new JLabel("C\u00F3digo  do cliente");
		jLblCC.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCC.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCC.setBounds(284, 144, 141, 25);
		jContentPane.add(jLblCC);
		 
		JLabel jLblNomeC = new JLabel("Cliente");
		jLblNomeC.setHorizontalAlignment(SwingConstants.CENTER);
		jLblNomeC.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblNomeC.setBounds(44, 145, 68, 25);
		jContentPane.add(jLblNomeC);
		
		JLabel jLblCA = new JLabel("C\u00F3digo do administrador");
		jLblCA.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCA.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCA.setBounds(255, 304, 180, 25);
		jContentPane.add(jLblCA);
		
		jLblCodCliente = new JLabel("");
		jLblCodCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodCliente.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCodCliente.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblCodCliente.setBounds(423, 145, 49, 23);
		jContentPane.add(jLblCodCliente);
		 
		jCmbNomeA = new JComboBox<String>();
	    jCmbNomeA.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		     selecionaChave(2);
		  }
		});
		jCmbNomeA.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jCmbNomeA.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbNomeA.setBackground(Color.LIGHT_GRAY);
		jCmbNomeA.setBounds(121, 302, 140, 25);
		uIResourceF = new BasicComboBoxRenderer.UIResource();
		uIResourceF.setHorizontalAlignment(SwingConstants.CENTER);
		jCmbNomeA.setRenderer(uIResourceF);
		jContentPane.add(jCmbNomeA);
		
		JLabel jLblCE = new JLabel("Código da empresa");
		jLblCE.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCE.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCE.setBounds(299, 16, 120, 25);
		jContentPane.add(jLblCE);
		
		jLblCodEmp = new JLabel("");
		jLblCodEmp.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodEmp.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCodEmp.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblCodEmp.setBounds(429, 17, 49, 23);
		jContentPane.add(jLblCodEmp);
		
		JLabel jLblAdministrador = new JLabel("Administrador");
		jLblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		jLblAdministrador.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblAdministrador.setBounds(10, 302, 112, 25);
		jContentPane.add(jLblAdministrador);
		uIResourceS = new BasicComboBoxRenderer.UIResource();
		uIResourceS.setHorizontalAlignment(SwingConstants.CENTER);
        
        jCmbNomeC = new JComboBox<String>();
        jCmbNomeC.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		selecionaChave(1);
         	}
        });
        jCmbNomeC.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
        jCmbNomeC.setBorder(new LineBorder(Color.BLACK, 2, true));
        jCmbNomeC.setBackground(Color.LIGHT_GRAY);
        jCmbNomeC.setBounds(110, 144, 140, 25);
        jCmbNomeC.setRenderer(uIResourceS);
        jContentPane.add(jCmbNomeC);
        
        jTxtTitulo = new JTextField();
        jTxtTitulo.setBackground(Color.LIGHT_GRAY);
        jTxtTitulo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
        jTxtTitulo.setBorder(new LineBorder(Color.BLACK, 2));
        jTxtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jTxtTitulo.setBounds(117, 17, 137, 24);
        jContentPane.add(jTxtTitulo);
        jTxtTitulo.setColumns(10);
        
        JLabel lblTipoInvestimento = new JLabel("Tipo Investimento:");
        lblTipoInvestimento.setHorizontalTextPosition(SwingConstants.CENTER);
        lblTipoInvestimento.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoInvestimento.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
        lblTipoInvestimento.setBounds(659, 20, 160, 25);
        jContentPane.add(lblTipoInvestimento);
		
        jLblRendaFixa = new JLabel("");
        jLblRendaFixa.setHorizontalAlignment(SwingConstants.CENTER);
		jLblRendaFixa.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblRendaFixa.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblRendaFixa.setBounds(54, 75, 92, 23);
		jContentPane.add(jLblRendaFixa);
		
		JLabel jLblRF = new JLabel("Renda Fixa");
		jLblRF.setHorizontalAlignment(SwingConstants.CENTER);
		jLblRF.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblRF.setBounds(37, 53, 120, 25);
		jContentPane.add(jLblRF);
		
		JLabel jLblFI = new JLabel("Fundo Imobiliario");
		jLblFI.setHorizontalAlignment(SwingConstants.CENTER);
		jLblFI.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblFI.setBounds(198, 53, 120, 25);
		jContentPane.add(jLblFI);
		
		jLblFundoImobiliario = new JLabel("");
		jLblFundoImobiliario.setHorizontalAlignment(SwingConstants.CENTER);
		jLblFundoImobiliario.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblFundoImobiliario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblFundoImobiliario.setBounds(198, 75, 120, 23);
		jContentPane.add(jLblFundoImobiliario);
		
		JLabel jLblA = new JLabel("Ação");
		jLblA.setHorizontalAlignment(SwingConstants.CENTER);
		jLblA.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblA.setBounds(369, 53, 120, 25);
		jContentPane.add(jLblA);
		
		jLblAcao = new JLabel("");
		jLblAcao.setHorizontalAlignment(SwingConstants.CENTER);
		jLblAcao.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblAcao.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblAcao.setBounds(382, 75, 96, 23);
		jContentPane.add(jLblAcao);
		
		jTxtNomeC = new JTextField();
		jTxtNomeC.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNomeC.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jTxtNomeC.setColumns(10);
		jTxtNomeC.setBorder(new LineBorder(Color.BLACK, 2));
		jTxtNomeC.setBackground(Color.LIGHT_GRAY);
		jTxtNomeC.setBounds(114, 144, 137, 24);
		jContentPane.add(jTxtNomeC);
		
		jCmbTipoInvestimento = new JComboBox<String>();
		jCmbTipoInvestimento.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent arg0) {
         		selecionaChave(3);
         	}
        });
		jCmbTipoInvestimento.setForeground(Color.WHITE);
		jCmbTipoInvestimento.setBackground(Color.LIGHT_GRAY);
		jCmbTipoInvestimento.setBounds(669, 56, 150, 22);
		jCmbTipoInvestimento.setRenderer(uIResourceS);
		jContentPane.add(jCmbTipoInvestimento);
		 
		
		jLblSalario = new JLabel("");
		jLblSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jLblSalario.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblSalario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblSalario.setBounds(54, 213, 92, 23);
		jContentPane.add(jLblSalario);
		
		JLabel jLblS = new JLabel("Salario");
		jLblS.setHorizontalAlignment(SwingConstants.CENTER);
		jLblS.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblS.setBounds(37, 191, 120, 25);
		jContentPane.add(jLblS);
		
		JLabel jLblAI = new JLabel("Aporte Inicial");
		jLblAI.setHorizontalAlignment(SwingConstants.CENTER);
		jLblAI.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblAI.setBounds(178, 191, 120, 25);
		jContentPane.add(jLblAI);
		
		jLblAporteInicial = new JLabel("");
		jLblAporteInicial.setHorizontalAlignment(SwingConstants.CENTER);
		jLblAporteInicial.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblAporteInicial.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblAporteInicial.setBounds(188, 213, 110, 23);
		jContentPane.add(jLblAporteInicial);
		
		JLabel jLblDI = new JLabel("Disponibilidade Investimento");
		jLblDI.setHorizontalAlignment(SwingConstants.CENTER);
		jLblDI.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblDI.setBounds(762, 168, 180, 25);
		jContentPane.add(jLblDI);
		
		jLblDisponibilidadeInvestimento = new JLabel("");
		jLblDisponibilidadeInvestimento.setHorizontalAlignment(SwingConstants.CENTER);
		jLblDisponibilidadeInvestimento.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblDisponibilidadeInvestimento.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblDisponibilidadeInvestimento.setBounds(762, 193, 180, 23);
		jContentPane.add(jLblDisponibilidadeInvestimento);
		
		jLblGanhoEsperado = new JLabel("");
		jLblGanhoEsperado.setHorizontalAlignment(SwingConstants.CENTER);
		jLblGanhoEsperado.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblGanhoEsperado.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblGanhoEsperado.setBounds(571, 193, 181, 23);
		jContentPane.add(jLblGanhoEsperado);
		
		JLabel jLblGE = new JLabel("Ganho Esperado");
		jLblGE.setHorizontalAlignment(SwingConstants.CENTER);
		jLblGE.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblGE.setBounds(572, 168, 180, 25);
		jContentPane.add(jLblGE);
		
		jLblTN = new JLabel("Tempo");
		jLblTN.setHorizontalAlignment(SwingConstants.CENTER);
		jLblTN.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblTN.setBounds(659, 234, 180, 25);
		jContentPane.add(jLblTN);
		
		jLblTempo = new JLabel("");
		jLblTempo.setHorizontalAlignment(SwingConstants.CENTER);
		jLblTempo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblTempo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblTempo.setBounds(659, 258, 188, 38);
		jContentPane.add(jLblTempo);
		
		jLblRF_1 = new JLabel("Renda Fixa");
		jLblRF_1.setForeground(Color.BLACK);
		jLblRF_1.setHorizontalAlignment(SwingConstants.CENTER);
		jLblRF_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblRF_1.setBounds(561, 98, 120, 25);
		jContentPane.add(jLblRF_1);
		
		jLblFI_1 = new JLabel("Fundo Imobiliario");
		jLblFI_1.setHorizontalAlignment(SwingConstants.CENTER);
		jLblFI_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblFI_1.setBounds(699, 98, 120, 25);
		jContentPane.add(jLblFI_1);
		
		jLblRendaFixaC = new JLabel("");
		jLblRendaFixaC.setHorizontalAlignment(SwingConstants.CENTER);
		jLblRendaFixaC.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblRendaFixaC.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblRendaFixaC.setBounds(575, 120, 91, 23);
		jContentPane.add(jLblRendaFixaC);
		
		jLblFundoImobiliarioC = new JLabel("");
		jLblFundoImobiliarioC.setHorizontalAlignment(SwingConstants.CENTER);
		jLblFundoImobiliarioC.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblFundoImobiliarioC.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblFundoImobiliarioC.setBounds(699, 120, 120, 23);
		jContentPane.add(jLblFundoImobiliarioC);
		
		jLblA_1 = new JLabel("Ação");
		jLblA_1.setHorizontalAlignment(SwingConstants.CENTER);
		jLblA_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblA_1.setBounds(832, 98, 120, 25);
		jContentPane.add(jLblA_1);
		
		jLblAcaoC = new JLabel("");
		jLblAcaoC.setHorizontalAlignment(SwingConstants.CENTER);
		jLblAcaoC.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		jLblAcaoC.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblAcaoC.setBounds(846, 120, 96, 23);
		jContentPane.add(jLblAcaoC);

		 
		setResizable(false);
		setLocationRelativeTo(null); //centraliza o formul rio
		 
		try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmInvestimento.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	private void limpar() {
         
		 JComboBox<?> jCmb[] = {jCmbNomeC, jCmbEmpresa, jCmbNomeA, jCmbTipoInvestimento};
	     for (JComboBox<?> cmb : jCmb) {
		    	cmb.setSelectedIndex(-1);
	            cmb.setEnabled(true);
	            cmb.setVisible(true);
		}
	        
        JLabel label[] = {jLblCodCliente, jLblCodAdm, jLblCodEmp, jLblRendaFixa, jLblFundoImobiliario, jLblAcao,jLblSalario,jLblAporteInicial,
        		jLblDisponibilidadeInvestimento,jLblGanhoEsperado,jLblRendaFixaC,jLblFundoImobiliarioC,jLblAcaoC,jLblTempo};
        for (JLabel lbl : label) {
            lbl.setText("");
        }
       
        JTextField txt[] = {jTxtTitulo, jTxtNomeC};
        for (JTextField t : txt){ 
            t.setText("");
        }
        
        JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
        for (JButton btn : jBtn) {
            btn.setEnabled(false);
        }
        jTxtTitulo.setVisible(false);
        jTxtNomeC.setVisible(false);
	    jTxtARelatorio.setText("");
    } 
	
	private void relatorioGeral() {
        listaE = cE.lista();
        String resp = "";
        
        float totalRF = 0;
        float totalFI = 0;
        float totalA = 0;
        float totalInvestimentos=0;
        
        int QtdConservador = 0;
        int QtdModerado = 0;
        int QtdAgressivo = 0;
        
        int QtdClientes = 0;
        float totalSal = 0;
        
        for (Object o : listaE) {
            Investimento e = (Investimento) o;
            resp += e.relatorio();
            QtdClientes++;
            float auxDispInvRelatorio = e.getCliente().getDispInv();
           
            resp+="\nCarteira: ";
            if (e.getTipoInvestimento().equals("CONSERVADOR")) {
            	QtdConservador++;
                
                totalRF+=auxDispInvRelatorio * 0.8;
            	totalFI+=auxDispInvRelatorio * 0.15;
            	totalA+=auxDispInvRelatorio * 0.05;
            	
            	resp += "Renda Fixa: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.8);
                resp += " | Fundo Imobiliario: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.15);
                resp += " | Ação: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.05);
            } else if (e.getTipoInvestimento().equals("MODERADO")) {
            	QtdModerado++;
            	
            	totalRF+=auxDispInvRelatorio * 0.6;
            	totalFI+=auxDispInvRelatorio * 0.25;
            	totalA+=auxDispInvRelatorio * 0.15;
            	
                resp += "Renda Fixa: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.6);
                resp += " | Fundo Imobiliario: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.25);
                resp += " | Ação: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.15);
            } else {
            	QtdAgressivo++;

            	totalRF+=auxDispInvRelatorio * 0.3;
            	totalFI+=auxDispInvRelatorio * 0.2;
            	totalA+=auxDispInvRelatorio * 0.5;
            	
                resp += "Renda Fixa: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.3);
                resp += " | Fundo Imobiliario: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.2);
                resp += " | Ação: " + Diversos.doisDigitos(1).format(auxDispInvRelatorio * 0.5);
            }
            totalSal += e.getCliente().getSalario();
            totalInvestimentos += e.getCliente().getAporteIni()+e.getCliente().getDispInv();
            double valorAtual = e.getCliente().getAporteIni();
            int meses = 0;
            while (valorAtual < e.getCliente().getGanhoEsp()) {
            valorAtual += auxDispInvRelatorio;
	        valorAtual *= (1 + (taxaRetorno) / 12);
	        meses++;
	       }
	       int anos = meses / 12;
	       meses = meses % 12;
	       String texto = String.format("%d anos, %d meses", anos, meses);   
   		   resp +="\nTempo: " + texto;
   		   resp += "\n__________________________________________________________________\n\n";
        }
        resp += "Resumo Geral (TOTAL)";
        resp += "\nInvestimentos FixInvest: " + Diversos.doisDigitos(1).format(totalInvestimentos);
		resp += "\nDistribuição das Carteiras: ";
	   	resp += "Renda Fixa: " + Diversos.doisDigitos(1).format(totalRF);
	    resp += " | Fundo Imobiliario: " + Diversos.doisDigitos(1).format(totalFI);
	    resp += " | Ação: " + Diversos.doisDigitos(1).format(totalA);
        resp += "\nTotal investimentos por Tipo: ";
        resp += "Conservador: " + QtdConservador;
	    resp += " | Moderado: " + QtdModerado;
	    resp += " | Agressivo: " + QtdAgressivo;
        resp += "\nMédia salarial dos Clientes: " + Diversos.doisDigitos(1).format(totalSal/QtdClientes);

        jTxtARelatorio.setEditable(false);

        jTxtARelatorio.setText(!resp.isEmpty() ? resp : "Inexistência de dados");
    }
	
	private void carregaLista() {
		 try{
	           int[] posi = {jCmbEmpresa.getSelectedIndex(), jCmbNomeC.getSelectedIndex(), jCmbNomeA.getSelectedIndex(), jCmbTipoInvestimento.getSelectedIndex()};
	           
	           //dados auxiliares
	           jLblRendaFixaC.setText("");
	           jLblFundoImobiliarioC.setText("");
	           jLblAcaoC.setText("");
	           
	           listaL = cL.lista();  
	           jCmbEmpresa.removeAllItems();
	           for (Object o : listaL) {
	                Empresa l = (Empresa) o;
	                jCmbEmpresa.addItem(l.getEmpresa());
	           } 
	           if (posi[0] > -1) 
	              jCmbEmpresa.setSelectedIndex(posi[0]);
	           else {
	        	  //dados auxilares
        	   	  jLblRendaFixa.setText("");
        	   	  jLblFundoImobiliario.setText("");
        	   	  jLblAcao.setText("");
        	   	  
	              jLblCodEmp.setText("");
	              jCmbEmpresa.setSelectedIndex(-1);
	           }
	           
	           listaC = cC.lista();  
	           jCmbNomeC.removeAllItems();
	           for (Object o : listaC) {
	                Cliente c  = (Cliente) o;
	                jCmbNomeC.addItem(c.getNomeC());
	           } 
	           if (posi[1] > -1) 
	                    jCmbNomeC.setSelectedIndex(posi[0]);
	           else {
	        	   	  //dados auxilares
	        	   	  jLblSalario.setText("");
	        	   	  jLblAporteInicial.setText("");
	        	   	  jLblDisponibilidadeInvestimento.setText("");
	        	   	  jLblGanhoEsperado.setText("");
	        	   
	                  jLblCodCliente.setText("");
	                  jCmbNomeC.setSelectedIndex(-1);
	           }
	           
	           listaA = cA.lista();  
	           jCmbNomeA.removeAllItems();
	            for (Object o : listaA) {
	                Administrador a = (Administrador) o;
	                jCmbNomeA.addItem(a.getNomeA());
	            }
	            if (posi[2] > -1)  
	                jCmbNomeA.setSelectedIndex(posi[0]);
	           else {
	                 jLblCodAdm.setText(""); 
	                 jCmbNomeA.setSelectedIndex(-1);
	           }
	            
	            jCmbTipoInvestimento.removeAllItems();
	            jCmbTipoInvestimento.addItem("CONSERVADOR");
	    		jCmbTipoInvestimento.addItem("MODERADO");
	    		jCmbTipoInvestimento.addItem("AGRESSIVO");
		           if (posi[3] > -1) 
		              jCmbEmpresa.setSelectedIndex(posi[0]);
		           else {
		        	  //dados auxilares
			          jLblTempo.setText("");
	        	   	  jLblRendaFixaC.setText("");
	        	   	  jLblFundoImobiliarioC.setText("");
	        	   	  jLblAcaoC.setText("");
		              jCmbTipoInvestimento.setSelectedIndex(-1);
		           }
	          
		 }   
	     catch(Exception e) {
	            Diversos.mostrarDados("Problemas ao carregar os dados dos Administradores\n" + 
	              " Empresas e ou Clientes" + e.getMessage(), titulo, false); 
	         }
	    }     
	
	private void carregaObjetos(Investimento a) {
		jLblCodEmp.setText(String.valueOf(a.getEmpresa().getCodEmp()));
		jCmbEmpresa.setSelectedItem(a.getEmpresa().getEmpresa());
		jTxtTitulo.setText(a.getEmpresa().getEmpresa());
		jLblCodCliente.setText(String.valueOf(a.getCliente().getCodCliente()));
		jCmbNomeA.setSelectedItem(a.getCliente().getNomeC());
		jTxtNomeC.setText(a.getCliente().getNomeC());
        jLblCodAdm.setText(String.valueOf(a.getAdministrador().getCodAdm()));
        jCmbNomeA.setSelectedItem(a.getAdministrador().getNomeA()); 
        jCmbTipoInvestimento.setSelectedItem(a.getTipoInvestimento());
        
        //dados adicionais empresa
		jLblRendaFixa.setText(Diversos.doisDigitos(2).format(a.getEmpresa().getRendafixa()));
		jLblFundoImobiliario.setText(Diversos.doisDigitos(2).format(a.getEmpresa().getFundoimobiliario()));
		jLblAcao.setText(Diversos.doisDigitos(2).format(a.getEmpresa().getAcao()));
		
        //dados adicionais cliente
		jLblSalario.setText(Diversos.doisDigitos(1).format(a.getCliente().getSalario()));
		jLblAporteInicial.setText(Diversos.doisDigitos(1).format(a.getCliente().getAporteIni()));
		jLblDisponibilidadeInvestimento.setText(Diversos.doisDigitos(1).format(a.getCliente().getDispInv()));
		jLblGanhoEsperado.setText(Diversos.doisDigitos(1).format(a.getCliente().getGanhoEsp()));
		
		//dados adicionais investimentos
		auxDispInv = a.getCliente().getDispInv();
		if(a.getTipoInvestimento()=="CONSERVADOR") {
			jLblRendaFixaC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.8));
			jLblFundoImobiliarioC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.15));
			jLblAcaoC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.05));
		} else if(a.getTipoInvestimento()=="MODERADO") {
			jLblRendaFixaC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.6));
			jLblFundoImobiliarioC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.25));
			jLblAcaoC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.15));
		} else {
			jLblRendaFixaC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.3));
			jLblFundoImobiliarioC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.2));
			jLblAcaoC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.5));
		}
		
	}
	
	private void centralizarTitulo() {
	    FontMetrics fM = getFontMetrics(getFont());
        int sC = (getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / fM.stringWidth(" ");
        setTitle(String.format("%" + (sC + 99 ) + "s", "") + titulo.trim());
    }
	
	private void cadastrarDados(char opcao) {
         String resp = "";
         if( jCmbEmpresa.getSelectedIndex() < 0 
        		 ||  jCmbNomeA.getSelectedIndex() < 0  ||  
        		 jCmbNomeC.getSelectedIndex() < 0 || jCmbTipoInvestimento.getSelectedIndex()<0) {
              resp = "Favor digitar os dados";
         }
        
		      
         else {
        	 Investimento e = new Investimento();
             Object o;

                o = cL.getBusca(Integer.parseInt(jLblCodEmp.getText()), 0);
             	Empresa l = (Empresa) o;
             	e.setEmpresa(l);
             	
             	o = cC.getBusca(Integer.parseInt(jLblCodCliente.getText()), 0);
             	Cliente c = (Cliente) o;
                e.setCliente(c);                
                 
                  o = cA.getBusca(Integer.parseInt(jLblCodAdm.getText()), 0);
                  Administrador a = (Administrador) o;
                  e.setAdministrador(a);
                 
                  e.setTipoInvestimento((String) jCmbTipoInvestimento.getSelectedItem());

                   if (!cE.setManipular(e, opcao)) {
                       resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ?  
                   	   "remover" : " inserir") + " Empresa: " + e.getEmpresa().getEmpresa();
                       resp += "\n Cliente: " + e.getCliente().getNomeC();
                       resp += "\n Administrador: " + e.getAdministrador().getNomeA();

                       jBtnAlterar.setEnabled(false);
                       jBtnExcluir.setEnabled(false);
                       jBtnIncluir.setEnabled(false);
                   }   
                   else {	
                	   
                	   if(opcao=='E'){
                       o = cL.getBusca(Integer.parseInt(jLblCodEmp.getText()), 0);
                    	Empresa li = (Empresa) o;
                    	e.setEmpresa(li);
                    	
                    	o = cC.getBusca(Integer.parseInt(jLblCodCliente.getText()), 0);
                     	Cliente cli = (Cliente) o;
                        e.setCliente(cli);
                        
                	   }
                	   
                          resp = "O empr stimo do Empresa " + e.getEmpresa().getEmpresa() + 
                        		  " para o cliente " + e.getCliente().getNomeC() + 
                		  " realizado pelo " + e.getAdministrador().getNomeA(); 

                         switch(opcao) {
                              case 'A' :  resp += "\nFoi atualizado(a) ";
                                               break;
                              case 'E' :  resp  +=  "\nFoi removido(a) ";
                                              limpar();
                                              jBtnAlterar.setEnabled(false);
                                              jBtnExcluir.setEnabled(false);
                                              break;
                              case 'I' :  resp +=  "\nFoi inserido(a) ";
                                              jBtnAlterar.setEnabled(true);
                                              jBtnExcluir.setEnabled(true);
                                              jBtnIncluir.setEnabled(false);                  
                            }
                          resp += " com sucesso";                
                    }                  
              }
            Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && resp.charAt(0) != 'P'));
    }
	
	private void selecionaChave(int posi) {
		int ind = -1;
                int indcli = -1;
                int inddispinv = -1;

		Object o;
		switch (posi) {
		    case 0 :  ind = jCmbEmpresa.getSelectedIndex();
                           if (ind > -1) {
                               o = listaL.get(ind);
                               Empresa l = (Empresa) o;
                               //dados auxiliares
                               jLblRendaFixa.setText(Diversos.doisDigitos(2).format(l.getRendafixa()));
                               jLblFundoImobiliario.setText(Diversos.doisDigitos(2).format(l.getFundoimobiliario()));
                               jLblAcao.setText(Diversos.doisDigitos(2).format(l.getAcao()));
                               
                               taxaRendaFixa = l.getRendafixa();
                               taxaFundoImobiliario = l.getFundoimobiliario();
                               taxaAcao = l.getAcao();
                   			  
                               jLblCodEmp.setText(String.valueOf(l.getCodEmp()));
                               jTxtTitulo.setText(String.valueOf(l.getEmpresa()));
                               pesquisa('L');
                               //pesquisaT('L');

                           }          
		    case 1 :  ind = jCmbNomeC.getSelectedIndex();
                           if (ind > -1) {
                                o = listaC.get(ind);
                                Cliente c = (Cliente) o;
                                //dados auxiliares
                                jLblSalario.setText(Diversos.doisDigitos(1).format(c.getSalario()));
                        		jLblAporteInicial.setText(Diversos.doisDigitos(1).format(c.getAporteIni()));
                        		jLblDisponibilidadeInvestimento.setText(Diversos.doisDigitos(1).format(c.getDispInv()));
                        		jLblGanhoEsperado.setText(Diversos.doisDigitos(1).format(c.getGanhoEsp()));
                        		
                        		auxDispInv=c.getDispInv();
                        		valorEsperado=c.getGanhoEsp();
                        		aporteInicial=c.getAporteIni();
                    			
                                jLblCodCliente.setText(String.valueOf(c.getCodCliente()));
                                jTxtNomeC.setText(c.getNomeC());
                                pesquisa('C');
                                //pesquisaT('C');


                           }     
		    case 2 :  ind = jCmbNomeA.getSelectedIndex();
                           if (ind > -1) {
                              o = listaA.get(ind);
                              Administrador a = (Administrador) o;
                              jLblCodAdm.setText(String.valueOf(a.getCodAdm()));	                            
                           }
                           
		    case 3 :  ind = jCmbTipoInvestimento.getSelectedIndex();
		    if (ind > -1) {
            if (ind == 0) {
            	taxaRetorno = (0.8 * taxaRendaFixa + 0.15 * taxaFundoImobiliario + 0.05 * taxaAcao);            	
            	jLblRendaFixaC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.8));
    			jLblFundoImobiliarioC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.15));
    			jLblAcaoC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.05));                            
            } else if(ind == 1)  {
            	
            	taxaRetorno = (0.6 * taxaRendaFixa + 0.25 * taxaFundoImobiliario + 0.15 * taxaAcao);
            	
            	jLblRendaFixaC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.6));
    			jLblFundoImobiliarioC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.25));
    			jLblAcaoC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.15));
            } else if(ind == 2) {
            	
            	taxaRetorno = (0.3 * taxaRendaFixa + 0.2 * taxaFundoImobiliario + 0.5 * taxaAcao);
            	
            	jLblRendaFixaC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.3));
            	jLblFundoImobiliarioC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.2));
    			jLblAcaoC.setText(Diversos.doisDigitos(1).format(auxDispInv*0.5));
            	//jLblTempo.setText(String.valueOf(Math.log(valorEsperado / aporteInicial) / Math.log(1 + taxaRetorno)));
            	}
            	        
		    }
		   }
			/*Este cálculo utiliza a taxa de retorno com base no estilo de investimento 
	    	//e, em seguida, calcula o tempo necessário para atingir o valor esperado. */   
	                ind = jCmbEmpresa.getSelectedIndex();
                    indcli = jCmbNomeC.getSelectedIndex();
                    inddispinv = jCmbTipoInvestimento.getSelectedIndex();

                           if (ind > -1 && indcli > -1 && inddispinv > -1) {
                     double valorAtual = aporteInicial;
                     int meses = 0;
                     while (valorAtual < valorEsperado) {
	             valorAtual += auxDispInv;
	             valorAtual *= (1 + (taxaRetorno) / 12);
	             meses++;
	            }
	            int anos = meses / 12;
	            meses = meses % 12;
	            int dias = (int) ((meses % 1) * 30);
	            String texto = String.format("%d anos, %d meses", anos, meses);
                    jLblTempo.setText("");
	            jLblTempo.setText(texto);
                           }else{ 
                    	   jLblTempo.setText("Cálculo indisponível");
                           }
	 	  
	}
	private void pesquisa(char a) {
	    if (!jLblCodCliente.getText().isEmpty() && 
	         jCmbNomeC.getSelectedIndex() > -1) {       

	        Cliente c = (Cliente) cC.getBusca(Integer.parseInt(jLblCodCliente.getText()), 0);
	        int codigo = Integer.parseInt(jLblCodCliente.getText());
	        Investimento e = (Investimento) cE.getBusca(codigo,0);

	        if (a == 'C') {
	            if (c == null) {
	                Diversos.mostrarDados("Cliente indisponível para investimento", titulo, false);	          
	                jBtnIncluir.setEnabled(false);
	                jBtnAlterar.setEnabled(false);
	                jBtnExcluir.setEnabled(false);
	            } else if (e == null) {
	                jBtnIncluir.setEnabled(true);
	                //Diversos.mostrarDados("Investimento disponível", titulo, true);
	            } else {
	                carregaObjetos(e);
	                jBtnAlterar.setEnabled(true);
	                jBtnExcluir.setEnabled(true);
	            }
	            //jCmbEmpresa.setVisible(false);
	            //jTxtTitulo.setVisible(true);
	            jTxtTitulo.setEditable(false);
	            jCmbNomeC.setVisible(false);
	            jTxtNomeC.setVisible(true);
	            jTxtNomeC.setEditable(false);

	            jCmbTipoInvestimento.requestFocusInWindow();
	        }
	    }
	}
}

