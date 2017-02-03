package com.game.animal

import com.game.node.Node

class Animal {

    int animalId
    String name

    static belongsTo = [nodeId:Node]

    static constraints = {
         animalId unique: true
    }
}
