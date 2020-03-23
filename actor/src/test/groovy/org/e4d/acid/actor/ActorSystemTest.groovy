package org.e4d.acid.actor

import org.junit.*
import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*

class ActorSystemTest {
  final system = new ActorSystem('system')

  @Test void system_self_message_has_the_system_as_recipient_and_sender() {
    final String selector = system.selector
    assertThat(system.selfMessage(ActorMessage), allOf(
      hasProperty('sender', equalTo(selector)),
      hasProperty('recipient', equalTo(selector)),
    ))
  }

  @Test void selector_has_system_property_starts_with_the_system_name() {
    assertThat(system.selector,
      hasProperty('system', startsWith('system'))
    )
  }
}