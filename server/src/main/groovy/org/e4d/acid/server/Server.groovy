package org.e4d.acid.server

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*

class Server {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'server',
      ConsoleDisplayActor,
    )
    actors.dispatch(
      new DisplayTextMessage(text: 'acid server 1.0.0')
    )
  }
}
