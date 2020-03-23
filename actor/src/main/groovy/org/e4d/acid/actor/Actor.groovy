package org.e4d.acid.actor

class Actor {
  final ActorSystem system
  final List<Class> messageTypes = []
  final ActorSelector selector

  Actor(ActorSystem system) {
    this.system = system
    selector = system.selector.ofActor(
      Integer.toHexString(System.identityHashCode(this))
    )
  }

  void receive(Class[] messageTypes) {
    messageTypes.collect(this.messageTypes) { it }
  }

  void dispatch(ActorMessage[] messages) {
    handle(messages.findAll {
      canHandle(it.class)
    })
  }

  boolean canHandle(Class<?> type) {
    type in messageTypes
  }

  void handle(List<ActorMessage> messages) {
    messages.each {
      handle(it)
    }
  }

  void handle(ActorMessage message) {
    "handle${ message.class.simpleName }"(message)
  }
}
