package groupJ;

public class User {
    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (id != user.id) return false;
        if (title != null ? !title.equals(user.title) : user.title != null) return false;
        return body != null ? body.equals(user.body) : user.body == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public User(){
        super();
    }

    public User(int id, String title, String body, int userId) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
