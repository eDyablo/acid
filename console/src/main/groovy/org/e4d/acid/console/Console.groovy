package org.e4d.acid.console

import org.e4d.acid.actor.*
import org.e4d.acid.domain.actor.*
import org.e4d.acid.domain.message.*

class Console {
  static void main(String[] args) {
    final actors = new ActorSystem(
      'console',
      ConsoleDisplayActor,
    )
    actors.dispatch(
      new DisplayTextMessage(text: 'acid console 1.0.0')
    )
  }
}
