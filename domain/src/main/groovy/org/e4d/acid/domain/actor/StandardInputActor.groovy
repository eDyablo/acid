package org.e4d.acid.domain.actor

import org.e4d.acid.actor.*
import org.e4d.acid.domain.message.*

class StandardInputActor extends Actor {
  StandardInputActor(ActorSystem system) {
    super(system)
    receive(InputTextMessage)
  }

  InputStream getInput() {
    System.out
  }

  void handleInputTextMessage(message) {
    system.send(
      new TextEnteredMessage(text: input.newReader().readLine())
    )
  }
}
