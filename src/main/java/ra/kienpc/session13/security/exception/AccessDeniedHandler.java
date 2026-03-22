package ra.kienpc.session13.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 403
        // Message: access denied - Mô tả rõ ai sai quyền truy cập
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); //403
        String message = "Access Denied";
        response.getWriter().write(String.format("{" +
                "Status: %d, \n" +
                "Message: %s" +
                "}", HttpServletResponse.SC_FORBIDDEN, message));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
