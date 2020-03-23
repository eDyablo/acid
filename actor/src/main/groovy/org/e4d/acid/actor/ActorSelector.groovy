package org.e4d.acid.actor

import groovy.transform.*

@MapConstructor
class ActorSelector {
  final String namespace
  final String system
  final String actor

  @Override
  String toString() {
    [
      namespace, system, actor
    ].collect {
      it ?: '*'
    }.join('/')
  }
}
