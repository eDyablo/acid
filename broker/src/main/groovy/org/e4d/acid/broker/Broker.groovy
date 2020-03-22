package org.e4d.acid.broker

import org.zeromq.ZContext
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket
import org.zeromq.ZThread

class Broker {
  final context = new ZContext()
  final Socket capture
  final Socket incoming
  final Socket outgoing

  static void main(String[] args) {
    new Broker(
      incoming: 'tcp://*:6001',
      outgoing: 'tcp://*:6002',
    ).run()
  }

  Broker(Map options) {
    incoming = context.createSocket(ZMQ.PULL)
    incoming.bind(options.incoming)
    outgoing = context.createSocket(ZMQ.XPUB)
    outgoing.bind(options.outgoing)
    capture = ZThread.fork(context, new Listener())
  }

  void run() {
    ZMQ.proxy(incoming, outgoing, capture)
  }
}
