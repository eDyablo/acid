package org.e4d.acid.server

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*
import org.e4d.acid.zmq.actor.*
import org.e4d.acid.zmq.message.*

class Server {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'server',
      StandardOutputActor,
      ZmqSocketActor,
    )
    actors.dispatch(
      new DisplayTextMessage(text: 'acid server 1.0.0'),
      new ReceiveZmqStringMessage(address: 'tcp://*:5555')
    )
  }
}
