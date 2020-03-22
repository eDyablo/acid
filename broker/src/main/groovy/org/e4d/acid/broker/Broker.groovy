package org.e4d.acid.broker

import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket
import org.zeromq.ZThread

class Broker {
  final context = new ZContext()
  final Socket capture = ZThread.fork(context, new Listener())
  final Socket incoming = context.createSocket(ZMQ.PULL)
  final Socket outgoing = context.createSocket(ZMQ.XPUB)

  static void main(String[] args) {
    new Broker(
      incoming: 'tcp://*:6001',
      outgoing: 'tcp://*:6002',
    ).run()
  }

  Broker(Map options) {
    incoming.bind(options.incoming)
    outgoing.bind(options.outgoing)
  }

  void run() {
    println('acid broker 1.0.0')
    ZMQ.proxy(incoming, outgoing, capture)
  }
}
