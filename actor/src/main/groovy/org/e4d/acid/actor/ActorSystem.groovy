package org.e4d.acid.actor

import java.util.concurrent.LinkedBlockingQueue

class ActorSystem {
  final String name
  final List<Actor> actors = []
  final Queue messageQueue = new LinkedBlockingQueue()

  ActorSystem(String name, Class[] actorTypes) {
    this.name = name
    actorTypes.collect(actors) {
      it.newInstance(this)
    }
  }

  void send(Object[] messages) {
    messages.each {
      messageQueue.add(it)
    }
  }

  void dispatch(Object[] messages) {
    send(messages)
    while(messageQueue.size()) {
      actors*.send(messageQueue.poll())
    }
  }
}
