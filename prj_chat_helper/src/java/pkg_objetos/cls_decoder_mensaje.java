package pkg_objetos;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class cls_decoder_mensaje implements Decoder.TextStream<cls_mensaje> {

    @Override
    public cls_mensaje decode(Reader reader) throws DecodeException, IOException {
        cls_mensaje mensaje = new cls_mensaje();
        try (JsonReader jsonreader = Json.createReader(reader)) {
            JsonObject json = jsonreader.readObject();
            mensaje.setNombre(json.getString("nombre"));
            mensaje.setMensaje(json.getString("mensaje"));
        }
        return mensaje;
    }

    @Override
    public void init(EndpointConfig config) {
        
    }

    @Override
    public void destroy() {
        
    }

}
