package com.game.node

import com.game.animal.Animal
import com.game.question.Question

class Node {

    int nodeId
    int parentId
    NodeDirection growthTo
    
    static hasOne = [animalId:Animal, questionId:Question]

    static constraints = {
        nodeId unique: true
    }
}
