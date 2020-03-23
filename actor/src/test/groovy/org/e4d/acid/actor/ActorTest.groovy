package org.e4d.acid.actor

import org.junit.*
import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.*

class ActorTest {
  final system = new ActorSystem('system')
  final actor = new Actor(system)

  @Test void selector_has_system_property_same_as_for_system_selector() {
    assertThat(actor.selector.system, is(equalTo(system.selector.system)))
  }

  @Test void selector_has_namespace_property_same_as_for_system_selector() {
    assertThat(actor.selector.namespace,
      is(equalTo(system.selector.namespace)))
  }

  @Test void selector_has_actor_property_equal_to_hex_representation_of_the_actor_hash_code() {
    assertThat(actor.selector.actor,
      is(equalTo(Integer.toHexString(actor.hashCode()))))
  }
}
