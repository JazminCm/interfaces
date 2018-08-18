/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosUtileria;

import acciones.Capturista;
import acciones.Capturista.onCapturistaAction;
import acciones.Consultor;
import acciones.Consultor.onConsultorInterface;
import java.util.Scanner;

public class Secretaria implements onConsultorInterface,onCapturistaAction {

    Consultor consultor;
    Capturista capturista;
    
    public Secretaria(){
        consultor=new Consultor(this);
        capturista=new Capturista(this);
        boolean cond=true;
        while(cond){
            System.out.println("1) Crear estudiante \n"
                    + "2) Buscar estudiante \n"
                    + "3) Modificar estudiante \n"
                    + "4) Eliminar estudiante \n"
                    + "5) Salir \n");
            Scanner scanner=new Scanner(System.in);
            int entrada=scanner.nextInt();
            switch(entrada){
                case 1:
                    capturista.capturar();
                    break;
                case 2:
                    capturista.ingresoDeNombre();
                    break;
                case 3:
                    capturista.ingresadoParaModificar();
                    break;
                case 4:
                    capturista.ingresadoParaBorrar();
                    break;
                case 5:
                    cond=false;
                    break;
            }
        }
    }

    @Override
    public void onConsulta() {
        
    }
    
    @Override
    public void onEstudianteParaActualizar(String data){
    
    }

    @Override
    public void onError() {
        System.out.println("El estudiante no fue encontrado o fue eliminado");
    }

    @Override
    public void onCreateEstudiante() {
        System.out.println("---->Tu estudiante ya fue ingresado en la lista");
    }

    @Override
    public void onCaptura() {
        System.out.println("---->Inicia procedimiento de creacion de estudiante");
    }

    @Override
    public void onErrorOnCaptura() {
        
    }

    @Override
    public void onCapturaTerminada(Estudiante estudiante) {
        consultor.agregarEstudianteALista(estudiante);
    }

    @Override
    public void onNombreABuscarIngresado(String ingresado) {
        System.out.println("----->Nombre ingresado");
        consultor.buscar(ingresado);
    }

    @Override
    public void onEstudianteEncontrado(Estudiante estudiante) {
        System.out.println("Nombre encontrado: "+estudiante.getNombre()+" "+estudiante.getPaterno()+" "+estudiante.getMaterno());
    }

    @Override
    public void onIngresadoParaBorrar(String data) {
        System.out.println("Nombre a borrar: "+ data);
        consultor.eliminar(data);
    }
    @Override
    public void onIngresadoParaActualizar(String data) {
        boolean encontrado = consultor.buscar(data);
        if (encontrado) {
            capturista.capturaModificar(data);
        }
    }
    @Override
    public void onEstudianteBorrado(Estudiante data) {
        System.out.println("Estudiante borrado fue ->"+data.getNombre()+ " "+data.getPaterno()+ " "+ data.getMaterno());
        consultor.muestra();
    }
    
   
   @Override
    public void onCapturaTerminadaActualizar(String nombren,String paternon,String maternon,String nombrev) {
        System.out.println("Estudiante se ha actualizado ->");
        consultor.actualizar(nombren,paternon,maternon,nombrev);
    }
    
    @Override
    public void onActualizacionTerminada(Estudiante estudiante) {
        System.out.println("Estudiante se ha actualizado correctamente con los siguientes datos: -> "+estudiante.getNombre()+" "+
                estudiante.getMaterno()+" "+estudiante.getPaterno());
    }
    
}
