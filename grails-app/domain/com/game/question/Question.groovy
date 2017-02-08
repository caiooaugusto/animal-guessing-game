package com.game.question

class Question {

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
