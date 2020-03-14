package org.e4d.acid.domain.message

import groovy.transform.MapConstructor

@MapConstructor
class OutputTextMessage implements Serializable {
  final String text
}
