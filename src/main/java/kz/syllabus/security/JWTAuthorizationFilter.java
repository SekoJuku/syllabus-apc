package kz.syllabus.security;


import kz.syllabus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.Access;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter<JWTTokenProvider> extends OncePerRequestFilter {


    private final JWTTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public JWTAuthorizationFilter(JWTTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            response.setStatus(OK.value());
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = authorizationHeader.substring(TOKEN_PREFIX.length());
            try {
                String username = jwtTokenProvider.getSubject(token);
                UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(username);
                if (!userPrincipal.isAccountNonLocked()) {
                    response.setStatus(UNAUTHORIZED.value());
                    return;
                }
                if (jwtTokenProvider.isTokenValid(username, token, request)) {
                    List<GrantedAuthority> authorities = new ArrayList<>(userPrincipal.getAuthorities());
                    Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    SecurityContextHolder.clearContext();
                }

                if (!userPrincipal.getAuthorities().toString().contains(request.getRequestURI()) && !Arrays.asList(PUBLIC_URLS).contains(request.getRequestURI())) {
                    response.setStatus(FORBIDDEN.value());
                    return;
                }
            } catch (Exception e) {
                response.setStatus(UNAUTHORIZED.value());
                return;
            }

        }
        filterChain.doFilter(request, response);
    }
}
