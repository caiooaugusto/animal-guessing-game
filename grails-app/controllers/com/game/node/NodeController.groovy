package com.game.node

import com.game.animal.Animal
import com.game.question.Question

class NodeController {
    //delete all rows in DB to reset game data
    def resetGame() {
        //that function should be improved, not working well in all cases
        Node.executeUpdate("delete Node n")
        Configuration.executeUpdate("delete Configuration c")
        Animal.executeUpdate("delete Animal a")
        Question.executeUpdate("delete Question q")        
    }
    //that function reorder nodes in our binary tree abstraction in DB
    def syncCreatedNodes(){
        Integer currentNodeId = getCurrentNodeId()        
        def lastVisitedNode = Node.get(currentNodeId)
        //get the last two records created
        def lastInsertedNodes = Node.list(max: 2, sort: "id", order: "desc")
        def lastInsertedAnimal = lastInsertedNodes[1]
        def lastInsertedQuestion =  lastInsertedNodes[0]
        //Update last inserted question parent and growth direction with last visited node data
        Node.executeUpdate("update Node n set n.parent=$lastVisitedNode.parent, n.growthTo=$lastVisitedNode.growthTo where id=$lastInsertedQuestion.id")
        //Update last visited node parent with last inserted question id
        Node.executeUpdate("update Node n set n.parent=$lastInsertedQuestion.id, n.growthTo=1 where id=$lastVisitedNode.id")
        //Update last inserted animal parent with last inserted question id
        Node.executeUpdate("update Node n set n.parent=$lastInsertedQuestion.id where id=$lastInsertedAnimal.id")
    }
    //get root node of our b-tree abstraction in DB
    Node getRoot(){
        def node = Node.findByParentIsNull()
        return node
    }
    //get current node showed in the view
    Integer getCurrentNodeId(){
        return Configuration.first().currentNodeId
    }
    //set current node showed in the view
    def setCurrentNodeId(node){
        def count = Configuration.countByIdIsNotNull()
        if (count && node) {
            Configuration.executeUpdate("delete Configuration c")
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
    //get first question and render to view
    def getFirstQuestion() {
        //get root node associated with first question
        def rootNode = getRoot()
        //set current node in data base
        setCurrentNodeId(rootNode)
        //get the question with root node id
        def firstQuestion = Question.get(rootNode.question.id)
        //render question text to template
        render(template: "message", bean: firstQuestion)
    }
    //show message in case the node is the leaf node of the tree
    def showLeafNodeMessage(boolean userButtonChoice) {
        //if user said that the suggest animal is wrong
        if (userButtonChoice) {
            //force fail, with no return or render, so remoteLink could call onFailure js function that show createAnimal dialog
        }else {
            //if user said that the suggest animal is right render view
            render("Haha I win!")
        }
    }
    //when showing message (animal name or question text) check if the node is a leaf to ask user a animal and a sentence or end game
    def showNodeMessage(node, boolean leaf, boolean userButtonChoice) {
        def animal = Animal.get(node.animal.id)
        def question = Question.get(node.question.id)
        //check if its a leaf node
        if(leaf) {
            showLeafNodeMessage(userButtonChoice)
            return
        }
        //if its not a leaf node show right message
        if(animal?.id) {
            render("The animal that you thought is the $animal.name?")
        }else {
            render("The animal $question.text ?")
        }
    }
    //when user choice the button "yes" we call need get left node
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
    //when user choice the button "no" we call need get right node
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
    //start game initial data, 1 question and 2 animals
    def initGame() {
        if (!Question.countByIdIsNotNull()) {
            def question = new Question(text: "lives in water")
            if (!question.save(flush: true)) {
                question.errors.each {
                    println it
                }
            }
        }
        if (!Animal.countByIdIsNotNull()) {
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
        }
        if(!Node.countByIdIsNotNull()){
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
