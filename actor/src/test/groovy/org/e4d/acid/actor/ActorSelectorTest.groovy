package org.e4d.acid.actor

import org.junit.*
import static org.hamcrest.MatcherAssert.*
import static org.hamcrest.Matchers.*

class ActorSelectorTest {
  @Test void is_global_selector_by_default() {
    assertThat(new ActorSelector(), hasToString(equalTo('*/*/*')))
  }

  @Test void is_concrete_selector_when_namespace_system_and_actor_are_defined() {
    assertThat(new ActorSelector(
      namespace: 'namespace',
      system: 'system',
      actor: 'actor',
    ), hasToString(equalTo('namespace/system/actor')))
  }

  @Test void is_namespace_selector_when_only_namespace_is_defined() {
    assertThat(new ActorSelector(namespace: 'namespace'),
      hasToString(equalTo('namespace/*/*')))
  }

  @Test void is_system_selector_when_only_system_is_defined() {
    assertThat(new ActorSelector(system: 'system'),
      hasToString(equalTo('*/system/*')))
  }
}
