package com.hspedu.qqclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;
import com.hspedu.qqcommon.User;

//该类完成用户登陆验证和用户注册等功能
public class UserClientService {
    //因为我们可能在其他地方使用user信息，因此做成成员属性
    private User u = new User();
    private Socket socket;

    public boolean checkUser(String userId, String pwd) {
        boolean b = false;
        //创建User对象
        u.setUserId(userId);
        u.setPasswd(pwd);

        //连接到服务器，发送u对象
        try {
            //连接到服务器，发送u对象
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            //为了发送user对象，得到ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);//发送User对象

            //读取从服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message)ois.readObject();

            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {//登录成功

                //创建一个和服务器保持通信的线程-> 创建一个类ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                //启动客户端的线程
                clientConnectServerThread.start();
                //为了后面客户端的扩展，我们将线程放入到集合管理
                ManageClinentConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                b = true;
            }else {
                //如果登录失败，我们就不能启动和服务器通信的线程，关闭socket
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //向服务器端请求在线用户列表
    public void onlineFriendList() {
        //发送一个Message，类型MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserId());
        //发送给服务器
        try {
            //从管理线程的集合中，通过userId，得到这个线程对象
            ClientConnectServerThread clientConnectServerThread =
                    ManageClinentConnectServerThread.getClientConnectServerThread(u.getUserId());
            //通过这个线程得到关联的socket
            Socket socket = clientConnectServerThread.getSocket();
            //得到当前线程得Socket对应的ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);//发送一个Message对象，向服务端要求在线用户列表
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //编写方法，退出客户端，并给服务器发送一个退出系统message对象
    public void logout(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());//一定要指定我是哪个客户端id

        //发送messgae
        try {
            //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClinentConnectServerThread.getClientConnectServerThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserId() + "退出系统");
            System.exit(0);//结束进程
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}























