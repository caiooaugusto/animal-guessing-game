/**
 *  Author: Caio Augusto Peixer da Silva
 *  Class description:
 *
 * */

package com.game.animal

import com.game.node.Node

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
