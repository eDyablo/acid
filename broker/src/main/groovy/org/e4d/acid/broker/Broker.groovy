package org.e4d.acid.broker

import org.zeromq.ZContext
import org.zeromq.ZFrame
import org.zeromq.ZMQ
import org.zeromq.ZMQ.Socket
import org.zeromq.ZThread
import org.zeromq.ZThread.IAttachedRunnable

class Listener implements IAttachedRunnable
{
  @Override
  void run(Object[] args, ZContext ctx, Socket pipe)
  {
    while (true) {
      ZFrame frame = ZFrame.recvFrame(pipe);
      if (frame == null)
        break;
      frame.print(null);
      frame.destroy();
    }
  }
}

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
    socket.connect('tcp://localhost:6001')
    return socket
  }

  Socket getBackend() {
    final socket = context.createSocket(ZMQ.XPUB)
    socket.bind('tcp://*:6002')
    return socket
  }

  Socket getCapture() {
    ZThread.fork(context, new Listener())
  }
}
