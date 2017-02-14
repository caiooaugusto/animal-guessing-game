package com.game.question

import com.game.node.Configuration
import com.game.node.Node

class QuestionController {
    //create question and related node 
    def createQuestion(){
        def question = new Question(text: params.text)
        if (!question.save(flush: true)) {
            question.errors.each {
                println it
            }
        }

        def questionNode = new Node(parent: null, animal: null, question: question)
        if (!questionNode.save(flush: true)) {
            questionNode.errors.each {
                println it
            }
        }
        //call node controller function to reorder nodes
        redirect(controller: "node", action: "syncCreatedNodes")
    }


}
