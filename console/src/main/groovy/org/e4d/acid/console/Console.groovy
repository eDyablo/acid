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
      CommandActor,
      ConsoleActor,
      ZmqSocketActor,
    )
    actors.dispatch(
      new DisplayTextMessage(text: 'acid console 1.0.0'),
      new DisplayTextMessage(text: 'type \'help\''),
      new SendZmqTextMessage(address: 'tcp://localhost:5555', text: 'hello'),
      new AskForCommandMessage(),
    )
  }
}
