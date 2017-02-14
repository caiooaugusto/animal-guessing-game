package com.game.animal

import com.game.node.Node
//this class is used when node is an animal and need a name
class Animal {

    Integer id
    String name

    static constraints = {
        id unique: true
        name blank: false
    }

    static mapping = {
        table "animal"
        id column: "id"
        name column: "name"
        version false
    }
}
