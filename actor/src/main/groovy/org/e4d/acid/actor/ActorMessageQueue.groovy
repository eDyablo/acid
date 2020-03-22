package org.e4d.acid.actor

interface ActorMessageQueue {
  void enqueue(ActorMessage message)

  ActorMessage dequeue()

  boolean hasMessages()
}
