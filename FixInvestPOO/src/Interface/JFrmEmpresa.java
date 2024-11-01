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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Administrador;
import Negocio.Empresa;
import Util.Diversos;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.ComponentOrientation;

import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;

public class JFrmEmpresa extends JFrame {

	private JPanel jContentPane;
    private JTextField jTxtCodEmp;
    private JTextField jTxtEmpresa;
    private JComboBox<String> jCmbAdm;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final String titulo;
    private ControleBasico cA, cO;
    private List<Object>  listaA;
    private JLabel jLblCodAdm;
    private JTextField jTxtRendaFixa;
    private JTextField jTxtFundoImobiliario;
    private JTextField jTxtAcao;
    

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmEmpresa frame = new JFrmEmpresa();
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
	public JFrmEmpresa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtEmpresa.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar Empresa";
		this.cA = new ControleGeral(1);
		this.cO = new ControleGeral(3);
	
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setTitle("Cadastrar Empresa");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 473, 363);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(Color.LIGHT_GRAY);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(Color.GRAY);
		jPnlBotoes.setBounds(21, 171, 414, 124);
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
		jBtnLimpar.setBounds(81, 72, 90, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnSair = new JButton("Sair");
		jBtnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja finalizar", titulo))
					dispose();
			}
		});
		jBtnSair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnSair.setBounds(225, 72, 90, 25);
		jPnlBotoes.add(jBtnSair);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja alterar?", titulo))
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(164, 23, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					cadastrarDados('E');
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(294, 23, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		JLabel jLblCodEmp = new JLabel("Cod Emp:");
		jLblCodEmp.setForeground(Color.WHITE);
		jLblCodEmp.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCodEmp.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodEmp.setBounds(21, 16, 90, 15);
		jLblCodEmp.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblCodEmp);
		
		jTxtCodEmp = new JTextField();
		jTxtCodEmp.setForeground(Color.WHITE);
		jTxtCodEmp.setBackground(Color.LIGHT_GRAY);
		jTxtCodEmp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtCodEmp.setBounds(109, 11, 326, 25);
		jTxtCodEmp.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCodEmp.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCodEmp.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtCodEmp);
		jTxtCodEmp.setColumns(10);
		
		JLabel jLblEmpresa = new JLabel("Empresa:");
		jLblEmpresa.setForeground(Color.WHITE);
		jLblEmpresa.setBounds(33, 47, 66, 15);
		jLblEmpresa.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		jContentPane.add(jLblEmpresa);
		
		jTxtEmpresa = new JTextField();
		jTxtEmpresa.setForeground(Color.WHITE);
		jTxtEmpresa.setBackground(Color.LIGHT_GRAY);
		jTxtEmpresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtEmpresa.setText(jTxtEmpresa.getText().toUpperCase());
			}
		});
		jTxtEmpresa.setBounds(109, 42, 326, 25);
		jTxtEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtEmpresa.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtEmpresa.setColumns(10);
		jTxtEmpresa.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtEmpresa);
		
		JLabel lblAdm = new JLabel("Administrador:");
		lblAdm.setForeground(Color.WHITE);
		lblAdm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdm.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblAdm.setBounds(10, 75, 99, 25);
		jContentPane.add(lblAdm);
		
		jCmbAdm = new JComboBox<String>();
		jCmbAdm.setForeground(Color.WHITE);
		jCmbAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaChave();
			}
		});
		jCmbAdm.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jCmbAdm.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbAdm.setBackground(Color.LIGHT_GRAY);
		jCmbAdm.setBounds(109, 75, 188, 25);
		jContentPane.add(jCmbAdm);
		 
		 JLabel lblCodAdm = new JLabel("Cod Adm: ");
		 lblCodAdm.setForeground(Color.WHITE);
		 lblCodAdm.setHorizontalAlignment(SwingConstants.RIGHT);
		 lblCodAdm.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblCodAdm.setBounds(298, 81, 75, 15);
		 jContentPane.add(lblCodAdm);
		 
		 jLblCodAdm = new JLabel("");
		 jLblCodAdm.setForeground(Color.WHITE);
		 jLblCodAdm.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		 jLblCodAdm.setHorizontalAlignment(SwingConstants.CENTER);
		 jLblCodAdm.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jLblCodAdm.setBounds(375, 75, 60, 25);
		 jContentPane.add(jLblCodAdm);
		 
		 JLabel jLblRendaFixa = new JLabel("Renda Fixa");
		 jLblRendaFixa.setHorizontalAlignment(SwingConstants.RIGHT);
		 jLblRendaFixa.setForeground(Color.WHITE);
		 jLblRendaFixa.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 jLblRendaFixa.setBounds(93, 118, 80, 15);
		 jContentPane.add(jLblRendaFixa);
		 
		 jTxtRendaFixa = new JTextField();
		 jTxtRendaFixa.setHorizontalAlignment(SwingConstants.CENTER);
		 jTxtRendaFixa.setForeground(Color.WHITE);
		 jTxtRendaFixa.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 jTxtRendaFixa.setColumns(10);
		 jTxtRendaFixa.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jTxtRendaFixa.setBackground(Color.LIGHT_GRAY);
		 jTxtRendaFixa.setBounds(108, 135, 60, 25);
		 jContentPane.add(jTxtRendaFixa);
		 
		 jTxtFundoImobiliario = new JTextField();
		 jTxtFundoImobiliario.setHorizontalAlignment(SwingConstants.CENTER);
		 jTxtFundoImobiliario.setForeground(Color.WHITE);
		 jTxtFundoImobiliario.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 jTxtFundoImobiliario.setColumns(10);
		 jTxtFundoImobiliario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jTxtFundoImobiliario.setBackground(Color.LIGHT_GRAY);
		 jTxtFundoImobiliario.setBounds(236, 135, 60, 25);
		 jContentPane.add(jTxtFundoImobiliario);
		 
		 JLabel jLblFundoImobiliario = new JLabel("Fundo Imobiliário");
		 jLblFundoImobiliario.setHorizontalAlignment(SwingConstants.RIGHT);
		 jLblFundoImobiliario.setForeground(Color.WHITE);
		 jLblFundoImobiliario.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 jLblFundoImobiliario.setBounds(213, 118, 111, 15);
		 jContentPane.add(jLblFundoImobiliario);
		 
		 jTxtAcao = new JTextField();
		 jTxtAcao.setHorizontalAlignment(SwingConstants.CENTER);
		 jTxtAcao.setForeground(Color.WHITE);
		 jTxtAcao.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 jTxtAcao.setColumns(10);
		 jTxtAcao.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jTxtAcao.setBackground(Color.LIGHT_GRAY);
		 jTxtAcao.setBounds(374, 135, 60, 25);
		 jContentPane.add(jTxtAcao);
		 
		 JLabel jLblAcao = new JLabel("Ação");
		 jLblAcao.setHorizontalAlignment(SwingConstants.RIGHT);
		 jLblAcao.setForeground(Color.WHITE);
		 jLblAcao.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 jLblAcao.setBounds(343, 118, 80, 15);
		 jContentPane.add(jLblAcao);
		 
		 JLabel lblRendimento = new JLabel("Rendimento(%):");
		 lblRendimento.setHorizontalAlignment(SwingConstants.CENTER);
		 lblRendimento.setForeground(Color.WHITE);
		 lblRendimento.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		 lblRendimento.setBounds(5, 135, 99, 25);
		 jContentPane.add(lblRendimento);
	        
		 setResizable(false);
		 setLocationRelativeTo(null); //centraliza o formul�rio
		 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmEmpresa.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	private void carregaLista() {
		int posi = jCmbAdm.getSelectedIndex();
		listaA = cA.lista();
		jCmbAdm.removeAllItems();
		for(Object o: listaA){
			Administrador b = (Administrador) o;
			jCmbAdm.addItem(b.getNomeA());
		}
		if(posi>-1)
			jCmbAdm.setSelectedIndex(posi);
		else{
			jLblCodAdm.setText("");
			jCmbAdm.setSelectedIndex(-1);
		}
	}
	
	private void limpar() {
		 JTextField txt[] = {jTxtCodEmp, jTxtEmpresa, jTxtRendaFixa, jTxtFundoImobiliario, jTxtAcao};
	     for (JTextField t : txt)
	            t.setText("");
	     jCmbAdm.setSelectedIndex(-1);
	     jLblCodAdm.setText("");
	     jTxtCodEmp.setEditable(true);
	     JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton btn : jBtn) 
	            btn.setEnabled(false);    
	     jTxtEmpresa.requestFocusInWindow();
	}
	
	private void carregaObjetos(Empresa e) {
		jTxtCodEmp.setText(String.valueOf(e.getCodEmp()));
        jTxtEmpresa.setText(e.getEmpresa());
        jLblCodAdm.setText(String.valueOf(e.getAdministrador().getCodAdm()));
        jCmbAdm.setSelectedItem(e.getAdministrador().getNomeA());
        jTxtRendaFixa.setText(Diversos.doisDigitos(2).format((e.getRendafixa())));
        jTxtFundoImobiliario.setText(Diversos.doisDigitos(2).format((e.getFundoimobiliario())));
        jTxtAcao.setText(Diversos.doisDigitos(2).format((e.getAcao())));
        
        /*jTxtRendaFixa.setText(Diversos.doisDigitos(2).format(e.getRendafixa()));
        jTxtFundoImobiliario.setText(Diversos.doisDigitos(2).format(e.getFundoimobiliario()));
        jTxtAcao.setText(Diversos.doisDigitos(2).format(e.getAcao()));*/
	}
	
	private void cadastrarDados(char opcao) {
	    String resp = "";
	    if(jTxtCodEmp.getText().isEmpty() || jTxtEmpresa.getText().isEmpty() || jCmbAdm.getSelectedIndex() < 0 || 
	        jTxtRendaFixa.getText().isEmpty() || jTxtFundoImobiliario.getText().isEmpty() || jTxtAcao.getText().isEmpty()) {
	        resp = "Favor digitar os dados";
	    } else {
	        Empresa e = new Empresa();
	        e.setCodEmp(Integer.parseInt(jTxtCodEmp.getText()));
	        e.setEmpresa(jTxtEmpresa.getText());
	        Object o = cA.getBusca(Integer.parseInt(jLblCodAdm.getText()), 0);
	        Administrador a = (Administrador) o;
	        e.setAdministrador(a);

	        e.setRendafixa(Float.parseFloat(Diversos.converterValor(jTxtRendaFixa.getText()))/100);
	        e.setFundoimobiliario(Float.parseFloat(Diversos.converterValor(jTxtFundoImobiliario.getText()))/100);
	        e.setAcao(Float.parseFloat(Diversos.converterValor(jTxtAcao.getText()))/100);
	        	        
	        if (!cO.setManipular(e, opcao)) {
	            resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ? "remover" : "inserir") + " os dados do(a) consumidor(a) " + e.getEmpresa();
	            jBtnAlterar.setEnabled(false);
	            jBtnExcluir.setEnabled(false);
	            jBtnIncluir.setEnabled(false);
	        } else {
	            resp = "A Empresa " +  e.getEmpresa();
	            switch(opcao) {
	                case 'A' :  resp += "\nfoi atualizado(a)";
	                            break;
	                case 'E' :  resp  +=  "\nfoi removido(a)";
	                            limpar();
	                            jBtnAlterar.setEnabled(false);
	                            jBtnExcluir.setEnabled(false);
	                            break;
	                case 'I' :  resp +=  "\nfoi inserido(a)";
	                            jBtnAlterar.setEnabled(true);
	                            jBtnExcluir.setEnabled(true);
	                            jBtnIncluir.setEnabled(false);                  
	            }
	            resp += " com sucesso";
	        }                  
	    }
	    Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && resp.charAt(0) != 'P'));
	}

	 
	private void pesquisa() {
		int numero;
        Empresa e;
        if (!Diversos.testaNum(jTxtCodEmp.getText(), titulo))
            jTxtCodEmp.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtCodEmp.getText()), 0 ,0 , titulo))
                 jTxtCodEmp.setText(""); //testar se � maior que zero
             else {
                   numero = Integer.parseInt(jTxtCodEmp.getText());
                   Object o = cO.getBusca(numero,0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Empresa " + numero + " inexistente", titulo, true);
                   }    
                   else {
                        e = (Empresa) o;
                        carregaObjetos(e);
                        jBtnAlterar.setEnabled(true);
                        jBtnExcluir.setEnabled(true);
                  }
                  jTxtCodEmp.setEditable(false);
            }
	}
	
	private void selecionaChave(){
		int ind = jCmbAdm.getSelectedIndex();
		if(ind>-1){
			Object o= listaA.get(ind);
			Administrador a = (Administrador) o;
			jLblCodAdm.setText(String.valueOf(a.getCodAdm()));
		}
	}
}
