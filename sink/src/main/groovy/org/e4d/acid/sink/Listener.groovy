package org.e4d.acid.sink

import org.zeromq.ZContext
import org.zeromq.ZFrame
import org.zeromq.ZMQ.Socket
import org.zeromq.ZThread.IAttachedRunnable

class Listener implements IAttachedRunnable
{
  final String prefix = this.class.getPackage().name

  @Override
  void run(Object[] args, ZContext ctx, Socket pipe)
  {
    while (true) {
      ZFrame frame = ZFrame.recvFrame(pipe)
      if (frame == null)
        break
      frame.print(prefix)
      frame.destroy()
    }
  }
}
