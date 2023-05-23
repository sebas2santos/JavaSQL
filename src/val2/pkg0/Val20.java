/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package val2.pkg0;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author El rey.
 */
public class Val20 {

    //Declaracion de variables usadas en todo momento.    	
    String URL = "jdbc:mysql://localhost:3306/"; //URl del localhost y el puerto donde se encotrara la base de datos.
    String driver="com.mysql.cj.jdbc.Driver"; //Driver que nos ayudara a reaizar la conexion con la  bbdd.
    String USER = "root"; //Usuario de la base de datos(si no es el usuario cambiarlo o saldra error.).
    String CLAVE = ""; // Clave de la  base de datos que sera pedia posteriormente.
    Connection con; //Varible de tipo Connection que nos  ayudara en todas las funciones
    String bd = "libros"; //Nombre de la  base de datos.



    public Val20() { //Contructor para evitar errores en el main.
    }
    
    public Connection conexion(String CLAVE){//Esta funcion es donde se realiza la conexiona a travez del sqlconecctor 
        // o jdbc como se prefiera llamar.
        try {
        //A continuacion se muestra como se realiza la conexion con la basse de datos
        Class.forName(driver);
        con=DriverManager.getConnection(URL+bd, USER, CLAVE);
        
         System.out.println("Si se pudo conectar.");
        } catch (ClassNotFoundException |SQLException ex) {
        //En el caso de haber un error en la conexion nos mostrara los mitovos de porque no se pudo conectar.
        Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("nop");
        
        }
        return con; // retornamos la conexion para que se puede usar en los otros procesos.
    }
    
    public void descon(String CLAVE){ // Este proceso muestra como s hace la desconexion la bbdd
        //y asi evitar el sobreconsumo dde recursos.
        try{
            con.close();
            
        }catch(SQLException ex) { //En el caso de no poderse desconectar se mostrara el error.
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearbbdd(String CLAVE){ //Funcion para crear la base de datos.
        try {
            //Realizamos el Drivemanager ya que sin el  no se puede crear el Statement
             con = DriverManager.getConnection(URL, USER, CLAVE);
            
            //Creamos un Statement que nos ayudara ejecutar los comandos SQL.
            Statement stmt = con.createStatement();
            
            //Aqui colocamos la ordden en lenguaje SQL para que lo ejecute el Statement.
            stmt.execute("CREATE  DATABASE libros");//Comando para crear la base de datos.

            System.out.println("Se creo la base de datos correctamente.");
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
            
        } catch (SQLException ex) {
            // Manejar errores de SQL y mostrarlos.
            ex.printStackTrace();
        }
    }
    
    public void borrarbbdd(String CLAVE){//Funcion para el borrado de la base de datos si ya existe.
        try {
            //Realizamos el Drivemanager ya que sin el  no se puede crear el Statement
            con = DriverManager.getConnection(URL, USER, CLAVE);

            //Aqui colocamos la ordden en lenguaje SQL para que lo ejecute el Statement.
            Statement stmt = con.createStatement();

            //Creamos un String con la orden en SQl es otra manera de ejecutarla.
            String query = "DROP DATABASE IF EXISTS libros";//Comando para borrar la base de datos si existe.
            stmt.executeUpdate(query);//Ejecucion del comando anteriormente mencionado.

            System.out.println("Base de de datos borrada con exito.");
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
            
        } catch (SQLException ex) {
            // Manejar errores de SQL y mostrarlos.
            ex.printStackTrace();
        }
    }
    
    public void creartablalibros(String CLAVE){//Funcion de creacion de tablas
        try {
            /* A partir de  este punto  como se repite el primer bloque se comentara por encima. */
            //Creacion del Statement.
            con=DriverManager.getConnection(URL+bd, USER, CLAVE);
            Statement stmt = con.createStatement();
        
            //Ejecuccion de la sentencia SQL.
            String query = "CREATE TABLE IF NOT EXISTS Libros  (id INT AUTO_INCREMENT PRIMARY KEY, titulo VARCHAR(255), autor int ,pais VARCHAR(255), paginas int , genero VARCHAR(255) )";
            stmt.executeUpdate(query);//Creacion de la tabla con todos sus atributos.
        
            System.out.println("Tabla libros creada.");
    
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
            
        } catch (SQLException ex) {
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex); //Impresion de errores.
        
        }

    }
    
    public void creartablautores(String CLAVE){//Funcion para crear tabla autores
        try {
            //Creacion del Statement.
            con=DriverManager.getConnection(URL+bd, USER, CLAVE);
            Statement stmt = con.createStatement();
        
            //Ejecuccion de la sentencia SQL.
            String query = "CREATE TABLE if not exists Autores  (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), apellido VARCHAR(255) )";
            stmt.executeUpdate(query);//Misma sentencia para crear tabla  con sus atributos.
    
            System.out.println("tabla creada");
    
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
            
        } catch (SQLException ex) {
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex);//Impresion de errores.
        }

    }
    
    public void insertarautores(String CLAVE){ // Funcion para insertar autores en la tabla.
        try {
            //Creacion del Statement.
            con=DriverManager.getConnection(URL+bd, USER, CLAVE);
            Statement stmt = con.createStatement();
        
            //Ejecuccion de la sentencia SQL.
            String query = "INSERT INTO autores (nombre, apellido) VALUES ('enzo', 'perez')";
            stmt.executeUpdate(query);//Introduccion del primer autor.
            String query2 = "INSERT INTO autores (nombre, apellido) VALUES ('sebastian', 'villa')";
            stmt.executeUpdate(query2);//Introduccion del egundo autor.
        
            System.out.println("autores insertados");
    
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex); //Impresion de errores.
        
        }
    
    }
    
    public void asignarlibros(String CLAVE){ //Funcion para la asignacion de libros.
        try {
            //Creacion del Statement.
            con=DriverManager.getConnection(URL+bd, USER, CLAVE);
            Statement stmt = con.createStatement();
        
            //Ejecuccion de una sentencia SQL extra.
            String alter = "alter table libros add constraint ff FOREIGN KEY (autor) REFERENCES autores(id);";
            stmt.executeUpdate(alter);//Esta senctencia realiza la funcion de relacionar los autoorescon los
            //lbros y en el caso de cambiar algo en los autores tambien cambiar en el llibro.
        
            //Ejecuccion de la sentencia SQL.
            String query2 = "INSERT INTO `libros`.`libros` (`titulo`, `autor`, `pais`, `paginas`, `genero`) VALUES ('river', '2', 'escocia', '23', 'miedo')";
            stmt.executeUpdate(query2);
            String query3 = "INSERT INTO `libros`.`libros` (`titulo`, `autor`, `pais`, `paginas`, `genero`) VALUES ('boca', '2', 'argentina', 95, 'deportes')";
            stmt.executeUpdate(query3);
            String query4 = "INSERT INTO `libros`.`libros` (`titulo`, `autor`, `pais`, `paginas`, `genero`) VALUES ('river','1', 'argentina', 95, 'deportes')";
            stmt.executeUpdate(query4);
            String query5 = "INSERT INTO `libros`.`libros` (`titulo`, `autor`, `pais`, `paginas`, `genero`) VALUES ('penal','1', 'argentina', 95, 'fraude')";
            stmt.executeUpdate(query5);
            //Insertar todos los libros para todos sus autores.
        
            System.out.println("Libros insertado");
            
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
            
        } catch (SQLException ex) {
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex); //Impresion de errores.
        
        }
    }
    
    public void Mostrarautores(String CLAVE){
        
        try {
            //Creacion del Statement.
            con=DriverManager.getConnection(URL+bd, USER, CLAVE);
        
            //Sentencia SQL.
            String query = "SELECT * FROM autores";
        
            PreparedStatement stmt = con.prepareStatement(query);//Este tipo de Statement es diferente
            //por medida de seguridad ya que se puede usar comandos malicioso para borrar o alterar de manera indeseada 
            //la base datos.
        
            ResultSet resultSet = stmt.executeQuery();//El resultset se ultiliza para poder acceder a los datos de la bd.
        
            //Impresion de todos los registro de la tabla deseada.
            System.out.println("---------Autores---------");
            while (resultSet.next()) { // Bucle para imprimir registros hasat que no hayan mas.
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
    
                System.out.println(id + " " + nombre + " " + apellido); //Esquema de como se imprimira.
            }
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
        } catch (SQLException ex) {
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex);//Impresion de errores.
        
        }
    }
    
    public void MostrarLibros(String CLAVE){
        
        try {
            //Creacion del Statement.
            con=DriverManager.getConnection(URL+bd, USER, CLAVE);
            //Sentencia SQL.
            String query = "SELECT p.id, titulo, CONCAT(nombre, '  ', apellido) AS Autor, pais, paginas, genero FROM libros p JOIN autores c ON p.autor = c.id";
        
            PreparedStatement stmt = con.prepareStatement(query);//Este tipo de Statement es diferente
            //por medida de seguridad ya que se puede usar comandos malicioso para borrar o alterar de manera indeseada 
            //la base datos.
        
            ResultSet resultSet = stmt.executeQuery();//El resultset se ultiliza para poder acceder a los datos de la bd.
        
            //Impresion de todos los registro de la tabla deseada.
            System.out.println("---------Libros---------");
            System.out.println("id titulo    Autor             pais   paginas   genero");
            while (resultSet.next()) { // Bucle para imprimir registros hasat que no hayan mas.
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String Autor = resultSet.getString("Autor");
                String pais = resultSet.getString("pais");
                int paginas = resultSet.getInt("paginas");
                String genero = resultSet.getString("genero");
    
                System.out.println(id+ "  " + titulo + "    " + Autor + "  " + pais + " " + paginas + "      " + genero);
            }
            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
        } catch (SQLException ex) {
            Logger.getLogger(Val20.class.getName()).log(Level.SEVERE, null, ex);//Impresion de errores.
        }
    }
    
    public void editarAutor(String CLAVE){
        
        Mostrarautores(CLAVE);//Se muestran los autores que hay en base de datos.
        
        try {
            //Creacion del Statement.
             con = DriverManager.getConnection(URL+bd, USER, CLAVE);
    
            // Pedir al usuario que ingrese el id del  autor.
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el id del  autor que quiere cambiar.");
            int id = scanner.nextInt();
    
            //De las siguientesfuciones se extrae si el usuario quiere cambiar el nombre o el apellido
            //y ademas de eso se obtiene el dato por el que se va a cambiar.
            String Nombre=elegiropcion();// Se da eleegir entre nombre y apellido.
            String b = nombre();// Se le pide al usuario  el  nombre/apellido.

            //Sentencia SQL.
            String sql = "UPDATE autores SET "+ Nombre +  " = " + b +  "WHERE id = ?";
    
            PreparedStatement stmt = con.prepareStatement(sql);//Este tipo de Statement es diferente
            //por medida de seguridad ya que se puede usar comandos malicioso para borrar o alterar de manera indeseada 
            //la base datos.

            stmt.setInt(1, id);//Se le da valor al id denro de la consulta.
            stmt.executeUpdate();//Ejecutamos la sentencia.
    
            System.out.println("yes");

            stmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
        } catch (SQLException e) {
            e.printStackTrace();//Impresion de errores.
        }

    }
    public String  elegiropcion(){
        
        Scanner scanner = new Scanner(System.in);
        String nombre;
        
        // Bucle hecho para no acabrse hasta que el usurio elije un opcion correcta.
        while (true) {
            // Mostrar el menú
            System.out.println("Indica que modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("0. Salir");
            
            // Leer la opción elegida por el usuario
            int opcion = scanner.nextInt();
            
            // Verificar la opción elegida
            switch (opcion) {
                case 1:
                    // Se ha elegido la opcion de nombre.              
                    nombre = "nombre";
                    return nombre;
                    
                case 2:
                    // Se ha elegido la opcion apellido.
                    nombre="apellido";
                    return nombre;
                
                case 0:
                    // Salir del programa
                    System.out.println("Ha elegido salir. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                
                default:
                    // Opción inválida
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }      
    }
    public String nombre(){//funcion para pedrile el nombre/pellido al usuario.
        Scanner sn =  new Scanner(System.in);
        String a ;
        a=sn.nextLine(); // se pide a travez de scanner.
        String b = "'"+ a +"'"; // se agregan las comillas para que sea entendible en la colsulta SQL.
        return b;
    }
    
    public void eliminarlibro(String CLAVE){
        
        Scanner sn = new Scanner(System.in);//Declaracion del scanner.
        
        MostrarLibros(CLAVE);//Mostrar todos los libros qu estan la base de datos.
        // Pedir al usuario que ingrese el id del libro a eliminar.
        System.out.println("Ingrese el id del libro que quiere eliminar.");
        int id = sn.nextInt();
        try {
            //Creacion del Statement.
            con = DriverManager.getConnection(URL+bd, USER, CLAVE);
      
            //Sentencia SQL.
            String sql = "DELETE FROM libros WHERE id = ?" ;//Sentencia SQL.
            PreparedStatement pstmt = con.prepareStatement(sql);//Este tipo de Statement es diferente
            //por medida de seguridad ya que se puede usar comandos malicioso para borrar o alterar de manera indeseada 
            //la base datos.
            pstmt.setInt(1, id);//Se le da valor al id denro de la consulta.
            pstmt.executeUpdate();//Ejecutamos la sentencia.
    
            System.out.println("yes");
               
            pstmt.close();//Cerramos el statement para hacer un buen uso de los recursos de nuestro pc.
        } catch (SQLException e) {
            System.out.println("Error!!");//Impresion de errores.
        }
    }
    
    public void reestablecerbd(String CLAVE){ //Funcion para recolpilar todos os procesor que ejecuta la opcion
        //reestablecer la bases de datos.
        
        borrarbbdd(CLAVE);//Borrar si ya existe.
        
        crearbbdd(CLAVE);//Crear la base de datos.
        
        creartablalibros(CLAVE);//Crear tabla libros.
        
        creartablautores(CLAVE);//Crear tabla autores.
        
        insertarautores(CLAVE);//Insertar autores.
        
        asignarlibros(CLAVE);//Inseertar los libros de los autores.
    }
    
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("Ingrese la contraseña de su usuraio root");
        CLAVE = scanner.nextLine();
        conexion( CLAVE);
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Opción 1");
            System.out.println("2. Opción 2");
            System.out.println("3. Opción 3");
            System.out.println("0. Salir");
            
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    reestablecerbd(CLAVE);
                    break;
                case 2:
                    Mostrarautores(CLAVE);
                    break;
                case 3:
                    MostrarLibros(CLAVE);
                    break;
                case 4:
                    editarAutor(CLAVE);
                    break;    
                case 5:
                    eliminarlibro(CLAVE);
                    break; 
                    
                case 0:
                    System.out.println("Saliendo...");
                    descon(CLAVE);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            
            System.out.println();
        } while (opcion != 0);
        
        scanner.close();
    }
    
}
    
    
