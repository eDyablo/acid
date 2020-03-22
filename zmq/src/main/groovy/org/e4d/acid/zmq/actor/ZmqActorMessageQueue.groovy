package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.ActorMessage
import org.e4d.acid.actor.ActorMessageQueue
import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket

class ZmqActorMessageQueue implements ActorMessageQueue {
  final ZContext context = new ZContext()
  final Socket incoming
  final Socket outgoing

  ZmqActorMessageQueue(Map options) {
    incoming = context.createSocket(ZMQ.SUB)
    incoming.connect(options.incoming)
    incoming.subscribe('')
    outgoing = context.createSocket(ZMQ.PUB)
    outgoing.bind(options.outgoing)
  }

  void enqueue(ActorMessage message) {
    outgoing.send(serialized(message))
  }

  ActorMessage dequeue() {
    final parcel = incoming.recv(0)
    if (parcel) {
      deserialized(parcel)
    }
  }

  boolean hasMessages() {
    true
  }

  byte[] serialized(ActorMessage message) {
    new ByteArrayOutputStream().withStream { buffer ->
      new ObjectOutputStream(buffer).withStream { stream ->
        stream.writeObject(message)
      }
      buffer.toByteArray()
    }
  }

  ActorMessage deserialized(byte[] bytes) {
    new ByteArrayInputStream(bytes).withStream { buffer ->
      new ObjectInputStream(buffer).withStream { stream ->
        stream.readObject() as ActorMessage
      }
    }
  }
}
