package org.e4d.acid.console

import org.e4d.acid.actor.*
import org.e4d.acid.console.actor.*
import org.e4d.acid.console.message.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*
import org.e4d.acid.zmq.message.*

class Console {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'console',
      StandardOutputActor,
      CommandActor,
      ConsoleActor,
    )
    actors.dispatch(
      new OutputTextMessage(text: "acid ${ actors.name } 1.0.0\n"),
      new OutputTextMessage(text: 'type \'help\'\n'),
      new AskForCommandMessage(),
    )
  }
}
