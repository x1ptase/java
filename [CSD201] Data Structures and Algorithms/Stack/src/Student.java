public class Student{
    private String id;
    private String name;
    private int yOB;
    private double gpa;
    
    public Student(){
        
    }

    public Student(String id, String name, int yOB, double gpa) {
        this.id = id;
        this.name = name;
        this.yOB = yOB;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getyOB() {
        return yOB;
    }

    public void setyOB(int yOB) {
        this.yOB = yOB;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", yOB=" + yOB + ", gpa=" + gpa + '}';
    }

    
    
    
}