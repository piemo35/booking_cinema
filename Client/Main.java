package Client;

import Utils.Utils;

import java.io.IOException;

public class Main {
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
