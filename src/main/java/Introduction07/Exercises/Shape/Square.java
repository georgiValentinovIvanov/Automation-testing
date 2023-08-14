package Introduction07.Exercises.Shape;

public class Square extends Shape{
    @Override
    Double getArea(Double side) {
        return side * side;
    }

    @Override
    Double getPerimeter(Double side) {
        return 4 * side;
    }
}
