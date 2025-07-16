// Student.java
class Student {
    private String id;
    private String name;
    private double gpa;
    private String major;
    private double balance; // Số tiền trong tài khoản

    public Student(String id, String name, double gpa, String major, double balance) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.major = major;
        this.balance = balance;
    }
    
    public Student(String id, String name, double gpa, String major) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.major = major;
        this.balance = 0;
    }
    
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.gpa = 0;
        this.major = "";
        this.balance = 0;
    }

    public Student() {
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

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void addToBalance(double amount) {
        this.balance += amount;
    }
    
    @Override
    public String toString(){
        return "("+id+","+name+","+gpa+","+major+","+balance+")";
    }
}