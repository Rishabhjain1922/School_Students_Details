package Student_Details.School.Students.filter;

import Student_Details.School.Students.Util.JavaUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JavaUtil javaUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("FILTER WORKING");
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String username = javaUtil.extractUsername(token);
                Integer teacherId = javaUtil.extractTeacherId(token);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (javaUtil.validateToken(token, username)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                request.setAttribute("teacherId", teacherId);
            } catch (Exception e) {
                System.out.println("Invalid JWT Token: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}

