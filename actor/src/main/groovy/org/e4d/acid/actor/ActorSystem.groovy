package org.e4d.acid.actor

class ActorSystem {
  final String name
  final List<Actor> actors = []
  final ActorMessageQueue messageQueue = new SimpleActorMessageQueue()

  ActorSystem(String name, Class[] actorTypes) {
    this.name = name
    actorTypes.collect(actors) {
      it.newInstance(this)
    }
  }

  void send(Object[] messages) {
    messages.each {
      messageQueue.enqueue(it)
    }
  }

  void dispatch(Object[] messages) {
    send(messages)
    while(messageQueue.hasMessages()) {
      actors*.send(messageQueue.dequeue())
    }
  }
}
