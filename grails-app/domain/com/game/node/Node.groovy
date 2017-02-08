/**
 *  Author: Caio Augusto Peixer da Silva
 *  Class description:
 *
 * */

package com.game.node

import com.game.animal.Animal
import com.game.question.Question

class Node {

    Node parent
    Animal animal
    Question question
    enum Direction {
        left, right
    }
    Direction growthTo

    static constraints = {
        id unique: true
        parent blank: false, nullable: true
        growthTo blank: false
    }

    static mapping = {
        table "node"
        id column: "id"
        parent column: "parent_id"
        animal column: "animal_id"
        question column: "question_id"
        growthTo column: "growth_to"
        version false
    }
}
