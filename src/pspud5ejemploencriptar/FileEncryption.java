package pspud5ejemploencriptar;

import java.io.*;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class FileEncryption {

    private static final String PRIVATE_KEY_FILE = "private.key";
    private static final String PUBLIC_KEY_FILE = "public.key";
    private static final String FICHERO = "fichero.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Cargar claves o generarlas si no existen
            PrivateKey privateKey = loadPrivateKey(PRIVATE_KEY_FILE);
            PublicKey publicKey = loadPublicKey(PUBLIC_KEY_FILE);
            if (privateKey == null || publicKey == null) {
                System.out.println("No se encontraron claves válidas. Generando nuevas claves...");
                KeyPair keyPair = RSAEncryption.generateKeyPair();
                privateKey = keyPair.getPrivate();
                publicKey = keyPair.getPublic();
                savePrivateKey(privateKey, PRIVATE_KEY_FILE);
                savePublicKey(publicKey, PUBLIC_KEY_FILE);
            }

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Guardar mensaje en fichero");
                System.out.println("2. Recuperar datos del fichero");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume la nueva línea

                switch (choice) {
                    case 1:
                        System.out.print("Ingrese el mensaje a encriptar: ");
                        String message = scanner.nextLine();
                        byte[] encryptedData = RSAEncryption.encrypt(message.getBytes(), publicKey);
                        writeToFile(FICHERO, encryptedData);
                        System.out.println("Mensaje encriptado y guardado en el archivo " + FICHERO);
                        break;
                    case 2:
                        byte[] data = readFromFile(FICHERO);
                        if (data.length == 0) {
                            System.out.println("El archivo está vacío. No hay datos para desencriptar.");
                        } else {
                            String decryptedMessage = new String(RSAEncryption.decrypt(data, privateKey));
                            System.out.println("Datos desencriptados: \n" + decryptedMessage);
                        }
                        break;
                    case 3:
                        System.out.println("Saliendo del programa.");
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }

    public static byte[] readFromFile(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        fis.close();
        return bos.toByteArray();
    }

    private static void savePrivateKey(PrivateKey privateKey, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(privateKey);
        }
    }

    private static void savePublicKey(PublicKey publicKey, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(publicKey);
        }
    }

    private static PrivateKey loadPrivateKey(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (PrivateKey) ois.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la clave privada: " + e.getMessage());
            return null;
        }
    }

    private static PublicKey loadPublicKey(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (PublicKey) ois.readObject();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la clave pública: " + e.getMessage());
            return null;
        }
    }
}
