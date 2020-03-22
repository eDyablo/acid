package org.e4d.acid.console

import org.e4d.acid.actor.*
import org.e4d.acid.console.actor.*
import org.e4d.acid.console.message.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*

class Console {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'console',
      StandardOutputActor,
      CommandActor,
      ConsoleActor,
      queue: new ZmqActorMessageQueue(
        incoming: 'tcp://localhost:6002',
        outgoing: 'tcp://localhost:6001',
      )
    )
    actors.dispatch(
      new OutputTextMessage(text: "acid ${ actors.name } 1.0.0\n"),
      new OutputTextMessage(text: 'type \'help\'\n'),
      new AskForCommandMessage(),
    )
  }
}
