package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.*
import org.zeromq.ZMQ

class ZmqPublishingActor extends ZmqActor {
  final String address = 'tcp://*:5555'

  ZmqPublishingActor(ActorSystem system) {
    super(system)
  }

  void send(Object[] messages) {
    new ByteArrayOutputStream().withStream { buffer ->
      new ObjectOutputStream(buffer).withStream { ostream ->
        messages.each { message ->
          ostream.writeObject(message)
        }
      }
      sendParcel(buffer.toByteArray())
    }
  }

  private void sendParcel(byte[] parcel) {
    final socket = getSocket(ZMQ.PUB, address)
    socket.send(parcel, 0)
  }
}
