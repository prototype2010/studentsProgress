package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Evgeny on 28.02.2016.
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String currentRole = (String)httpServletRequest.getSession().getAttribute("role");
        String currentPage = httpServletRequest.getRequestURI();



        if(currentRole == null) {
//            css styles cannot be imported here
            if(currentPage.equals("/login") || currentPage.endsWith(".css")) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendRedirect("/login");
                return;
            }
        } else {
            currentRole = currentRole.toLowerCase();
            if(currentPage.startsWith("/admin")) {
                if(!currentRole.equals("admin")) {
                    httpServletResponse.sendRedirect("/page_not_found");
                    return;
                }
            }

            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    public void destroy() {

    }
}
