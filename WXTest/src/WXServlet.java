import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 
 * <h1><a href="http://www.jiawa365.com">���ۿƼ�</a><br>��ʦ��̷����</h1>
 * @time 2019��6��6�� ����3:01:20
 */
@WebServlet("/wx")
public class WXServlet extends HttpServlet{

	/**
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String echostr = req.getParameter("echostr");
		System.out.println("doGet����΢�Ź���ƽ̨��֤");
		//��echostr���ظ�΢�ű�����֤
		resp.getWriter().write(echostr);
	}
	
	
	/**
	 * ���ڴ�����Ϣ����
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("�յ�����Ϣ,��������");
		BufferedReader reader = req.getReader();
		String content=null;
		List<String> msg=new ArrayList<String>();
		
		while((content=reader.readLine())!=null) {
			msg.add(content);
		}
		reader.close();
		
		String result="<xml>\n" +
				" <ToUserName><![CDATA[onOrP5_4RSb586lKqr6KXQuE502s]]></ToUserName>\n" +
				" <FromUserName><![CDATA[gh_e5f3d62aa0de]]></FromUserName>\n" +
				" <CreateTime>"+System.currentTimeMillis()+"</CreateTime>\n" +
				" <MsgType><![CDATA[text]]></MsgType>\n" +
				" <Content><![CDATA[are you pig��]]></Content>\n" +
				" </xml>";
		//�ظ���˿��Ϣ
		resp.getWriter().write(result);
		
		
	}
}

