package org.e4d.acid.server.message

import groovy.transform.MapConstructor

@MapConstructor
class ReceiveZmqStringMessage {
  final String address
}
