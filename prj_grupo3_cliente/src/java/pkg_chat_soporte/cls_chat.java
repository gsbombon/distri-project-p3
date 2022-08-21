package pkg_chat_soporte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


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
