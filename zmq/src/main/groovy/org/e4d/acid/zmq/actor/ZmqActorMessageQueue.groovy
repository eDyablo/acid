package org.e4d.acid.zmq.actor

import org.e4d.acid.actor.ActorMessage
import org.e4d.acid.actor.ActorMessageQueue
import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket

class ZmqActorMessageQueue implements ActorMessageQueue {
  final ZContext context = new ZContext()
  final Socket incoming = context.createSocket(ZMQ.SUB)
  final Socket outgoing = context.createSocket(ZMQ.PUSH)

  ZmqActorMessageQueue(Map options) {
    incoming.connect(options.incoming)
    incoming.subscribe('')
    outgoing.connect(options.outgoing)
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

  byte[] serialized(ActorMessage message) {
    new ByteArrayOutputStream().withStream { buffer ->
      new ObjectOutputStream(buffer).withStream { stream ->
        stream.writeObject(message)
      }
      buffer.toByteArray()
    }
  }

  ActorMessage deserialized(byte[] bytes) {
    try {
      new ByteArrayInputStream(bytes).withStream { buffer ->
        new ObjectInputStream(buffer).withStream { stream ->
          stream.readObject() as ActorMessage
        }
      }
    }
    catch(ClassNotFoundException) {
    }
  }
}
