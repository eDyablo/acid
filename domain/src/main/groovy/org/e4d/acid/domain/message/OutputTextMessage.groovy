package org.e4d.acid.domain.message

import groovy.transform.*
import org.e4d.acid.actor.ActorMessage

@MapConstructor(pre={ super(args) })
@ToString(includeSuperProperties=true)
class OutputTextMessage extends ActorMessage {
  final String text
}
