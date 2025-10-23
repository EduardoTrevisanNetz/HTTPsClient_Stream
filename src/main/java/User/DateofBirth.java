package User;

import java.util.Date;

public class DateofBirth {
    private Date date;
    private int age;

    public Date getDate() { return date; }
    public int getAge() { return age; }

    public void setDate(Date date) { this.date = date; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return  date +", " + age + " years old";}
}
