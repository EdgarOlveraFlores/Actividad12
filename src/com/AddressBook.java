
package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBook {
    //Creamos atributo hash map donde se almacenaran loc contactos
    /*HashMap <String,Object> map = new HashMap<String, Object>();*/
    //Prueba con array 
    private Contacto[] contactos;
    
    
    //Constructores
    public AddressBook (){
        this.contactos=new Contacto[10];   //Definiendo tamaño del arreglo deo default
    }
    public AddressBook (int tamanio){     
        this.contactos=new Contacto[tamanio];    //Definiendo tamaño del arreglo 
    }
    
    //----------------------------------------------------------------------------------
    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        boolean salir = false;
        int opcion;
        try {
            crearDirectorio("c:/Actividad12");      //Al mandar a llamar el metodo crearDirectorio tenemos que capturar o controlar la excepsion con try cath
            crearDirectorio("c:/Actividad12/Ejercicio");      //Creamos un subdirectorio
            creaArchivo("c:/Actividad12/Ejercicio/ArchivoTextoPlanoActividad12");
            System.out.println("\nSe a creado un archivo en tu PC\n");
            muestraInfoArchivo("c:/Actividad12/Ejercicio/ArchivoTextoPlanoActividad12");
            
            AddressBook agendaTelefonica = new AddressBook(5);
            String nombre;
            int telefono;
            Contacto c;

            while (!salir) {
                System.out.println("----------MENU---------------");
                System.out.println("1. Añadir contacto");
                System.out.println("2. Listar contacto");
                System.out.println("3. Existe contacto");
                System.out.println("4. Eliminar contacto");
                System.out.println("5. Salir");
                
                
                System.out.println("Ingresa una opcion: ");
                opcion = entrada.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("Ingresa un nombre: ");
                        nombre = entrada.next();
                        System.out.println("Ingresa un telefono");
                        telefono = entrada.nextInt();
                        
                        c = new Contacto(nombre, telefono);
                        agendaTelefonica.crear(c);  // Creamos el contacto
                        
                        break;
                    case 2:
                        agendaTelefonica.lista();  //Listamos el contacto
                        break;
                    case 3:
                        System.out.println("Ingresa un nombre: ");
                        nombre = entrada.next();
                        c= new Contacto(nombre, 0);
                        
                        if(agendaTelefonica.existeContacto(c)){ //Verificamos si el contacto existe   
                            System.out.println("Existe contacto");
                        }
                        break;
                    case 4:
                        System.out.println("Ingresa un nombre: ");
                        nombre = entrada.next();
                        c= new Contacto(nombre, 0);
                        
                        agendaTelefonica.borrar(c);    //Eliminamos contacto 
                                                
                        break;
                    case 5:
                        
                        break;
                    default:
                        System.out.println("Ingresa un numero del    1 - 6");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //-----------------------------------------------------------------------------------
    
    //Metodos para escritura y lectura
    public void cargar() {
        
    }
    public void guardar() {
        
    }
    
    //Metodos para modificar informacion
    public void lista() { //Metodo para listar contactos
        for (int i = 0; i < contactos.length; i++) {
            if (contactos[i] != null) {
                System.out.println(contactos[i]);
            }
        }
    }
    public void crear(Contacto c) {  //Metodo para agregar nuevo contacto
        
        //Verificamos si existe el contacto
        if (existeContacto(c)) {
            System.out.println("El contacto con ese nombre ya existe");
        }
        boolean encontrado = false;
        for (int i = 0; i<contactos.length && !encontrado ; i++) {
            if (contactos[i]== null) {
                contactos[i] = c;
                encontrado = true;
            }
        }
        if (encontrado) {
            System.out.println("Se a añadido contacto");
        }else{
            System.out.println("NO se a podido añadir");
        }
    }
    
    //Metodo verificar si existe contacto
    public boolean existeContacto(Contacto c) {

        for (int i = 0; i<contactos.length; i++) {
            if (contactos[i]!= null && c.equals(contactos[i])) {
                return true;
            }
        }
        return false;
    }
    
    public void borrar(Contacto c) { //Metodo para eliminar contacto
        
        boolean encontrado=false;
        for (int i = 0; i < contactos.length; i++) {
            if (contactos[i] != null && contactos[i].equals(c)) {
                contactos [i] = null;
                encontrado = true;
            }
        }
        //Indicamos si se ha eliminado 
        if (!encontrado) {
            System.out.println("No se ha eliminado el contacto");
        }
        
    }
    
    //Metodos para cerar directorio donde se almacenara archivo
    static void crearDirectorio(String directorio) throws IOException {
        //Almacenamos directorio en path
        Path dirPath = Paths.get(directorio);
        //Verificamos si ya existe el directorio
        if (Files.notExists(dirPath)) {         //Si no existe la suta que te estoy mandando
            Files.createDirectory(dirPath);     //Este metodo provoca una excepcion de tipo IOExeption
        }
    }    
    //Metodo para crear archivo de texto plano
    
    static void creaArchivo(String rutaArchivo) throws IOException {
        //Almacenamos archivo en ruta de path
        Path dirPath = Paths.get(rutaArchivo);
        //Verificamos si existe la ruta
        if (Files.notExists(dirPath)) {
            Files.createFile(dirPath);   //Este metodo nos provova una excepcion
        }
    }   
    
    //Metodo que muestra informacion del archivo
    static void muestraInfoArchivo(String rutaArchivo) throws IOException{
        Path dirFile = Paths.get(rutaArchivo);
        System.out.println("Nombre del archivo: "+dirFile.getFileName());  //mostramos nombre del archivo
        System.out.println("Ruta absoluta: "+dirFile.toAbsolutePath());  //mostramos ruta del archivo
        System.out.println("Es reescribible?: "+Files.isWritable(dirFile));  //mostramos si el archivo es reescibible
        System.out.println("Tamaño del archivo: "+Files.size(dirFile));  //mostramos tamaño del archivo    Ademas este metodo nos genera una IOException
        
    }
    
}
