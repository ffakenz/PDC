package ar.edu.ubp.pdc.utilities;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletsUtil {

	default public void redirect(String address, HttpServletResponse response) {
		try {
			response.sendRedirect(address);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	default public void printErrorMessage(ServletContext ctx, String message, HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(400);
		request.setAttribute("error", message);
    	gotoPage(ctx, "/error.jsp", request, response);    	
    	
    }

	default public void gotoPage(ServletContext ctx, String address, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = ctx.getRequestDispatcher(address);
        try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			printErrorMessage(ctx, e.getMessage(), request, response);
		}
    }
	
	default public <A,B> Optional<B> strategy(A operacion, Predicate<A> predicate
											, Supplier<Optional<B>> ifBlock
											, Supplier<Optional<B>> elseBlock) {
		if(predicate.test(operacion)) 
			return ifBlock.get(); 
		else 
			return elseBlock.get();	
	}

}



