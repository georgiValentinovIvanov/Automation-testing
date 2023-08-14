package Introduction06.constructors.vehicle;

public class car extends vehicle{
    private String brand;

    public car(String regNo, String brand) {
        super(regNo);
        this.brand = brand;
    }
}
