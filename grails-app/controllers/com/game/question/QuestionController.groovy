package com.game.question

class QuestionController {

    def createQuestion(){
        log.error(params)
        def question = new Question(text: params.text)
        if (!question.save(flush: true)) {
            question.errors.each {
                println it
            }
        }
    }
}
