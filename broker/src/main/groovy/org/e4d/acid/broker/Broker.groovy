package org.e4d.acid.broker

import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket

class Broker {
  final context = new ZContext()

  static void main(String[] args) {
    new Broker().run()
  }

  void run() {
    ZMQ.proxy(frontend, backend, capture)
  }

  Socket getFrontend() {
    final socket = context.createSocket(ZMQ.XSUB)
    socket.bind('tcp://*:5551')
    return socket
  }

  Socket getBackend() {
    final socket = context.createSocket(ZMQ.XPUB)
    socket.bind('tcp://*:5552')
    return socket
  }

  Socket getCapture() {
  }
}
