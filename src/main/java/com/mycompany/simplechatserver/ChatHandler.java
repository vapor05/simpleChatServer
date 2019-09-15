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
}
