import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * websocket 服务端
 * <h1><a href="http://www.jiawa365.com">嘉哇科技</a><br>讲师：谭兴义</h1>
 */
@ServerEndpoint("/websocket")
public class WebSocketServer {


    /**
     * 产生新会话
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println("用户上线:"+session);
    }

    /**
     * 有用户下线
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("用户下线:"+session);
    }

    /**
     * 收到新消息
     * @param message
     * @param session
     */
    @OnMessage
    public void OnMessage(String message, Session session){
        //回复消息
        try {
            System.out.println("收到用户："+session+"的新消息："+message);
            session.getBasicRemote().sendText("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
