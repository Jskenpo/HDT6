/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hdt6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jsken
 */
public class Controlador{
    FileReader leer;
    FileWriter escribir;
    PrintWriter linea;
    BufferedReader almacenamiento;

    public IMap<String, List<String>> getCollection() {
        return collection;
    }

    public IMap<String, List<String>> getInventary() {
        return inventary;
    }
    
    
    
    
    BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
    
    private FactoryMap factoryMap = new FactoryMap();
    private IMap<String, List<String>> collection;
    private IMap<String, List<String>> inventary;
    private List<String> categories = new ArrayList<String>();
    
    
    public void newOperation(File datos) throws IOException{
        //método para ingresar un nuevo producto en caso de que no exista ninguno
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        escribir= new FileWriter(datos,true);
        linea = new PrintWriter(escribir);
        
        System.out.println("Ingrese un producto y su clasificación");
        String cadena= br.readLine();
        
        
        linea.println(cadena);
       
        linea.close();
        escribir.close();
        
    }
    
     public void Factory() throws IOException{
        

      
        System.out.println("Ingrese tipo de mapa a implementar: ");
        System.out.println("1. HashMAP");
        System.out.println("2. TreeMAP");
        System.out.println("3. LinkedHashMap");
        Integer optionMAP = Integer.parseInt(br.readLine());

     
        collection = factoryMap.getMap(optionMAP);
        inventary = factoryMap.getMap(optionMAP);
        
    }
    
    public void leerOperaciones(File Datos) throws IOException{
        //método para leer todas los productos dentro del archivo
        String caracter="",cadena;
        int cont=0;
        Datos = new File("ListadoProducto.txt");
        leer = new FileReader(Datos);
        almacenamiento = new BufferedReader(leer);
        
         while ((caracter=almacenamiento.readLine()) != null){
             
             cadena=caracter;
             String[] products = caracter.replace("|", ",").split(",");
                String category = products[0].trim().toUpperCase();
                String product = products[1].trim();

                
                if (inventary.get(category) != null) { 
                    
                    List<String> currentList = inventary.get(category);
                    currentList.add(product);
                    inventary.put(category, currentList);
                    
                } else { 
                    
                    List<String> newList = new ArrayList<String>();
                    newList.add(product);
                    inventary.put(category, newList);
                    categories.add(category);
                    
                }
             
         }
        
    }
    
    
   
    
    public void addProductToCollection() throws IOException {

        Boolean Terminar = true;

        while (Terminar) {

            System.out.println("Agregar productos a la colección");

            System.out.print("\n-> Ingrese el nombre de una categoría, si desea salir pulse la tecla <<s>>: ");
            String category = br.readLine().toUpperCase();

            if (category.equals("s")) {
                Terminar = false;
                break;

            } else {

                List<String> products = inventary.get(category);

                if (products == null) {
                    System.out.println("[!] La categoria que desea ingresar no existe");
                } else {

                    for (int i = 0; i < products.size(); i++) {
                        System.out.println((i + 1) + ". " + products.get(i));
                    }

                    System.out.print("\n-> Ingrese el numero de un producto ");
                    int productIndex = Integer.parseInt(br.readLine());
                    productIndex = productIndex - 1;

                    if (productIndex > products.size() || productIndex < 1) {
                        System.out.println("\n[!] No existe ese producto");
                    } else {

                        // Revisa si existe la categoria, si no existe la creo
                        if (collection.get(category) != null) {

                            List<String> currentList = collection.get(category);
                            currentList.add(products.get(productIndex));
                            collection.put(category, currentList);

                        } else {
                            List<String> newList = new ArrayList<String>();
                            newList.add(products.get(productIndex));
                            collection.put(category, newList);
                        }

                        System.out.println("\n[OK] Producto agregado exitosamente");

                    }

                }

            }
        }

    }
    
    
    public void showIMAP(IMap<String, List<String>> tempList, Boolean isSort) {

        if (isSort) {
            Collections.sort(categories);
        }

        for (int i = 0; i < categories.size(); i++) {

            List<String> products = tempList.get(categories.get(i));

            if (products != null) {
                System.out.println("\n-> " + categories.get(i));

                for (int e = 0; e < products.size(); e++) {
                    System.out.println("  - " + products.get(e) + "| 1");
                }

            }
        }

      

    }

    public void showCategoryOfAProduct() throws IOException {
        System.out.println("\n Categoría de un producto\n");

        System.out.print("-> Ingrese el nombre de un producto: ");
        String product = br.readLine();

        String tempCategory = "";

        for (int i = 0; i < categories.size(); i++) {

            List<String> products = inventary.get(categories.get(i));

            for (int e = 0; e < products.size(); e++) {
                if (products.get(e).equals(product)) {
                    tempCategory = categories.get(i);
                }
            }

        }

        System.out.println("\n [OK] El producto ingresado pertence a la categoria: ");
        System.out.println("         -> " + tempCategory + "\n");
        

    }

    
    
    
    
}
