package org.e4d.acid.actor

class Actor {
  final ActorSystem system
  final List<Class> messageTypes = []

  Actor(ActorSystem system) {
    this.system = system
  }

  void receive(Class[] messageTypes) {
    messageTypes.collect(this.messageTypes) { it }
  }

  void dispatch(Object[] messages) {
    messages.findAll {
      canHandle(it.class)
    }.each {
      handle(it)
    }
  }

  boolean canHandle(Class<?> type) {
    type in messageTypes
  }

  void handle(Object message) {
    "handle${ message.class.simpleName }"(message)
  }
}
