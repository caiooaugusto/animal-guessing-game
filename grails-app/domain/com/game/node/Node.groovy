/**
 *  Author: Caio Augusto Peixer da Silva
 *  Class description:
 *
 * */

package com.game.node

import com.game.animal.Animal
import com.game.question.Question

class Node {

    Integer id
    Node parent
    Animal animal
    Question question
    /** By convention
     *  growthTo(0) => left
     *  growthTo(1) => right
     */
    Integer growthTo

    static constraints = {
        id unique: true
        parent blank: false, nullable: true
        animal blank: false, nullable: true
        question blank: false, nullable: true
        growthTo blank: false, nullable: true
    }

    static mapping = {
        table "node"
        id column: "id"
        parent column: "parent_id"
        parent cascade: 'all-delete-orphan'
        animal column: "animal_id"
        animal cascade: 'all-delete-orphan'
        question column: "question_id"
        question cascade: 'all-delete-orphan'
        growthTo column: "growth_to"
        version false
    }
}
