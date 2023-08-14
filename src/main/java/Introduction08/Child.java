package Introduction08;

public class Child extends Person{
    public Child(String name, String sex, String religion, String language, String nationality, String country, long egn) {
        super(name, sex, religion, language, false, nationality, country, egn);
    }

    @Override
    public boolean isAdult() {
        System.out.println("The child is not an adult.");
        return false;
    }

    @Override
    public boolean canTakeLoan() {
        System.out.println("The child can not take a loan.");
        return false;
    }

    @Override
    public void setJob(boolean job) {
        throw new IllegalArgumentException("The child cannot have a job");
    }

    public void play(String toy) {
        System.out.println("I am playing with a " + toy);
    }
}
