package User;

public class Name {
    private String title;
    private String first;
    private String last;

    public String getTitle() { return title; }
    public String getFirst() { return first; }
    public String getLast() { return last; }

    public void setTitle(String title) { this.title = title; }
    public void setFirst(String first) { this.first = first; }
    public void setLast(String last) { this.last = last; }

    @Override
    public String toString() {
        return title + " " + first + " " + last;}
}
