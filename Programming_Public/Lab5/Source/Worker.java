package com.nsi.lab5.commandwork;
import java.time.ZonedDateTime;

/**
 * @author ITMO
 * Class if Main Collection
 */
public class Worker implements Comparable<Worker>{

    /**
     *
     * @return Returns usual String format
     */
    @Override
    public String toString() {
        return ("ID: " + id + " Name: " + name + " Coordinates: " + this.coordinates + " Date: " + this.creationDate + " Salary: " + salary + " Position:" + this.position + " Status: " + this.status + " Organization: " + this.organization);
    }

    ;

    /**
     *
     * @return Returns CSV String format
     */
    public String tocsv() {
        return (name + ";" + this.coordinates.tocsv() + ";" + salary + ";" + this.status + ";" + this.position + ";" + this.organization.tocsv());
    }

    ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return name.equals(worker.name);
    }

    ;

    @Override
    public int hashCode() {
        return (id);
    }

    ;

    /**
     * Compares two Workers
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Worker o) {
      if(o.getSalary() == this.getSalary())
          return 0;
      else if(o.getSalary() > this.getSalary())
          return 1;
      else if (o.getSalary() < this.getSalary())
          return -1;
      return 0;
    };
    /**
     * ID of Worker
     */
    private int id;//1Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * Worker's Name
     */
    private String name; //2Поле не может быть null, Строка не может быть пустой
    /**
     * Worker's {@link Coordinates}
     */
    private Coordinates coordinates; //3Поле не может быть null
    /**
     * Date of Initialization
     */
    private java.time.ZonedDateTime creationDate; //4Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * Worker's Salary
     */
    private float salary; //5Значение поля должно быть больше 0
    /**
     * Worker's {@link Position}
     */
    private Position position; //6Поле не может быть null
    /**
     * Worker's {@link Status}
     */
    private Status status; //7Поле не может быть null
    /**
     *      * Worker's {@link Organization}
     *
     */
    private Organization organization; //8Поле не может быть null

    public  Worker  () {};

    public Worker(String[] indata) {

        Main.idcount++;

        id = Main.idcount;

        name = indata[0];

        coordinates = new Coordinates(indata[1]);

        creationDate = ZonedDateTime.now();

        salary = Float.parseFloat(indata[2]);

        position = Position.getPosition(indata[4]);

        status = Status.getStatus(indata[3]);

        organization = new Organization(indata[5]);


        //System.out.println(this);

    };


    public int getId() {
        return id;
    }

    public Float getSalary() {
        return salary;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}




