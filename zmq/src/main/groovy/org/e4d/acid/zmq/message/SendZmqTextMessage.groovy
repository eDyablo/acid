package org.e4d.acid.zmq.message

import groovy.transform.MapConstructor

@MapConstructor
class SendZmqTextMessage implements Serializable {
  final String address
  final String text
}
