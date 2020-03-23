package org.e4d.acid.actor

import org.junit.*
import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*

class ActorSystemTest {
  @Test void system_message_has_the_system_as_recipient_and_sender() {
    final system = new ActorSystem('system')
    final message = system.message(ActorMessage)
    final String selector = system.selector
    assertThat(message, allOf(
      hasProperty('sender', equalTo(selector)),
      hasProperty('recipient', equalTo(selector)),
    ))
  }
}