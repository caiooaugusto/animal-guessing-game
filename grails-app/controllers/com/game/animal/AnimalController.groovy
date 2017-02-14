package com.game.animal

import com.game.node.Configuration
import com.game.node.Node

class AnimalController {
    //get last animal that was suggested that user thought
    String getLastVisitedAnimalName(){
        def currentNodeId = Configuration.first().currentNodeId
        def lastVisitedNode = Node.get(currentNodeId)
        return Animal.get(lastVisitedNode.animal.id).name
    }
    //create the animal that user thought and render the sentence for user complete
    def createAnimal(){
        //save animal in DB
        def animal = new Animal(name: params.name)
        if (!animal.save(flush: true)) {
            animal.errors.each {
                println it
            }
        }
        //save node with animal reference in DB
        def animalNode = new Node(parent: null, animal: animal, question: null, growthTo: 0)
        if (!animalNode.save(flush: true)) {
            animalNode.errors.each {
                println it
            }
        }
        //render the sentence to user complete
        def lastVisitedAnimalName = getLastVisitedAnimalName()
        render("The $params.name ____, but the $lastVisitedAnimalName not!")
    }
}
