package com.game.question

import com.game.node.Node

class Question {

    int questionId
    String text

    static belongsTo = [nodeId:Node]

    static constraints = {
        questionId unique: true
    }
}
