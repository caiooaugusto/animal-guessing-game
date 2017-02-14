package com.game.animal

import com.game.node.Configuration
import com.game.node.Node

class AnimalController {

    String getLastVisitedAnimalName(){
        def currentNodeId = Configuration.first().currentNodeId
        def lastVisitedNode = Node.get(currentNodeId)
        return Animal.get(lastVisitedNode.animal.id).name
    }

    def createAnimal(){
        def animal = new Animal(name: params.name)
        if (!animal.save(flush: true)) {
            animal.errors.each {
                println it
            }
        }
        def lastVisitedAnimalName = getLastVisitedAnimalName()

        def animalNode = new Node(parent: null, animal: animal, question: null, growthTo: 0)
        if (!animalNode.save(flush: true)) {
            animalNode.errors.each {
                println it
            }
        }

        render("The $params.name ____, but the $lastVisitedAnimalName not!")
    }
}
