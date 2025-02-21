# PARCIAL 1 AREP
Usted debe construir un "Reflective ChatGPT". La solución consta de un servidor backend que responde a solicitudes HTTP POST y/o GET de la Facade, un Servidor Facade que responde a solicitudes HTTP POST y/o GET del cliente  y un cliente Html+JS que envía los comandos y muestra las respuestas. El api permite explorar clases del API de java. Cuando el usuario solicita información de una clase el chat le responde con el nombre de la clase, la lista de los campos declarados en la clase y la lista de los métodos declarados en la clase. Además el API debe permitir invocar y mostrar la salida de métodos estáticos con 0, 1 o 2 parámetros. Los parámetros de entrada pueden ser numéricos o Strings.

## Uso
Se recomienda usar java 17
Se recomienda utilizar FIREFOX como browse

### Ejecución y compilación 

Clone el repositorio e ingrese a el folder resultante en una terminal

- Terminal 1
```bash
    mvn clean compile
    java -cp "target/classes" edu.escuelaing.arep.parcial1.Facade
```

- Terminal 2
```bash
    java -cp "target/classes" edu.escuelaing.arep.parcial1.ReflectiveChat
```

Ingrese al browser la siguiente direccion: *http://localhost:30000/cliente*

## IMPORTANTE

- No dejar espacios al momento de ingresar los parametros.
- Validar el sintax de cada función, recuerde que las funciones validas son las siguientes:
- No usar parametros entre comillas por ejemplo "3" en lugar deje el 3.

**Los comandos que soporta el chat son los siguientes:**
1. Class([class name]): Retorna una lista de campos declarados y métodos declarados.
**Ejemplo:** class(java.lang.Math)
2. invoke([class name],[method name]): retorna el resultado de la invocación del método.  
**Ejemplo:** invoke(java.lang.System,getenv)
3. unaryInvoke([class name],[method name],[paramtype],[param value]): retorna el resultado de la invocación del método. paramtype = int | double | String.
**Ejemplos:**
- unaryInvoke(java.lang.Math,abs,int,3)
- unaryInvoke(java.lang.Integer,valueOf,String,3)
4. binaryInvoke([class name],[method name],[paramtype 1],[param value], [paramtype 1],[param value],): retorna el resultado de la invocación del método. paramtype = int | double | String. 
**Ejemplos:**
- binaryInvoke(java.lang.Math,max,double,4.5,double,-3.7)
4. El chat solo soporta invocación de métodos estáticos.




