package com.game.animal



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AnimalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Animal.list(params), model:[animalInstanceCount: Animal.count()]
    }

    def show(Animal animalInstance) {
        respond animalInstance
    }

    def create() {
        respond new Animal(params)
    }

    @Transactional
    def save(Animal animalInstance) {
        if (animalInstance == null) {
            notFound()
            return
        }

        if (animalInstance.hasErrors()) {
            respond animalInstance.errors, view:'create'
            return
        }

        animalInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'animal.label', default: 'Animal'), animalInstance.id])
                redirect animalInstance
            }
            '*' { respond animalInstance, [status: CREATED] }
        }
    }

    def edit(Animal animalInstance) {
        respond animalInstance
    }

    @Transactional
    def update(Animal animalInstance) {
        if (animalInstance == null) {
            notFound()
            return
        }

        if (animalInstance.hasErrors()) {
            respond animalInstance.errors, view:'edit'
            return
        }

        animalInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Animal.label', default: 'Animal'), animalInstance.id])
                redirect animalInstance
            }
            '*'{ respond animalInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Animal animalInstance) {

        if (animalInstance == null) {
            notFound()
            return
        }

        animalInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Animal.label', default: 'Animal'), animalInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'animal.label', default: 'Animal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
