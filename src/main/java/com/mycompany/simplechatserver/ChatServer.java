/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplechatserver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author NicholasBocchini
 */
public class ChatServer {
    public ChatServer(int port) throws IOException 
    {
        ServerSocket s = new ServerSocket(port);
        while (true)
        {
            new ChatHandler(s.accept()).start();
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
            throw new RuntimeException(
                "Syntax: java ChatServer <port>");
        new ChatServer(Integer.parseInt(args[0]));
    }
}
