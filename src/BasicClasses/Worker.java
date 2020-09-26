package BasicClasses;

import Collection.IDGenerator;

import java.io.Serializable;
import java.time.ZonedDateTime;


public class Worker implements Comparable<Worker>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float salary; //Значение поля должно быть больше 0
    private Position position; //Поле может быть null
    private Status status; //Поле может быть null
    private Organization organization; //Поле не может быть null

    public Worker(String name, Coordinates coordinates, float salary, Position position, Status status, Organization organization) {
        this.id = IDGenerator.generateID();
        this.name = name;
        this.coordinates = coordinates;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.organization = organization;
        this.creationDate = ZonedDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public float getSalary() {
        return salary;
    }

    public Integer getId() {
        return id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Worker worker) {
        return this.id - worker.getId();
    }
}
