package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.*
import org.e4d.acid.zmq.message.*
import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket

class ZmqSocketActor extends Actor {
  final ZContext context = new ZContext()
  final Map<String, Socket> sockets = [:]

  ZmqSocketActor(ActorSystem system) {
    super(system)
    receive(
      ReceiveZmqTextMessage,
      SendZmqTextMessage,
    )
  }

  private Socket createReplaySocket(String address) {
    final socket = context.createSocket(ZMQ.REP)
    socket.bind(address)
    sockets.put(address, socket)
    return socket
  }

  private Socket createRequestSocket(String address) {
    final socket = context.createSocket(ZMQ.REQ)
    socket.connect(address)
    sockets.put(address, socket)
    return socket
  }

  void handleReceiveZmqTextMessage(message) {
    final socket = sockets.get(message.address) ?: createReplaySocket(message.address)
    final text = new String(socket.recv(0), ZMQ.CHARSET)
  }

  void handleSendZmqTextMessage(message) {
    final socket = sockets.get(message.address) ?: createRequestSocket(message.address)
    socket.send(message.text.getBytes(ZMQ.CHARSET), 0)
  }
}
