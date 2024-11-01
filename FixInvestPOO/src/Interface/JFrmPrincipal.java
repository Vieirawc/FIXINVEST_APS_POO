package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Desktop;
import java.net.URI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.FontUIResource;

import Util.Diversos;

import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class JFrmPrincipal extends JFrame {

	private JPanel jContentPane;
    private JLabel jLblAutor;
    private JLabel jLblData;
    private JLabel jLblHora; 
    private JSeparator jSepAutor;
    private JSeparator jSepHora;
    private JSeparator jSepSair;
    private JMenuBar jMnBPrincipal;
    private JMenuItem jMnISair;
    private final String titulo;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	  //	EventQueue.invokeLater(new Runnable() {
	  //		public void run() {
				try {
					JFrmPrincipal frame = new JFrmPrincipal();
					frame.setVisible(true);
					frame.relogio();
				} catch (Exception e) {
					e.printStackTrace();
				}
	//      }
    //	});
	}

	/**
	 * Create the frame.
	 */
	public JFrmPrincipal() {
		//setAlwaysOnTop(true);
		//setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				jLblAutor.setText("  Autor : Fix Invest    ");
		        jLblData.setText(" Data : " +
		        DateFormat.getDateInstance().format(new Date())+ "                        ");
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
			
			}
		});
		titulo = "Sistema Fix Invest";
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		centralizarTitulo();
		//setTitle("                                                                                      " + titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 865, 466);
		jContentPane = new JPanel();
		jContentPane.setBackground(Color.LIGHT_GRAY);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JToolBar jTolBInfo = new JToolBar();
		jTolBInfo.setForeground(Color.WHITE);
		jTolBInfo.setBounds(0, 403, 973, 24);
		jTolBInfo.setBackground(Color.GRAY);
		jTolBInfo.setRollover(true);
		jTolBInfo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jTolBInfo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTolBInfo);
		
		jLblAutor = new JLabel("");
		jLblAutor.setForeground(Color.WHITE);
		jTolBInfo.add(jLblAutor);
		jLblAutor.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		
		jSepAutor = new JSeparator();
		jSepAutor.setBorder(null);
		jSepAutor.setOrientation(SwingConstants.VERTICAL);
		jSepAutor.setForeground(Color.WHITE);
		jTolBInfo.add(jSepAutor);
		
		jLblData = new JLabel("");
		jLblData.setForeground(Color.WHITE);
		jLblData.setVerticalAlignment(SwingConstants.TOP);
		jLblData.setHorizontalTextPosition(SwingConstants.LEFT);
		jLblData.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTolBInfo.add(jLblData);
		
		jSepHora = new JSeparator();
		jSepHora.setOrientation(SwingConstants.VERTICAL);
		jSepHora.setForeground(Color.WHITE);
		jTolBInfo.add(jSepHora);
		
		jLblHora = new JLabel("");
		jLblHora.setForeground(Color.WHITE);
		jLblHora.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTolBInfo.add(jLblHora);
		
		jMnBPrincipal = new JMenuBar();
		jMnBPrincipal.setBounds(0, 0, 958, 24);
		jMnBPrincipal.setBackground(Color.WHITE);
		jMnBPrincipal.setForeground(Color.BLACK);
		jMnBPrincipal.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 11));
		jContentPane.add(jMnBPrincipal);
		
		JMenu jMnArquivo = new JMenu("Arquivo");
		jMnArquivo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnArquivo.setHorizontalAlignment(SwingConstants.CENTER);
		jMnBPrincipal.add(jMnArquivo);
		
		jMnISair = new JMenuItem("Sair");
		jMnISair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja finalizar", titulo))
					System.exit(0);
			}
		});
		jMnISair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		
		jSepSair = new JSeparator();
		jSepSair.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnArquivo.add(jSepSair);
		jMnISair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnISair.setHorizontalAlignment(SwingConstants.CENTER);
		jMnArquivo.add(jMnISair);
		
		JMenu jMnCadastrar = new JMenu("Cadastrar");
		jMnCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		jMnCadastrar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnBPrincipal.add(jMnCadastrar);
		
		JSeparator jSepAnimal = new JSeparator();
		jSepAnimal.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepAnimal);
		
		JMenuItem jMnICliente = new JMenuItem("Cliente");
		jMnICliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmCliente frame = new JFrmCliente();
				frame.setVisible(true);
			}
		});
		jMnICliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		jMnICliente.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnICliente.setHorizontalAlignment(SwingConstants.CENTER);
		jMnCadastrar.add(jMnICliente);
		
		JSeparator jSepFuncionario = new JSeparator();
		jSepFuncionario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepFuncionario);
		
		JMenuItem jMnIAdministrador = new JMenuItem("Administrador");
		jMnIAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmAdministrador frame = new JFrmAdministrador();
				frame.setVisible(true);
			}
		});
		jMnIAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIAdministrador.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		jMnIAdministrador.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIAdministrador);
		
		JSeparator jSepServico = new JSeparator();
		jSepServico.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepServico);
		
		JMenuItem jMnILivro = new JMenuItem("Empresa");
		jMnILivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrmEmpresa frame = new JFrmEmpresa();
				frame.setVisible(true);
			}
		});
		jMnILivro.setHorizontalAlignment(SwingConstants.CENTER);
		jMnILivro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		jMnILivro.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnILivro);
		
		JSeparator jSepExecuta1 = new JSeparator();
		jSepExecuta1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepExecuta1);
		
		JSeparator JSepExecuta2 = new JSeparator();
		JSepExecuta2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(JSepExecuta2);
		
		JMenuItem jMnIEmprestimo = new JMenuItem("Investimento");
		jMnIEmprestimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrmInvestimento frame = new JFrmInvestimento();
				frame.setVisible(true);
			}
		});
		jMnIEmprestimo.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIEmprestimo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		jMnIEmprestimo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIEmprestimo);
		
		JMenu jMnSistema = new JMenu("Sistema");
		jMnSistema.setHorizontalAlignment(SwingConstants.CENTER);
		jMnSistema.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnBPrincipal.add(jMnSistema);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnSistema.add(separator_1);
		
		JMenuItem mntmManuteno = new JMenuItem("Diagrama de Classe");
		mntmManuteno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
	                    Desktop desktop = Desktop.getDesktop();
	                    URI uri = new URI("https://prnt.sc/PN_LlZ_NrAI2");
	                    desktop.browse(uri);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }			}
		});
		mntmManuteno.setHorizontalAlignment(SwingConstants.CENTER);
		mntmManuteno.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jMnSistema.add(mntmManuteno);
		setResizable(false);		
		setLocationRelativeTo(null);
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
            UIManager.put("OptionPane.messageForeground", Color.BLUE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	
	private void centralizarTitulo() {
	    FontMetrics fM = getFontMetrics(getFont());
        int sC = (getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / fM.stringWidth(" ");
        setTitle(String.format("%" + (sC + 130 ) + "s", "") + titulo.trim());
    }
	
	public void relogio() {
		  try {
	    	  Thread.sleep(1000);
	    	  while(true)  
	    		   jLblHora.setText
	              ("Hora : " + DateFormat.getTimeInstance().format(new Date().getTime()) + "                  ");		 
	     } 
	     catch (InterruptedException e){
	         Diversos.mostrarDados("Problema ao acionar a hora " + e.getMessage(), titulo, false);  
	       }
	}	
}
