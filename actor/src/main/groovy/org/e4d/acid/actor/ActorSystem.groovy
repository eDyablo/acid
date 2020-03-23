package org.e4d.acid.actor

class ActorSystem {
  final String name
  final List<Actor> actors = []
  final ActorMessageQueue messageQueue

  ActorSystem(Map options=[:], String name, Class[] actorTypes) {
    this.name = name
    messageQueue = options.queue ?: new SimpleActorMessageQueue()
    actorTypes.collect(actors) {
      it.newInstance(this)
    }
  }

  void send(ActorMessage[] messages) {
    messages.each {
      messageQueue.enqueue(it)
    }
  }

  void dispatch(ActorMessage[] messages) {
    send(messages)
    while (true) {
      actors*.dispatch(messageQueue.dequeue())
    }
  }

  ActorSelector getSelector() {
    new ActorSelector(system: name)
  }

  ActorMessage message(Map options=[:], Class<?> messageType) {
    messageType.newInstance(options + [
      sender: selector,
      recipient: selector,
    ])
  }
}
