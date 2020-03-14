package org.e4d.acid.domain.message

import groovy.transform.MapConstructor

@MapConstructor
class TextEnteredMessage implements Serializable {
  final String text
}
