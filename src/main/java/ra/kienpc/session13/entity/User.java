package ra.kienpc.session13.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    //id (Long).
    //username (String, unique).
    //password (String - sẽ lưu chuỗi mã hóa).
    //role (String - Ví dụ: "USER", "ADMIN").
    //enabled (boolean - mặc định true).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    @JsonIgnore
    String password;
    String role;
    boolean enabled = true;

}
