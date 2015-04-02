package cn.yyljlyy.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTestServlet extends HttpServlet {

	private static final long serialVersionUID = -8837205738090956963L;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private long startTime;

	private long duration;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Thread.currentThread().setName("NB." + Thread.currentThread().getId());
		before();
		_service(req, resp);
		after();
	}
	
	

	private void before() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		startTime = System.nanoTime();
		logger.debug("   start :{}",df.format(new Date()));
	}

	public abstract void _service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
	   
	private void after() throws IOException {
		long end = System.nanoTime();
		duration = end - startTime;
		DecimalFormat df = new DecimalFormat("0.0000");
		logger.debug("duration :{} ms.", df.format(duration / 1000000.0));
		logger.debug(this.getClass().getName());
		setToExcel(this.getClass().getName(), df.format(duration / 1000000.0).toString(), null);
	}
	
	private void setToExcel(String classname,String duration,String agentlog) throws IOException{        
        FileInputStream fs=new FileInputStream("F:\\works\\test.xls");
        POIFSFileSystem ps=new POIFSFileSystem(fs);  
        HSSFWorkbook hwb = new HSSFWorkbook(ps);//产生工作薄对象
		HSSFSheet sheet = hwb.getSheetAt(0);
		OutputStream out = new FileOutputStream("F:\\works\\test.xls");
        HSSFRow row = sheet.createRow((short)sheet.getLastRowNum()+1);

        HSSFCell cn = row.createCell(0);
        cn.setCellValue(classname);
 
        HSSFCell du = row.createCell(1);
        du.setCellValue(duration);
                 
        HSSFCell al = row.createCell(2);
        al.setCellValue(agentlog);
        	
       
        hwb.write(out);
        out.flush();
        out.close();
        System.out.println("数据库导出成功");
		
	}

}
