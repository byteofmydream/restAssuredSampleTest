package groupJ;

public class UserPost {
    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPost user = (UserPost) o;

        if (userId != user.userId) return false;
        if (id != user.id) return false;
        if (title != null ? !title.equals(user.title) : user.title != null) return false;
        return body != null ? body.equals(user.body) : user.body == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public UserPost(){
        super();
    }

    public UserPost(int id, String title, String body, int userId) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
