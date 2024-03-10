## Encriptación RSA: Funcionamiento, Ventajas y Desventajas

RSA es un algoritmo de encriptación asimétrica ampliamente utilizado en seguridad de la información y comunicaciones. Funciona mediante la generación de un par de claves: una pública y una privada. La clave pública se utiliza para encriptar los datos, mientras que la clave privada se utiliza para desencriptarlos. 

### Funcionamiento de RSA

1. **Generación de Claves**: Se genera un par de claves usando un algoritmo matemático. La clave pública se comparte abiertamente, mientras que la clave privada se mantiene en secreto.

2. **Encriptación**: El emisor cifra el mensaje usando la clave pública del receptor. Este mensaje solo puede ser descifrado por el receptor, que posee la clave privada correspondiente.

3. **Desencriptación**: El receptor utiliza su clave privada para descifrar el mensaje encriptado y recuperar los datos originales.

### Ventajas de RSA

- **Seguridad**: RSA es uno de los algoritmos de encriptación más seguros cuando se utiliza correctamente, ya que la clave privada nunca se comparte.
- **Autenticidad**: Permite la autenticación de datos mediante la firma digital, ya que solo el poseedor de la clave privada puede generar una firma válida.

### Desventajas de RSA

- **Rendimiento**: Los cálculos necesarios para RSA pueden ser computacionalmente costosos, especialmente con claves más largas.
- **Tamaño de Clave**: Las claves RSA más seguras tienden a ser bastante largas, lo que puede aumentar el tamaño del mensaje encriptado.

## Funcionamiento del Programa

El programa proporciona una implementación básica de encriptación y desencriptación utilizando el algoritmo RSA. Está dividido en dos clases:

1. **RSAEncryption**: Proporciona métodos para encriptar, desencriptar y generar pares de claves RSA.

2. **FileEncryption**: Permite al usuario encriptar y desencriptar mensajes almacenados en un archivo. Si no existen claves previamente generadas, el programa las crea automáticamente. Las claves se guardan en archivos locales ('private.key' y 'public.key'). Luego, el usuario puede seleccionar entre guardar un mensaje encriptado en el archivo o recuperar y desencriptar los datos del archivo.

Este programa simplificado es útil para comprender los conceptos básicos de encriptación RSA y cómo se pueden aplicar en la práctica para proteger la información.
