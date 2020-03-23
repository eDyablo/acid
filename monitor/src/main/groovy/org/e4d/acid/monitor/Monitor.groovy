package org.e4d.acid.monitor

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*

class Monitor {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'monitor',
      StandardOutputActor,
      queue: new ZmqActorMessageQueue(
        incoming: 'tcp://localhost:6002',
        outgoing: 'tcp://localhost:6001',
      )
    )
    actors.dispatch(
      actors.selfMessage(OutputTextMessage, text: "acid ${ actors.name } 1.0.0\n"),
    )
  }
}
