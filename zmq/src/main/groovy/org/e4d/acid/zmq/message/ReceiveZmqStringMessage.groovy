package org.e4d.acid.zmq.message

import groovy.transform.MapConstructor

@MapConstructor
class ReceiveZmqStringMessage {
  final String address
}
