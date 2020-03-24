package org.e4d.acid.monitor

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*

class Monitor {
  final ActorSystem actors

  static void main(String[] args) {
    new Monitor(
      incoming: 'tcp://localhost:6002',
      outgoing: 'tcp://localhost:6001',
    ).run()
  }

  Monitor(Map options) {
    actors = new ActorSystem(
      'monitor',
      StandardOutputActor,
      queue: new ZmqActorMessageQueue(
        incoming: options.incoming,
        outgoing: options.outgoing,
      )
    )
  }

  void run() {
    actors.dispatch(
      actors.selfMessage(OutputTextMessage, text: "acid ${ actors.name } 1.0.0\n"),
    )
  }
}
