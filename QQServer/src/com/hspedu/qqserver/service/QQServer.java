package com.hspedu.qqserver.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;
import com.hspedu.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

//这是服务器，在监听9999，等待客户端连接，并保持通信
public class QQServer {
    private ServerSocket ss = null;
    //创建一个集合，存放多个用户，如果是这些用户登录，就认为是合法
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static { //在静态代码块，初始化validUsers
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("至尊宝", new User("至尊宝", "123456"));
        validUsers.put("紫霞仙子", new User("紫霞仙子", "123456"));
        validUsers.put("Zhang", new User("Zhang", "123456"));
        validUsers.put("Wang", new User("Wang", "123456"));

    }

    //验证用户是否有效的方法
    private boolean checkUser(String userId, String passwd) {
        User user = validUsers.get(userId);
        if (user == null) {//说明userId没有存在validUsers的key中
            return false;
        }
        if (!user.getPasswd().equals(passwd)) {//userId正确，但是密码错误
            return false;
        }
        return true;
    }

    public QQServer() {

        try {
            System.out.println("服务端在9999端口监听..");
            System.out.println("サーバーは9999ポートでリッスンしています。");
            //启动推送新闻的线程
            new Thread(new SendNewsToAllService()).start();
            ss = new ServerSocket(9999);

            while (true) {//当和某个客户端连接后，会继续监听，因此while
                Socket socket = ss.accept();//如果没有客户端连接，就会阻塞在这里
                //得到socket关联的对象输入流
                ObjectInputStream ois =
                        new ObjectInputStream(socket.getInputStream());
                //得到socket关联的对象输出流
                ObjectOutputStream oos =
                        new ObjectOutputStream(socket.getOutputStream());
                User u = (User) ois.readObject();//读取客户端发送的User对象
                //构建一个Message对象，准备回复客户端
                Message message = new Message();
                //验证

                if (checkUser(u.getUserId(), u.getPasswd())) {//登录成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    //将message对象回复客户端
                    oos.writeObject(message);
                    //创建一个线程，和客户端保持通信，该线程需要持有socket对象
                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, u.getUserId());
                    //启动该线程
                    serverConnectClientThread.start();
                    //把该线程对象，放入到一个集合中，进行管理
                    ManageClientThreads.addClientThread(u.getUserId(), serverConnectClientThread);
                } else {//登录失败
                    System.out.println("用户 id=" + u.getUserId() + " pwd=" + u.getPasswd() + "验证失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    //关闭socket
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //如果服务器退出了while，说明服务器端不再监听，因此关闭ServerSocket
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ;


}

