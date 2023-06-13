package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MessageClientService {
    public void sendMessageToAll(String content, String senderId) {//content 内容  senderId 发送者
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//群发消息这种类型
        message.setContent(content);
        message.setSender(senderId);
        message.setSendTime(new Date().toString());//发送时间设置到message对象
        System.out.println(senderId + " 对大家说" + content);
        //发送给服务器
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream((ManageClinentConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream()));
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void sendMessageToOne(String content, String senderId, String getterId){//content 内容  senderId 发送用户id  getterId 接收用户id
        //构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);//普通的聊天消息这种类型
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString());//发送时间设置到message对象
        System.out.println(senderId + " さんが" + getterId + " に" + content);
        //发送给服务器
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream((ManageClinentConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream()));
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
