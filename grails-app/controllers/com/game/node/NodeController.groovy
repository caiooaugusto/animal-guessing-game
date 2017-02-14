package com.game.node

import com.game.animal.Animal
import com.game.question.Question

class NodeController {

    def resetGame(){

        Configuration.executeUpdate("delete Configuration")
        Node.executeUpdate("delete Node")
        Animal.executeUpdate("delete Animal")
        Question.executeUpdate("delete Question")
// Agency.where { }.deleteAll()

    }

    def syncCreatedNodes(){
        def currentNodeId = Configuration.first().currentNodeId
        def lastVisitedNode = Node.get(currentNodeId)

        def lastInsertedNodes = Node.list(max: 2, sort: "id", order: "desc")
        def lastInsertedAnimal = lastInsertedNodes[1]
        def lastInsertedQuestion =  lastInsertedNodes[0]

        //Update last inserted question parent with last visited node parent
        Node.executeUpdate("update Node n set n.parent=$lastVisitedNode.parent.id where id=$lastInsertedQuestion.id")

        //Update last visited node parent with last inserted question id
        Node.executeUpdate("update Node n set n.parent=$lastInsertedQuestion.id where id=$lastVisitedNode.id")

        //Update last inserted animal parent with last inserted question id
        Node.executeUpdate("update Node n set n.parent=$lastInsertedQuestion.id where id=$lastInsertedAnimal.id")

        return true
    }

    Node getRoot(){
        def node = Node.findByParentIsNull()
        return node
    }

    Integer getCurrentNodeId(){
        return Configuration.first().currentNodeId
    }

    def setCurrentNodeId(node){
        def count = Configuration.countByIdIsNotNull()
        if (count) {
            //Configuration.executeUpdate("update Configuration set current_node_id = ? where id = ?", [node.id, 1])
            //Configuration.executeUpdate("update Configuration set current_node_id = $node.id where id = 1")
            Configuration.executeUpdate("delete Configuration c")
            //Configuration.executeUpdate("update Configuration c set c.currentNodeId=:newCurrentNode where c.id=1", [newCurrentNode: node.id])
            def currentNode = new Configuration(currentNodeId: node.id)
            if (!currentNode.save(flush: true)) {
                currentNode.errors.each {
                    println it
                }
            }
        }else{
            def currentNode = new Configuration(currentNodeId: 1)
            if (!currentNode.save(flush: true)) {
                currentNode.errors.each {
                    println it
                }
            }
        }
    }

    def getFirstQuestion() {
        //get root node associated with first question
        def rootNode = getRoot()
        //set current node in data base
        setCurrentNodeId(rootNode)
        //get the question with root node id
        def firstQuestion = Question.get(rootNode.id)
        //render question text to template
        render(template: "message", bean: firstQuestion)
    }

    def showLeafNodeMessage(boolean userButtonChoice) {
        if (userButtonChoice) {
            //force fail, with no return or render, so remoteLink could call onFailure js function that show createAnimal dialog
        }else {
            render("Haha I win!")
        }
    }

    def showNodeMessage(node, boolean leaf, boolean userButtonChoice) {
        def animal = Animal.get(node.animal.id)
        def question = Question.get(node.question.id)

        if(leaf) {
            showLeafNodeMessage(userButtonChoice)
            return
        }

        if(animal?.id) {
            render("The animal that you thought is the $animal.name?")
        }else {
            render("The animal $question.text ?")
        }
    }

    def getLeftNode() {
        def currentNodeId = getCurrentNodeId()
        def leftNode = Node.executeQuery("from Node where parent_id = ? and growth_to = ?", [currentNodeId, 0])
        if(!leftNode.empty) {
            setCurrentNodeId(leftNode)
            showNodeMessage(leftNode, false, false)
        }else{
            showNodeMessage(leftNode, true, false)
        }
    }

    def getRightNode() {
        def currentNodeId = getCurrentNodeId()
        def rightNode = Node.executeQuery("from Node where parent_id = ? and growth_to = ?", [currentNodeId, 1])
        //if its not empty set the current node and show node message
        if(!rightNode.empty) {
            setCurrentNodeId(rightNode)
            showNodeMessage(rightNode, false, true)
        }else{ //else it is a leaf node and we need to know if we win or ask for a question
            showNodeMessage(rightNode, true, true)
        }
    }

    def initGame() {
        if (!Node.exists(1)) {
            def question = new Question(text: "lives in water?")
            if (!question.save(flush: true)) {
                question.errors.each {
                    println it
                }
            }
            def shark = new Animal(name: "Shark")
            if (!shark.save(flush: true)) {
                shark.errors.each {
                    println it
                }
            }
            def monkey = new Animal(name: "Monkey")
            if (!monkey.save(flush: true)) {
                monkey.errors.each {
                    println it
                }
            }
            def root = new Node(parent: null, animal: null, question: 1, growthTo: null)
            if (!root.save(flush: true)) {
                root.errors.each {
                    println it
                }
            }
            def nodeLeft = new Node(parent: 1, animal: 1, question: null, growthTo: 0)
            if (!nodeLeft.save(flush: true)) {
                nodeLeft.errors.each {
                    println it
                }
            }
            def nodeRight = new Node(parent: 1, animal: 2, question: null, growthTo: 1)
            if (!nodeRight.save(flush: true)) {
                nodeRight.errors.each {
                    println it
                }
            }
        }
        Configuration.executeUpdate("delete Configuration c")
    }
}
