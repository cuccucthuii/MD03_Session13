package ra.kienpc.session13.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String exception = (String) request.getAttribute("exception");

        Map<String, Object> map = new HashMap<>();
        map.put("status", 401);

        if ("ExpiredJwtException".equals(exception)) {
            map.put("message", "Token hết hạn");
        } else {
            map.put("message", "Chưa xác thực");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);

        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
