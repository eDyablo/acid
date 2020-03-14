package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.*
import org.e4d.acid.zmq.message.*
import org.zeromq.ZMQ

class ZmqSubscriptionActor extends ZmqActor {
  ZmqSubscriptionActor(ActorSystem system) {
    super(system)
    receive(ReceiveZmqTextMessage)
  }

  void handleReceiveZmqTextMessage(message) {
    final socket = getSocket(ZMQ.SUB, message.address)
    socket.subscribe('')
    final parcel = socket.recv(ZMQ.DONTWAIT)
    if (parcel) {
      println parcel
    }
    system.send(message)
    Thread.sleep(100)
  }
}
