package org.e4d.acid.console.actor

import org.e4d.acid.actor.*
import org.e4d.acid.console.message.*
import org.e4d.acid.domain.message.*

class CommandActor extends Actor {
  CommandActor(ActorSystem system) {
    super(system)
    receive(
      CommandEnteredMessage,
    )
  }

  void handleCommandEnteredMessage(message) {
    if (message.command != null && message.command != 'quit') {
      switch(message.command) {
        case 'help':
          system.send(new OutputTextMessage(text: '''\
          Commands:
            help    Display help information
            quit    Exit the application
          '''.stripIndent()))
        break
      }
      system.send(new AskForCommandMessage())
    }
  }
}
