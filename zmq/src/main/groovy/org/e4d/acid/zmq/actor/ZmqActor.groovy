package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.*
import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket

class ZmqActor extends Actor {
  final ZContext context = new ZContext()
  final Map<String, Socket> sockets = [:]

  ZmqActor(ActorSystem system) {
    super(system)
  }

  protected Socket getSocket(Integer type, String address) {
    sockets.get(getSocketId(type, address)) ?: createSocket(type, address)
  }

  private String getSocketId(Integer type, String address) {
    [type.toString(), address].join(':')
  }

  private Socket createSocket(Integer type, String address) {
    final socket = context.createSocket(type)
    switch(type) {
      case ZMQ.PUB:
        socket.bind(address)
      break
      case ZMQ.SUB:
        socket.connect(address)
      break
    }
    sockets.put(getSocketId(type, address), socket)
    return socket
  }
}
