package ra.kienpc.session13.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ra.kienpc.session13.entity.User;

import java.util.Date;

@Getter
@Setter
@Builder
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Date expired;
//    @JsonIgnoreProperties
    private User user;
}
