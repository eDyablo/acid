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

  ActorSelector ofActor(String actor) {
    new ActorSelector(namespace: namespace, system: system, actor: actor)
  }
}
