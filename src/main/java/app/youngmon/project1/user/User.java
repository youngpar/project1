package app.youngmon.project1.user;

public class User {
    private Long id;
    private String userId;
    private String userPw;
    private UserDetail userDetail;

    public User() {};

    public User(Long id, String userId, String userPw) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userDetail = null; //  이메일, 휴대폰 번호 등 상세 정보는 진행 중 변경 가능성이 높아 확장성을 위해 분리
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() { return userPw; }
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public UserDetail getUserDetail() { return userDetail; }
    public void setUserDetail(UserDetail userDetail) { this.userDetail = userDetail; }
}
