package org.e4d.acid.console.message

import groovy.transform.MapConstructor
import org.e4d.acid.actor.ActorMessage

@MapConstructor
class CommandEnteredMessage extends ActorMessage {
  final String command
}
