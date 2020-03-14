package org.e4d.acid.server

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*
import org.e4d.acid.zmq.message.*

class Server extends Actor {
  Server(ActorSystem system) {
    super(system)
  }

  static void main(String[] args) {
    final actors = new ActorSystem(
      'server',
      StandardOutputActor,
      Server,
    )
    actors.dispatch(
      new OutputTextMessage(text: "acid ${ actors.name } 1.0.0\n"),
    )
  }
}
