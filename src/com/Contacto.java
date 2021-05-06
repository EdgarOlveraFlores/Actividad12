
package com;

public class Contacto {
    private String nombre;
    private int telefono;

    
    //Constructor para recibir los valores, nombre y telefono
    public Contacto(String nombre, int telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public Contacto(String nombre) { //constructor para recibir solo el nombre y hacer busquedas
        this.nombre = nombre;
        this.telefono = 0;
    }

    //Metodos get y set de las variable privadas de la clase Contacto
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    
    //Metodo para verificar si el contacto ya existe
    public boolean iguales(Contacto c){
        if (this.nombre.equalsIgnoreCase(c.getNombre())) {  //Funcion para ignorar mayusculas
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Contacto{" + " nombre=" + nombre + ", telefono=" + telefono + '}';
    }
    
 
    
}
