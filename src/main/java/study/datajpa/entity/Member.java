package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of={"id","username","age"}) //연관관계 필드는 순환참조가 될 수 있으니 가급적 하지말자
public class Member {

    @Id @GeneratedValue
    @Column(name ="member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    //protected Member() {}

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team !=null)   changeTeam(team);
    }

    //연관관계를 세팅하는 메서드
    public void changeTeam(Team team){
        //객체기 때문에 서로 바꿔줘야한다
        this.team = team; //멤버의 팀을 바꿔준다
        team.getMembers().add(this);  //팀의 멤버를 바꿔준다.
    }
}
