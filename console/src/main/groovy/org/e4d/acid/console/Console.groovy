package org.e4d.acid.console

import org.e4d.acid.actor.*
import org.e4d.acid.console.actor.*
import org.e4d.acid.console.message.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*

class Console {
  final ActorSystem actors

  static void main(String[] args) {
    new Console(
      incoming: 'tcp://localhost:6002',
      outgoing: 'tcp://localhost:6001',
    ).run()
  }

  Console(Map options) {
    actors = new ActorSystem(
      'console',
      StandardOutputActor,
      CommandActor,
      ConsoleActor,
      queue: new ZmqActorMessageQueue(
        incoming: options.incoming,
        outgoing: options.outgoing,
      )
    )
  }

  void run() {
    actors.dispatch(
      actors.selfMessage(OutputTextMessage, text: "acid ${ actors.name } 1.0.0\n"),
      actors.selfMessage(OutputTextMessage, text: 'type \'help\'\n'),
      actors.selfMessage(AskForCommandMessage),
    )
  }
}
