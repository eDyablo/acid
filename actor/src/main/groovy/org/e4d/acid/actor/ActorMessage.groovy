package org.e4d.acid.actor

import groovy.transform.*

@MapConstructor
@ToString
class ActorMessage implements Serializable {
  final String recipient
  final String sender
}
