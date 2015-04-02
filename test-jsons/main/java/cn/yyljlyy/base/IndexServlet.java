package cn.yyljlyy.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends AbstractTestServlet {

	private static final long serialVersionUID = 3670375966361402697L;

	@Override
	public void _service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String xx = "";
		for (int i = 0; i < 10000; i++) {
			xx += i;
		}
		resp.getWriter().println(xx);
		
	}
}
