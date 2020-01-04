/**
 * Created by ZSW on 2019/3/4.
 */

var websocket;

//如果浏览器支持WebSocket
if(!window.WebSocket){
    alert("浏览器不支持WebSocket");
}
websocket = new WebSocket("ws://localhost:8090/ws");  //获得WebSocket对象

//当有消息过来的时候触发
websocket.onmessage = function(event,cb){
    console.log(event)
    cb(event.data);
};

//连接关闭的时候触发
websocket.onclose = function(event,cb){
    console.log(event);
    cb(event)
};

//连接打开的时候触发
websocket.onopen = function(event,cb){
    cb(event);
};

websocket.sendMsg = function(msg,cb){
    if(window.WebSocket){
        alert("浏览器不支持webscoket");
    }
    //如果WebSocket是打开状态
    if(websocket.readyState != WebSocket.OPEN) {
        alert("链接已关闭请刷新页面重试！");
    }
    websocket.send(msg); //send()发送消息
};
