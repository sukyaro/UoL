public class Person {
    private String name;
    private String email;

    public String greet() {
        return "sendto: " + email + "Hi " + name + ",\n";
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getEmail() {
        return email;
    }
}
