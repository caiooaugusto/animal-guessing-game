package com.game.node

import com.game.animal.Animal
import com.game.question.Question
//this class is used for out binary tree abstraction in DB and could contain a question or an animal associated
class Node {

    Integer id
    Integer parent
    Animal animal
    Question question
    /** By convention
     *  growthTo(0) => left
     *  growthTo(1) => right
     *  We need this variable to know from what direction de binary tree is growing
     */
    Integer growthTo

    static constraints = {
        id unique: true
        parent blank: true, nullable: true
        animal blank: true, nullable: true
        question blank: true, nullable: true
        growthTo blank: true, nullable: true
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
