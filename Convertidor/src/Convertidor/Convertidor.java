
package Convertidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Convertidor {

   
    public static void main(String[] args) {
        
        
         String ruta;
        ruta = new File ("").getAbsolutePath (); //ruta inicial del directorio 
        ruta = ruta+"\\Texto\\Texto.txt";        // direccion del archivo 
        
        //System.out.print(ruta); //Mostrara la ruta donde esta ubicado

        //Lee por primera vez el Archivo 
        try{
                FileReader fr = new FileReader(ruta);       //abre el archivo 
                BufferedReader br = new BufferedReader(fr); //bufer de lectura
                
                String cadena;                              //variable que almacenara la cadena 
                int i=0;                                    // Linea 
                String resultado = "";                      //variable que almacena los resultados de las etiquetas
                int countErrors = 0;                        // variable de contador de errores
                
                while((cadena=br.readLine())!=null){        //trae las lineas del documento
                    // System.out.println(i+". "+cadena);         //muestra la cadena 
                     

                          //expresiones regulares  
                          Pattern pat1 = Pattern.compile("[$]\\w+[$]");     //etiquetas como head o body  
                          Matcher mat1 = pat1.matcher(cadena);
                          Pattern pat2 = Pattern.compile("[$]/\\w+[$]");     //etiquetas como /head o /body  
                          Matcher mat2 = pat2.matcher(cadena);                        
                          Pattern pat3 = Pattern.compile("[$]\\w+[$][\\w\\s]+[$]/\\w+[$]");    // etiquetas con contenido entre ellas como <p>texto<p>
                          Matcher mat3 = pat3.matcher(cadena);
                          
    //---------------------------------------------etiquetas de encabezado o cuerpo HEAD,BODY-------------------------------------------------
                          if(mat1.matches()){     //etiquetas de encabezado o cuerpo HEAD,BODY
                                  // System.out.println("enabezado"); 
                                 //etiquetas a convertir y posble error
                            
                              String[] parts1 = cadena.split("[$]");
                              String partA1 = parts1[0];  
                              String partA2 = parts1[1]; // etiqueta
                            
                                //System.out.println(partA2);
                            
                                //lista de posibles etiquetas
                                
                                //HTML
                                 if(partA2.equals("LenguajeH")){
                                     System.out.println("<HTML>");
                                     resultado += "<HTML>";
                                 }
                                 
                                //HEAD
                                 else if(partA2.equals("CabezaH")){
                                     System.out.println("<HEAD>");
                                     resultado += "<HEAD>";
                                 }
                                 
                                 //BODY
                                 else if(partA2.equals("CuerpoH")){
                                     System.out.println("<BODY>");
                                     resultado += "<BODY>";
                                 }
                                 
                                 else{ //proboca que el programa pare y muester un mensaje de error
                                     System.out.println("error esta etiqueta no existe "+cadena+" posible error en linea: "+i);
                                     countErrors += 1;
                                     break;
                                 }
                 
                            }
                            
                          
    //----------------------------------------------Etiquetas que finalizan como </HEAD>---------------------------------------------
                              if(mat2.matches()){     //etiquetas de encabezado o cuerpo HEAD,BODY
                                  // System.out.println("enabezado"); 
                                 //etiquetas a convertir y posble error
                            
                            String[] parts1 = cadena.split("[$]");
                            String partA1 = parts1[0];  
                            String partA2 = parts1[1]; // etiqueta
                            
                               // System.out.println(partA2);
                            
                                //lista de posibles etiquetas
                                
                                //HTML
                                 if(partA2.equals("/LenguajeH")){
                                     System.out.println("</HTML>");
                                     resultado += "</HTML>";
                                 }
                                                                
                                //HEAD
                                 
                                 else if(partA2.equals("/CabezaH")){
                                     System.out.println("</HEAD>");
                                     resultado += "</HEAD>";
                                 }
                                 
                                 
                                 else if(partA2.equals("/CuerpoH")){
                                     System.out.println("</BODY>");
                                     resultado += "</BODY>";
                                 }
                                 
                                 
                                 else{//proboca que el programa pare y muester un mensaje de error
                                     System.out.println("esta etiqueta no existe "+cadena+" posible error en linea: "+i);
                                     countErrors += 1;
                                     break;
                                 }
                 
                            }
                          
    
        //----------------------etiquetas compuestas como <p>texto</p>-----------------------------------------------------
                              
                            else if(mat3.matches()){ //etiquetas compuestas como <p>texto</p>
                         
                            // Cadena a seperar part
                            String[] parts3 = cadena.split("[$]");
                            String partC1 = parts3[0];  
                            String partC2 = parts3[1]; // etiqueta
                            String partC3 = parts3[2]; // comentario
                            String partC4 = parts3[3]; // fin etiqueta

                        //    System.out.println(part1);
                        //    System.out.println(part2);
                        //    System.out.println(part3);
                        //    System.out.println(part4);
                          
                        //convertidor de etiquetas
                              //TITTLE
                                if((partC2.equals("TituloH"))){
                                     if((partC4.equals("/TituloH"))){
                                           System.out.println("<TITLE>"+partC3+"</TITLE>");
                                           resultado += "<TITLE>"+partC3+"</TITLE>";
                                     }  
                                     else{
                                         System.out.println("final de la expresion " +cadena +" incorrecta linea: "+i);
                                         countErrors += 1;
                                         break;
                                     }
                                }
                                
                              //H1
                                else if((partC2.equals("Titulo1H"))){
                                     if((partC4.equals("/Titulo1H"))){
                                           System.out.println("<h1>"+partC3+"</h1>");
                                           resultado += "<h1>"+partC3+"</h1>";
                                     }  
                                     else{
                                         System.out.println("final de la expresion " +cadena +" incorrecta linea: "+i);
                                         countErrors += 1;
                                         break;
                                     }
                                }
                              //H2
                                else if((partC2.equals("Titulo2H"))){
                                     if((partC4.equals("/Titulo2H"))){
                                           System.out.println("<h2>"+partC3+"</h2>");
                                           resultado += "<h2>"+partC3+"</h2>";
                                     }  
                                     else{
                                         System.out.println("final de la expresion " +cadena +" incorrecta linea: "+i);
                                         countErrors += 1;
                                         break;
                                     }
                                }
                              
                              //p
                                else if((partC2.equals("Parrafo"))){
                                     if((partC4.equals("/Parrafo"))){
                                           System.out.println("<p>"+partC3+"</p>");
                                           resultado += "<p>"+partC3+"</p>";
                                     }  
                                     else{
                                         System.out.println("final de la expresion " +cadena +" incorrecta linea: "+i);
                                         countErrors += 1;
                                         break;
                                     }
                                }
                                
                                
                                
                                else{
                                    System.out.print("la etiqueta: "+ cadena +"no existe error en la linea: "+i);
                                    countErrors += 1;
                                    break;
                                }
                                
                                
                                
                            }else{
                               // System.out.println("NO");
                            }
                             
                   i++;  //contador
                     
                }
            
            System.out.println(resultado);
            
            // Condicional IF que crea el archivo HTML, si no existen errores
            if(countErrors >= 1) {
                String rutanewFile;
                rutanewFile = new File ("").getAbsolutePath (); //ruta inicial del directorio 
                rutanewFile = rutanewFile+"\\Texto\\filename.html";
                String contenido = resultado;
                File file = new File(rutanewFile);
                // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contenido);
                bw.close();
            }
        }catch (Exception ex){}
       
        
        
        
        /*
        
   
       String cadena ="$h$ hola como esta $/h$";
        
        Pattern pat = Pattern.compile("[$]h[$][\\w\\s]+[$]/h[$]");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()){
            System.out.println("SI");
            
            
        String[] parts = cadena.split("[$]");
        String part1 = parts[0]; // h
        String part2 = parts[1]; // comentario
        String part3 = parts[2]; // cierre
        String part4 = parts[3];
        
        System.out.println(part1);
        System.out.println(part2);
        System.out.println(part3);
        System.out.println(part4);
            
            
        
        }else{
            System.out.println("NO");
        }
        
        
        */
        
        
        
        /*String cadena ="$h$ hola como esta $/h$";
        
        Pattern pat = Pattern.compile("[$]h[$][\\w\\s]+[$]/h[$]");
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()){
            System.out.println("SI");
            
        
        }else{
            System.out.println("NO");
        }
        */
        
      /*  
        String string = "$h$ hola como esta $/h$";
        String[] parts = string.split("[$]");
        String part1 = parts[0]; // h
        String part2 = parts[1]; // comentario
        String part3 = parts[2]; // cierre
        String part4 = parts[3];
        
        System.out.println(part1);
        System.out.println(part2);
        System.out.println(part3);
        System.out.println(part4);
        */
        
    }
    
    
}
