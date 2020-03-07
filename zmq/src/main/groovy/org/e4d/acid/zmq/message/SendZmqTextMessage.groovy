package org.e4d.acid.zmq.message

import groovy.transform.MapConstructor

@MapConstructor
class SendZmqTextMessage {
  final String address
  final String text
}
