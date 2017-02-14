/**
 *  Author: Caio Augusto Peixer da Silva
 *  Class description:
 *
 * */

package com.game.question

import com.game.node.Node

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
