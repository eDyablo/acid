package org.e4d.acid.zmq.message

import groovy.transform.MapConstructor

@MapConstructor
class ZmqStringReceivedMessage {
  final String string
}
