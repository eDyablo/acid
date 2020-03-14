package org.e4d.acid.console.message

import groovy.transform.MapConstructor

@MapConstructor
class CommandEnteredMessage implements Serializable {
  final String command
}
