package org.e4d.acid.domain.actor

import org.e4d.acid.actor.*
import org.e4d.acid.domain.message.*

class ConsoleDisplayActor extends Actor {
  ConsoleDisplayActor(ActorSystem system) {
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
