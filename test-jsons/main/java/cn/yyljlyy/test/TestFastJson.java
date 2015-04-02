package cn.yyljlyy.test;

import cn.yyljlyy.base.AbstractTestServlet;
import cn.yyljlyy.fastjson.TestFast;
import cn.yyljlyy.jackson.TestJack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by j on 2015/4/2.
 */
public class TestFastJson extends AbstractTestServlet {
    @Override
    public void _service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("getfNum");
        String times = req.getParameter("getfTimes");
        TestJack t = new TestJack(Integer.parseInt(num),Integer.parseInt(times));
        Thread th = new Thread(t);
        th.start();
    }
}
