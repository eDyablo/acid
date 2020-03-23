package org.e4d.acid.console.message

import groovy.transform.*
import org.e4d.acid.actor.ActorMessage

@MapConstructor(pre={ super(args) })
@ToString(includeSuperProperties=true)
class CommandEnteredMessage extends ActorMessage {
  final String command
}
