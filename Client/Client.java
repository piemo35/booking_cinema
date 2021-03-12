package Client;

import java.io.*;
import java.net.Socket;


import Utils.Utils;
import Persons.Persons;

public class Client extends Utils {

    public static char[][] matrix = new char[Utils.ROW][Utils.COL];

    public void  runClient() throws IOException, ClassNotFoundException {

        Socket socket = new Socket("localhost", Utils.port);

        System.out.println("Benvenuto!");
        System.out.println("Premi 1 per visualizzare i posti disponibili");
        System.out.println("Premi 0 per uscire");
        int s = Utils.scanner.nextInt();

        while (s != 0) {

            // ricevo la matrice dal server [1]
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            matrix = (char[][]) is.readObject();
            Utils.printMatrix(matrix);

            // controllo se i posti sono tutti occupati
            if(Utils.isFull(matrix)){
                System.out.println("I posti sono tutti occupati :'(");
                System.exit(0);
            }

            // lettura da tastiera dei dati dell'user
            Persons usr = Utils.askData();

            // invio dell'oggetto contenente i dati dell'user al server [2]
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(usr);

            // ricevo matrice modificata dal server [3]
            ObjectInputStream iss = new ObjectInputStream(socket.getInputStream());
            matrix = (char[][]) iss.readObject();
            Utils.printMatrix(matrix);

            // esito che ricevo dal server [4]
            boolean confirm = (boolean) is.readObject();

            if (confirm) {
                System.out.println("Prenotazione effettuata! Controlla il tuo posto nella matrice");
                System.out.println("Vuoi prenotare un'altro posto? Premi 1, altrimenti premi 0");
                s = Utils.scanner.nextInt();

                // mando la variabile che controlla il ciclo al server [5]
                os.writeObject(s);
            } else {
                System.out.println("PRENOTAZIONE NON ANDATA A BUON FINE, IL POSTO E' GIA' OCCUPATO, RIPROVA..");
                s = 1;

                // mando la variabile che controlla il ciclo al server [5]
                os.writeObject(s);
            }

        }

        socket.close();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // controllo la data e faccio partire il client
        if (Utils.checkTime()) new Client().runClient();

        else System.out.println(
                        "Ci dispiace, ora non puoi prenotare posti per questo film :'( " +
                        "\nLe prenotazioni per il film 'Via col vento' possono essere effettuate solo dal 22 Febbraio al 27 Febbraio." +
                        "\nTorna a trovarci presto!"
        );

    }

}
