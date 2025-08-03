package multiagentsystem.domain;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Data

public class TeamMember  {

    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    
    
    
    private String email;


    protected TeamMember() {
        
    }
    
    protected TeamMember(String email, ) {
        this.email = email;
        
    }

    public String getEmail() {
        return email;
    }


}
