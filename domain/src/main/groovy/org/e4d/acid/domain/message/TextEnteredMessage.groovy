package org.e4d.acid.domain.message

import groovy.transform.MapConstructor
import org.e4d.acid.actor.ActorMessage

@MapConstructor
class TextEnteredMessage extends ActorMessage {
  final String text
}
