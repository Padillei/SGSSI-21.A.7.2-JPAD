package Prueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HASH {
	public static void main(String args[]) throws IOException, NoSuchAlgorithmException
    {
        File archivo = new File("C:/Julen/Descargas/SGSSI-21.CB.03.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("C:/Julen/Descargas/NuevoFichero.txt");
        BufferedWriter out = new BufferedWriter(fw);
        boolean otro=false;
        int maxNumeroCeros=0;
        String hexadecimalFinal="", hashFinal="";
        
        
        String linea = "";
        String texto = "";
        while((linea=br.readLine())!=null)
        {
            out.write(linea);
            out.newLine();
            texto+=linea;
        }
        
        String textoViejo = texto;

        long start = System.currentTimeMillis();
        long end = start + 60*1000;
        
        while(System.currentTimeMillis() < end)
        {
            otro=false;
        texto=textoViejo;
        String hexadecimal = "";
        Random r = new Random();
        StringBuffer sb1 = new StringBuffer();
        
        while(sb1.length() < 8){
            sb1.append(Integer.toHexString(r.nextInt()));
        }
        hexadecimal = sb1.toString().substring(0, 8);
             
        texto+=hexadecimal;
    
        MessageDigest md = null;
        md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(texto.getBytes());
        
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {        
            sb.append(String.format("%02x", b));
        }
        
        String resultado=sb.toString();
        
        int numeroCeros=0;
        String cero="0";
        
        for(int i=0; i<resultado.length(); i++)
        {
                if(resultado.charAt(i)==cero.charAt(0))
                {
                    numeroCeros++;
                }
                else
                {
                    break;
                }    
        }
        
        if(numeroCeros>maxNumeroCeros)
        {
            maxNumeroCeros=numeroCeros;
            hashFinal=resultado;
            hexadecimalFinal=hexadecimal;
        }
        }
        hexadecimalFinal+=" G0232";
        out.write(hexadecimalFinal);
        out.newLine();
        out.write(hashFinal);
        out.close();
        

        
        
    }
}
