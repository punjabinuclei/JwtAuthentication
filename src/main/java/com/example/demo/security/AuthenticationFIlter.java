package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
// @SuppressWarnings("deprecation")
// public class AuthenticationFIlter extends OncePerRequestFilter {

//     @Autowired
//     JwtUtil jwtUtil;

//     @Autowired
//     UserDetailsService userDetailsService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         // Extract auth header
//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         // JWT Purpose

//         String jwtToken = authHeader.substring(7);
//         String userName = jwtUtil.extractUserNames(jwtToken);

//         if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             UserDetails userDb = userDetailsService.loadUserByUsername(userName);

//             if (userDb != null && jwtUtil.validateToken(jwtToken, userDb)) {
//                 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                         userDb, null, userDb.getAuthorities());
//                 authenticationToken.setDetails(new WebAuthenticationDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//             }
//         }
//         filterChain.doFilter(request, response);
//     }

// }

@Component
@Deprecated
public class AuthenticationFIlter extends OncePerRequestFilter{


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            
                String authHeader=request.getHeader("Authorization");

                if(authHeader==null || !authHeader.startsWith("Bearer "))
                {
                    filterChain.doFilter(request, response);
                    return;
                }

                String token=authHeader.substring(7);
                String username=jwtUtil.extractUserNames(token);

                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    UserDetails userDb=userDetailsService.loadUserByUsername(username);

                    if( jwtUtil.validateToken(token, userDb) )
                    {
                        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDb,null, userDb.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken); 
                    }
                }

                filterChain.doFilter(request, response);


            
            }

}