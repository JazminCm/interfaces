/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import objetosUtileria.Estudiante;

/**
 *
 * @author rafaelm
 */
public class Consultor {
    
    List<Estudiante> estudiantes;
    onConsultorInterface laSecre;
    
    public Consultor(onConsultorInterface secre){
        this.laSecre=secre;
        estudiantes=new ArrayList<Estudiante>();
    }
    
    
    public void agregarEstudianteALista(Estudiante estudiante){
        estudiantes.add(estudiante);
        laSecre.onCreateEstudiante(); 
    }
    
    public boolean buscar(String nombre){
        int cont = 0, cantidad = estudiantes.size();
        boolean encontrado = false;
      
        for(Estudiante estudianteARevisar:estudiantes){
            System.out.println("Estudiante iterado "+estudianteARevisar.getNombre());
            if(nombre.equals(estudianteARevisar.getNombre())){
                laSecre.onEstudianteEncontrado(estudianteARevisar);
                encontrado = true;
                break;
            }
            cont++;
        }
        if (cont == cantidad) {
            laSecre.onError();
            encontrado = false;
            
        }
        return encontrado;
    }
    
    public void muestra(){
        for(Estudiante estudianteARevisar:estudiantes){
            System.out.println("Estudiante iterado "+estudianteARevisar.getNombre()+" "+estudianteARevisar.getPaterno()+" "+estudianteARevisar.getMaterno());
        }
    }
    public void eliminar(String nombre){
         for(Estudiante estudianteARevisar:estudiantes){
            if(nombre.equals(estudianteARevisar.getNombre())){
                Estudiante estudianteEncontrado = estudianteARevisar;
                estudiantes.remove(estudianteARevisar);
                laSecre.onEstudianteBorrado(estudianteEncontrado);
                break;
           
            }
        }
    }
    
    public void actualizar(String nombren,String paternon,String maternon,String nombrev){
         for(Estudiante estudianteARevisar:estudiantes){
            if(nombrev.equals(estudianteARevisar.getNombre())){
                estudianteARevisar.setNombre(nombren);
                estudianteARevisar.setPaterno(paternon);
                estudianteARevisar.setMaterno(maternon);
                laSecre.onActualizacionTerminada(estudianteARevisar);
                break;
            }
        }
    }
    
    
    
    
    public interface onConsultorInterface{
        public void onConsulta();
        public void onError();
        public void onCreateEstudiante();
        public void onEstudianteEncontrado(Estudiante estudiante);
        public void onEstudianteParaActualizar(String data);
        public void onEstudianteBorrado(Estudiante data);
        
        public void onActualizacionTerminada(Estudiante estudiante);
    }
    
}
