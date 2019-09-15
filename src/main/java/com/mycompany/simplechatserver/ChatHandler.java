/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplechatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author NicholasBocchini
 */
public class ChatHandler {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    static Set<ChatHandler> handlers = new HashSet<ChatHandler>();
    
    public ChatHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        handlers.add(this);
    }
    
    public void start()
    {
        String name ="";
        try 
        {
            name = in.readUTF();
            System.out.println(
                "New Client " + name + " from " + socket.getInetAddress());
            broadcast(name + " entered");
            while (true)
                broadcast(name + ": " + in.readUTF());
        } 
        catch (IOException e)
        {
            System.out.println("-- Connection to user lost.");
        }
        finally 
        {
            handlers.remove(this);
            try
            {
                in.close();
                out.close();
                socket.close();
            }
            catch (IOException e) {}
        }    
    }
    
    static synchronized void broadcast(String message) throws IOException
    {
        for (ChatHandler handler : handlers)
        {
            handler.out.writeUTF(message);
            handler.out.flush();
        }
    }
}
