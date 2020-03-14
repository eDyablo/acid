package org.e4d.acid.console.actor

import org.e4d.acid.actor.*
import org.e4d.acid.console.message.*
import org.e4d.acid.domain.message.*

class ConsoleActor extends Actor {
  ConsoleActor(ActorSystem actors) {
    super(actors)
    receive(
      AskForCommandMessage,
    )
  }

  PrintStream getOutput() {
    System.out
  }

  InputStream getInput() {
    System.in
  }

  void handleAskForCommandMessage(message) {
    output.print([message.prompt, ' '].join())
    final text = input.newReader().readLine()
    system.send(
      new CommandEnteredMessage(command: text),
    )
  }
}
