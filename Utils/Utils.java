package Utils;

import Persons.Persons;

import java.io.IOException;
import java.util.*;

public class Utils {

    public static final int port = 4444;
    public static int COL = 15;
    public static int ROW = 10;
    public static char[][] SEATS = new char[ROW][COL]; // matrice che mostra i posti con "L" o "O"
    public static List<Persons> data = new ArrayList<>(); // lista in cui memorizzo gli utenti che hanno prenotato
    public static Scanner scanner = new Scanner(System.in); // scanner per leggere da tastiera

    // funzione per controllare se il posto passato come parametro è libero
    public static synchronized boolean isL(int r, int c) { return SEATS[r][c] == 'L'; }

    // funzione per controllare se la data di oggi rientra nelle date limite
    public static boolean checkTime(){

        // data limite massima per prenotare
        Calendar calendarMax = Calendar.getInstance();
        calendarMax.set(2022, Calendar.FEBRUARY, 27, 19, 30, 0);

        // data limite minima per prenotare
        Calendar calendarMin = Calendar.getInstance();
        calendarMin.set(2021, Calendar.FEBRUARY, 22, 0, 0, 1);

        // data di oggi
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());

        // comparazione
        return currentCalendar.compareTo(calendarMax) < 0 && currentCalendar.compareTo(calendarMin) > 0;

    }

    // funzione per controllare le i posti sono già tutti occupati
    public static synchronized boolean isFull(char[][] SEATS){
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if ((SEATS[i][j] != 'O')) {
                        return false;
                    }
                }
            }
        return true;

    }

    // stampa una lista contenente gli utenti che hanno già prenotato
    public static synchronized void printList() throws IOException {
        clearScreen();
        System.out.print("\nLista degli utenti che hanno prenotato fino ad ora ");
        for (Persons datum : data) System.out.println(datum);
    }

    // pulisce il terminale su windows e linux/macOS
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {}
    }

    // Prenotazione: cambia il contenuto della matrice in base alle coordinate che ha scelto l'user ( se il posto era 'L' diventa 'O' )
    public synchronized Persons book(Persons user) {

        // controllo se le coordinate sono Libere o Occupate
        if (isL(user.getR() - 1, user.getC() - 1)) {

            // se sono Libere cambio "L" in "O" e la variabile isBooked dell'oggetto user
            SEATS[user.getR() - 1][user.getC() - 1] = 'O';
            user.setIsBooked(true);

            //memorizzo dati del cliente nella lista del server
            data.add(user);
        }

        return user;
    }

    // funzione per chiedere e memorizzare i dati personali dell'user
    public static Persons askData(){

        System.out.println("PRENOTAZIONE POSTI PER IL FILM 'VIA COL VENTO'");
        System.out.println("IL FILM VERRA' PROIETTATO IL GIORNO 27 FEBBRAIO 2021 ALLE ORE 20:00");
        System.out.println("SARA' POSSIBILE EFFETTUARE LE PRENOTAZIONI FINO ALLE ORE 19:30 DEL 27 FEBBRAIO 2021");
        System.out.print("\n");
        System.out.println("L = Libero");
        System.out.println("O = Occupato");
        System.out.print("\n");


        System.out.println("Inserisci il nome");
        Scanner g = new Scanner(System.in);
        String name = g.next();

        System.out.println("Inserisci il cognome");
        String surname = g.next();

        System.out.println("Inserisci il numero di telefono");
        String cellNo = g.next();

        System.out.println("Inserisci la riga");
        int r = g.nextInt();

        System.out.println("Inserisci la colonna");
        int c = g.nextInt();

        return new Persons(name, surname, cellNo, r, c);

    }

    // funzione per stampare la matrice dei posti
    public static synchronized void printMatrix(char[][] SEATS){

        System.out.print("\n\n   \t");
        for (int i = 0; i < COL; i++) System.out.print(i + 1 + "\t");
        System.out.println("\n");

        for (int i = 0; i < ROW; i++) {

            if(i < 9){
                System.out.print("0" + (i + 1) + ")\t" );
            }else{
                System.out.print((i + 1) + ")\t");
            }

            for (int j = 0; j < COL; j++) System.out.print(SEATS[i][j] + "\t");

            System.out.println("\n");
        }
    }

}

