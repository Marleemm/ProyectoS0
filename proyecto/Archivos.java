package proyecto;

import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Archivos {
    public void Archivos(){
  }
  
public void escribir(String ruta,String msg){
  
      try {
          FileWriter file=new FileWriter(ruta,true);
          
          file.write(msg);
          file.close();
      } catch (Exception e) {
          System.out.println("Archivo no encontrado ");
      }
  
  }  
  public String rutaArchivo(){
      String rutaArchivo = "";
   JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto", "txt");
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
             rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

            if (!rutaArchivo.toLowerCase().endsWith(".txt")) {
                rutaArchivo += ".txt";
            }
            
            //escribir(rutaArchivo, informacion);
            JOptionPane.showMessageDialog(null, "Informacion guardada con exito en:" + rutaArchivo);
        } else {
            System.out.println("ERROR no se eligio una ruta ...");
        }
  return rutaArchivo;
  }
    

  
    

}