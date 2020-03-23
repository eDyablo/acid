package org.e4d.acid.actor

import groovy.transform.*

@EqualsAndHashCode
class ActorSelector {
  final String namespace
  final String system
  final String actor

  static ActorSelector fromText(String text) {
    final def (String namespace, String system, String actor) =
      text.split('/') + ['*', '*', '*']
    new ActorSelector(namespace: namespace, system: system, actor: actor)
  }

  ActorSelector(Map options=[:]) {
    namespace = options.namespace ?: '*'
    system = options.system ?: '*'
    actor = options.actor ?: '*'
  }

  @Override
  String toString() {
    [
      namespace, system, actor
    ].join('/')
  }

  ActorSelector ofActor(String actor) {
    new ActorSelector(namespace: namespace, system: system, actor: actor)
  }
}
