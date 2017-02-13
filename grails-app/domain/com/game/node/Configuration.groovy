/**
 *  Author: Caio Augusto Peixer da Silva
 *  Class description:
 *
 * */

package com.game.node

class Configuration {

    Integer id
    Integer currentNodeId

    static constraints = {
        id unique: true
        currentNodeId blank: false, nullable: false
    }

    static mapping = {
        table "configuration"
        id column: "id"
        currentNodeId column: "current_node_id"
        version false
    }
}
