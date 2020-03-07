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
      ReceiveZmqStringMessage,
      ZmqStringReceivedMessage,
    )
  }

  private Socket getSocket(String address) {
    sockets.get(address) ?: createSocket(address)
  }

  private Socket createSocket(String address) {
    final socket = context.createSocket(ZMQ.REP)
    socket.bind(address)
    sockets.put(address, socket)
    return socket
  }

  void handleReceiveZmqStringMessage(message) {
    final socket = getSocket(message.address)
    system.send(new ZmqStringReceivedMessage(string: socket.recvStr()))
  }
}
