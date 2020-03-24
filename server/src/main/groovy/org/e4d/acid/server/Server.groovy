package org.e4d.acid.server

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*

class Server {
  final ActorSystem actors
  
  static void main(String[] args) {
    new Server(
      incoming: 'tcp://localhost:6002',
      outgoing: 'tcp://localhost:6001',
    ).run()
  }

  Server(Map options) {
    actors = new ActorSystem(
      'server',
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
