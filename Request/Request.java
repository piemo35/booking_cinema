package Request;

import Persons.Persons;
import Utils.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Request extends Thread {
    private final Socket socket;
    private final Utils utils;


    public Request(Socket socket) {
        this.socket = socket;
        this.utils = new Utils();
    }


    @Override
    public void run() {
        int s = 1; // variabile che controlla il ciclo

        while (s != 0) {

            try {
                // invio la matrice da stampare nel client [1]
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                os.writeObject(Utils.SEATS);

                // memorizzo l'oggetto in arrivo dal client [2]
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                Persons req = (Persons) is.readObject();

                //controllo se posso prenotare il posto o se è occupato, poi modifico la matrice SEATS
                req = utils.book(req);      //synchronized (utils){ req = utils.checkForBook(req); }   // altro modo per usare synchronized

                // invio al client la matrice modificata [3]
                ObjectOutputStream oss = new ObjectOutputStream(socket.getOutputStream());
                oss.writeObject(Utils.SEATS);

                // controllo se la prenotazione è avvenuta
                if (req.getIsBooked()) {

                    // mando l'esito positivo al client [4]
                    oss.writeObject(true);

                    // stampo nel server la lista di utenti memorizzati
                    Utils.printList();

                } else {
                    // mando esito negativo al client [4]
                    oss.writeObject(false);
                }

                // ricevo la variabile che controlla il ciclo in arrivo dal client [5]
                s = (int) is.readObject();
            }
            catch (IOException | ClassNotFoundException e){
                break;
            }

        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
