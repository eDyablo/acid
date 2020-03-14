package org.e4d.acid.monitor

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*

class Monitor {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'monitor',
      StandardOutputActor,
    )
    actors.dispatch(
      new DisplayTextMessage(text: "acid ${ actors.name } 1.0.0"),
    )
  }
}
