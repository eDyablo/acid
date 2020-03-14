package org.e4d.acid.domain.message

import groovy.transform.MapConstructor

@MapConstructor
class DisplayTextMessage implements Serializable {
  final String text
}
