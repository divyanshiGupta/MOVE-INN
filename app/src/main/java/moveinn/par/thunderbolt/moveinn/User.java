package moveinn.par.thunderbolt.moveinn;

import android.content.Context;

public class User {

    String fname,lname,email, username, password;
    String contactno;

    public User(String fname,String lname, String contactno,String email, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.contactno = contactno;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this("","", "","", username, password);
    }

    public User(Context context)
    {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLName() {
        return lname;
    }

    public void setLName(String name) {
        this.lname = name;
    }

    public String getFName()
    {
        return fname;
    }

    public void setFname(String name)
    {
        this.fname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    public String getContact() {
        return contactno;
    }

    public void setContact(String contact) {
        this.contactno = contact;
    }
}
