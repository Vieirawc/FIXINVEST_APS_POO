
package Util;

import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Diversos {
   
  public static String leDados(String mensagem, String titulo){
      String dado="";
      do{
       dado=JOptionPane.showInputDialog(null,mensagem,titulo,JOptionPane.QUESTION_MESSAGE);
      }
      while(dado==null||dado.isEmpty()); 
      dado=dado.replace(",",".");
      return(dado);
  }
  
  public static void mostrarDados(String mensagem, String titulo, boolean icone) {
     int num ;
     num =  (icone) // == true 
    		 ? JOptionPane.INFORMATION_MESSAGE : 
    	                                                 JOptionPane.ERROR_MESSAGE;
     JOptionPane.showMessageDialog(null, mensagem, titulo, num);		
  }
  
  public static boolean testaNum(String dado,String titulo) {
      boolean teste = true;
	
      try{
           Double.parseDouble(dado); 
      }
      catch(NumberFormatException erro) {
	   mostrarDados("Houve erro na convers�o\n"+
           "Digite apenas caracteres num�ricos" + erro.getMessage(), 
                                                                           titulo, false);
            teste = false;
      } //Fim try_catch
      return (teste);
  } //Fim do testaNum

  public static boolean confirmar (String mensagem, String titulo) {
      return(JOptionPane.showConfirmDialog(null, mensagem,
                  titulo, JOptionPane.YES_NO_OPTION) == 0);     
  }
  
  public static boolean intervalo(double n, double n1, double n2,String titulo) {
     if (n < 0) {
        mostrarDados("Favor digitar valores superiores a zero", titulo, false);
        return (false) ;
     } else if (n1 != n2 && (n < n1 || n > n2)) {
                  mostrarDados("Valores fora do intervalo de " + n1 + " e " + n2,
                		                             titulo, false);
	              return(false);
              } else 
     return (true);
  }

   public static DefaultFormatterFactory FormatoMascara(String titulo, int op) {
        MaskFormatter mascara = null;
        try {
            switch(op) {
                case 0 : mascara = new MaskFormatter("##/##/####");
                              break;
                case 1 : mascara = new MaskFormatter("##:##");
                              break;
                case 2 : mascara = new MaskFormatter("(##) #####-####");   
                			  break;
                case 3 : mascara = new MaskFormatter("###.###.###-##");   
                			  break;
                case 4 : mascara = new MaskFormatter("#####-###");   
                			  break;
            }
        }
        catch (ParseException e){
            mostrarDados("Erro na formata��o " + e.getMessage(), titulo, 
            		                                                                                  false);
        }
        DefaultFormatterFactory formato = 
        		new DefaultFormatterFactory(mascara, mascara);
        return (formato);
   }

   public static NumberFormat doisDigitos(int tipo) {
       NumberFormat doisDigitos = null;
        switch (tipo) {
            case 0 : doisDigitos = NumberFormat.getNumberInstance(); //n�mero
                          break;
            case 1 : doisDigitos = NumberFormat.getCurrencyInstance(); //valor
                          break;
            case 2 : doisDigitos = NumberFormat.getPercentInstance();
        }
        doisDigitos.setMinimumFractionDigits(2);
        doisDigitos.setMaximumFractionDigits(2);
        return doisDigitos;  
   } 
   
   public static String converterValor(String numero) {
       //numero = numero.replace(".", ".");
	  // numero = numero.replace(',', '.');
	   //numero = numero.replace("R$", "");
     //  numero = numero.replace("%", "");
      // numero = numero.trim();
     //  numero = numero.replaceAll("[^0-9.]", "");  
	   numero = numero.replaceAll("[^0-9.,]", ""); // Mantém números, pontos e vírgulas
	   numero = numero.replaceAll("\\.", "");
	   numero = numero.replace(",", "."); // Substitui vírgulas por pontos
       return numero;
    }  

 } //Fim da classe diversos
