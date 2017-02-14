package com.game.question

import com.game.node.Node
//this class is used when node is a question and need a text
class Question {

    Integer id
    String text

    static constraints = {
        id unique: true
        text blank: false
    }

    static mapping = {
        table "question"
        id column: "id"
        text column: "text"
        version false
    }
}
