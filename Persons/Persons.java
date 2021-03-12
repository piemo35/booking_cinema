package Persons;

import java.io.Serializable;

public class Persons implements Serializable {

    private final String name;
    private final String surname;
    private final int r,c;
    private final String cellNo;
    private boolean isBooked; 

    public Persons(String name, String surname, String cellNo, int r, int c) {
        this.name = name;
        this.surname = surname;
        this.r = r;
        this.c = c;
        this.cellNo = cellNo;
        this.isBooked = false;
    }

    public int getR() { return r; }

    public int getC() { return c; }

    public boolean getIsBooked() { return isBooked; }

    public void setIsBooked(boolean b) { this.isBooked = b;}

    @Override
    public String toString() {
        return "\nName = " + name +
                "\nSurname = " + surname +
                "\nCell number = " + cellNo +
                "\nPosition = R:" + r +
                "\t C:" + c + " \n";
    }

}
