package org.e4d.acid.actor

import java.util.concurrent.LinkedBlockingQueue

class SimpleActorMessageQueue implements ActorMessageQueue {
  final Queue queue = new LinkedBlockingQueue<ActorMessage>()

  void enqueue(ActorMessage message) {
    queue.add(message)
  }

  ActorMessage dequeue() {
    queue.poll()
  }

  boolean hasMessages() {
    queue.size() > 0
  }
}
