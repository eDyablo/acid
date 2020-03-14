package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.*
import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket

class ZmqSystemActor extends OuterSystemActor {
  final ZContext context = new ZContext()
  final Map<String, Socket> sockets = [:]
  final String address = 'tcp://localhost:5555'

  ZmqSystemActor(ActorSystem system) {
    super(system)
  }

  void send(byte[] data) {
    final socket = sockets.get(address) ?: createRequestSocket(address)
    println socket
    socket.send('hello'.getBytes(ZMQ.CHARSET), 0)
  }

  private Socket createRequestSocket(String address) {
    final socket = context.createSocket(ZMQ.REQ)
    socket.connect(address)
    sockets.put(address, socket)
    return socket
  }
}
