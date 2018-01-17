package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test AuthFilter.
 * @author atrifonov.
 * @version 1.
 * @since 16.01.2018.
 */
public class AuthFilterTest {
    @Test(expected = IOException.class)
    public void whenReqHasNotLoginThenRedirect() throws ServletException, IOException {
        AuthFilter authFilter = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain filterChain = mock(FilterChain.class);


        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("test");
        when(session.getAttribute("login")).thenReturn(null);
        when(request.getContextPath()).thenReturn("/test");

        String test = String.format("%s/login", request.getContextPath());

        doThrow(new IOException()).when(response).sendRedirect(test);

        authFilter.doFilter(request, response, filterChain);
    }

    @Test(expected = IOException.class)
    public void whenReqHasLoginThenDoFilter() throws ServletException, IOException {
        AuthFilter authFilter = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain filterChain = mock(FilterChain.class);


        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("/login");
        when(session.getAttribute("login")).thenReturn(null);
        when(request.getContextPath()).thenReturn("/test");

        doThrow(new IOException()).when(filterChain).doFilter(request, response);

        authFilter.doFilter(request, response, filterChain);
    }
}
