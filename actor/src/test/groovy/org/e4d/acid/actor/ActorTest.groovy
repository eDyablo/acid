package org.e4d.acid.actor

import org.junit.*
import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*
import static org.mockito.Mockito.*

class ActorTest {
  final system = mock(ActorSystem)
  final actor = spy(new Actor(system))

  @Test void selector_has_system_property_same_as_for_system_selector() {
    doReturn(new ActorSelector(system: 'system')).when(system).selector
    assertThat(actor.selector, allOf(
      hasProperty('system', equalTo('system')),
    ))
  }

  @Test void selector_has_actor_property_equal_to_hex_representation_of_the_actor_hash_code() {
    doReturn(new ActorSelector(system: 'system')).when(system).selector
    assertThat(actor.selector, allOf(
      hasProperty('actor', equalTo(Integer.toHexString(actor.hashCode()))),
    ))
  }
}
