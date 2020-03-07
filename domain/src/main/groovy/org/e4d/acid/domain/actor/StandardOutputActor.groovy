package org.e4d.acid.domain.actor

import org.e4d.acid.actor.*
import org.e4d.acid.domain.message.*

class StandardOutputActor extends Actor {
  StandardOutputActor(ActorSystem system) {
    super(system)
    receive(DisplayTextMessage)
  }

  PrintStream getOutput() {
    System.out
  }

  void handleDisplayTextMessage(message) {
    output.println(message.text)
  }
}
