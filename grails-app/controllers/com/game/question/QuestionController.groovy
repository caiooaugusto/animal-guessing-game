package com.game.question

import com.game.node.Node

class QuestionController {

    def createQuestion(){
        def question = new Question(text: params.text)
        if (!question.save(flush: true)) {
            question.errors.each {
                println it
            }
        }

        def questionNode = new Node(parent: null, animal: null, question: question, growthTo: 1)
        if (!questionNode.save(flush: true)) {
            questionNode.errors.each {
                println it
            }
        }
        redirect(controller: "node", action: "syncCreatedNodes")
    }


}
