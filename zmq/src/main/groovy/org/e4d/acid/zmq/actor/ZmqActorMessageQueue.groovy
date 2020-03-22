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
    outgoing = context.createSocket(ZMQ.PUB)
    outgoing.bind(options.outgoing)
  }

  void enqueue(ActorMessage message) {
    println("sending ${ serialized(message).size() } bytes of ${ message } to ${ outgoing }")
    outgoing.send(serialized(message), 0)
  }

  ActorMessage dequeue() {
    println("receiving from ${ incoming }")
    final parcel = incoming.recv(ZMQ.DONTWAIT)
    if (parcel) {
      println("received ${ parcel.size() } bytes")
    }
  }

  boolean hasMessages() {
    println("asking ${ incoming }")
    return true
  }

  byte[] serialized(ActorMessage message) {
    new ByteArrayOutputStream().withStream { buffer ->
      new ObjectOutputStream(buffer).withStream { ostream ->
        ostream.writeObject(message)
      }
      buffer.toByteArray()
    }
  }
}
