package com.hspedu.qqcommon;

import java.io.Serializable;

//表示客户端和服务器通信时的消息对象
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;//发送者
    private String getter;//接收者
    private String content;//消息内容
    private String sendTime;//发送时间
    private String mesType;//消息类型[可以再接口定义消息类型]

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    private byte[] fileByte;//将来按字节数组的方式进行文件传输
    private int fileLen = 0;//文件长度大小初始化
    private String dest;//将文件传输到哪里
    private String src;//源文件路径

    public String getMesType() {

        return mesType;
    }

    public void setMesType(String mesType) {

        this.mesType = mesType;
    }

    public String getSender() {

        return sender;
    }

    public void setSender(String sender) {

        this.sender = sender;
    }

    public String getGetter() {

        return getter;
    }

    public void setGetter(String getter) {

        this.getter = getter;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getSendTime() {

        return sendTime;
    }

    public void setSendTime(String sendTime) {

        this.sendTime = sendTime;
    }


}
