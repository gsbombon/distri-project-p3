(function (window, document, JSON)
{
    'use strict';

    var url = 'ws://' + window.location.host + '/prj_chat_websocket/chat',
            ws = new WebSocket(url),
            mensajes = document.getElementById('conversacion'),
            boton = document.getElementById('btn_enviar'),
            mensaje = document.getElementById('mensaje');

    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    boton.addEventListener('click', enviar);

    function onOpen() {
        console.log('Conectado');
    }

    function onClose() {
        console.log('Desconectado');
    }

    function enviar() {
        var msg = {
            nombre: 'Distri Bot',
            mensaje: mensaje.value
        };
        ws.send(JSON.stringify(msg));
    }
    function onMessage(evt) {
        var obj = JSON.parse(evt.data),
                msg = '<h5>' + obj.nombre + ' </h5><p>' + obj.mensaje + '</p>';
        mensajes.innerHTML += '<div class="chat-img"> \n\
                                    <img alt="Avtar" src="https://bootdey.com/img/Content/avatar/avatar2.png"></img> \n\
                                </div> \n\
                                <div class="chat-body"> \n\
                                    <div class="chat-message">' + msg + '</div> </div>';
    }
})(window, document, JSON);