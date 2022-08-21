/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import pkg_objetos.cls_decoder_mensaje;
import pkg_objetos.cls_encoder_mensaje;
import pkg_objetos.cls_mensaje;

/**
 *
 * @author pocho
 */
@ServerEndpoint(value = "/chat", encoders = {cls_encoder_mensaje.class}, decoders = {cls_decoder_mensaje.class})
public class cls_chat {

    private static final List<Session> conectados = new ArrayList<>();

    @OnOpen
    public void inicio(Session sesion) {
        conectados.add(sesion);
    }

    @OnClose
    public void salir(Session sesion) {
        conectados.remove(sesion);
    }

    @OnMessage
    public void mensaje(cls_mensaje mensaje) throws IOException, EncodeException {
        for (Session sesion : conectados) {
            sesion.getBasicRemote().sendObject(mensaje);
        }
    }
}
