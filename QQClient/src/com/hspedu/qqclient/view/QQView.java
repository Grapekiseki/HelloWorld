package com.hspedu.qqclient.view;

import com.hspedu.qqclient.service.FileClientService;
import com.hspedu.qqclient.service.MessageClientService;
import com.hspedu.qqclient.service.UserClientService;
import com.hspedu.qqclient.utils.Utility;

//客户端的菜单界面
public class QQView {
    private boolean loop = true;//控制是否显示菜单
    private String key = ""; //接受用户的键盘输入
    //对象是用于登录服务/注册用户
    private UserClientService userClientService = new UserClientService();
    //对象用户私聊/群聊
    private MessageClientService messageClientService = new MessageClientService();
    private FileClientService fileClientService = new FileClientService();//该对象是用户传输文件

    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("客户端退出系统..");
    }
    //显示主菜单
    private void mainMenu() {

        while(loop) {
            System.out.println("========欢迎登录网络通信系统========");
            System.out.println("\t 1 ログイン(登录系统)");
            System.out.println("\t 9 ログアウト(退出系统)");
            System.out.print("请输入你的选择: ");

            key = Utility.readString(1);

            switch(key) {
                case "1":
                    System.out.println("ユーザーIDを入力してください(请输入用户号)：");
                    String userId = Utility.readString(50);
                    System.out.println("パスワードを入力してください(请输入密码)：");
                    String pwd = Utility.readString(50);

                    if(userClientService.checkUser(userId, pwd)) {
                        System.out.println("========(User" + userId + "ログイン成功)========");
                        //进入二级菜单
                        while(loop) {
                            System.out.println("========网络通信系统二级菜单(User: " + userId + ")========");
                            System.out.println("\t 1 オンラインユーザーリストを表示(显示在线用户列表)");
                            System.out.println("\t 2 メッセージを一斉送信(群发消息)");
                            System.out.println("\t 3 プライベートメッセージを送信(私聊消息)");
                            System.out.println("\t 4 ファイルを送信(发送文件)");
                            System.out.println("\t 9 システムを終了(退出系统)");
                            System.out.print("選択肢を入力してください(请输入你的选择): ");
                            key = Utility.readString(1);
                            switch(key) {
                                case "1":
                                    //写一个方法，来获取在线用户列表
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.print("请输入想对大家说的内容：");
                                    String s = Utility.readString(100);
                                    //编写一个方法，将消息封装成message对象，发送给服务器
                                    messageClientService.sendMessageToAll(s, userId);
                                    break;
                                case "3":
                                    System.out.print("チャットしたいユーザーのIDを入力してください(请输入想聊天的用户号(在线))：");
                                    String getterId = Utility.readString(50);
                                    System.out.print("言いたいことを入力してください(请输入想说的话)：");
                                    String content = Utility.readString(100);
                                    //编写一个方法，将消息发送给服务器
                                    messageClientService.sendMessageToOne(content, userId, getterId);
                                    break;
                                case "4":
                                    System.out.println("请输入你想把文件发送给的用户(在线用户): ");
                                    getterId = Utility.readString(50);
                                    System.out.println(("请输入发送文件的路径(形式 d:\\xx.jpg)"));
                                    String src = Utility.readString(100);
                                    System.out.println("请输入把文件发送到对应的路径(形式 d:\\yy.jpg)");
                                    String dest = Utility.readString(100);
                                    fileClientService.sendFileToOne(src, dest, userId, getterId);
                                    break;
                                case "9":
                                    //调用方法，给服务器发送一个退出系统的message
                                    userClientService.logout();
                                    loop = false;
                                    break;

                            }
                        }
                    }else {//登录服务器失败
                        System.out.println("======登录失败======");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}

