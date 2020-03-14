package org.e4d.acid.actor

abstract class OuterSystemActor extends Actor {
  OuterSystemActor(ActorSystem system) {
    super(system)
  }

  void send(Object[] messages) {
    new ByteArrayOutputStream().withStream { buffer ->
      new ObjectOutputStream(buffer).withStream { ostream ->
        messages.each { message ->
          ostream.writeObject(message)
        }
      }
      send(buffer.toByteArray())
    }
  }

  protected abstract void send(byte[] data)
}
