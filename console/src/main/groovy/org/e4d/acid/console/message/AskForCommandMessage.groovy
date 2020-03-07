package org.e4d.acid.console.message

import groovy.transform.MapConstructor

@MapConstructor
class AskForCommandMessage {
  final String prompt = '>'
}
