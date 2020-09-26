package BasicClasses;

import java.io.Serializable;

public class Location implements Serializable {
    private Float x; //Поле не может быть null
    private Float y;//Поле не может быть null
    private Double z; //Поле не может быть null

    public Location(Float xTown, Float yTown, Double zTown) {
        this.x = xTown;
        this.y = yTown;
        this.z = zTown;
    }

    public Double getZ() {
        return z;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
