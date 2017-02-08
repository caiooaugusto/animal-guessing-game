package com.game.node



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NodeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Node.list(params), model:[nodeInstanceCount: Node.count()]
    }

    def show(Node nodeInstance) {
        respond nodeInstance
    }

    def create() {
        respond new Node(params)
    }

    @Transactional
    def save(Node nodeInstance) {
        if (nodeInstance == null) {
            notFound()
            return
        }

        if (nodeInstance.hasErrors()) {
            respond nodeInstance.errors, view:'create'
            return
        }

        nodeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'node.label', default: 'Node'), nodeInstance.id])
                redirect nodeInstance
            }
            '*' { respond nodeInstance, [status: CREATED] }
        }
    }

    def edit(Node nodeInstance) {
        respond nodeInstance
    }

    @Transactional
    def update(Node nodeInstance) {
        if (nodeInstance == null) {
            notFound()
            return
        }

        if (nodeInstance.hasErrors()) {
            respond nodeInstance.errors, view:'edit'
            return
        }

        nodeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Node.label', default: 'Node'), nodeInstance.id])
                redirect nodeInstance
            }
            '*'{ respond nodeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Node nodeInstance) {

        if (nodeInstance == null) {
            notFound()
            return
        }

        nodeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Node.label', default: 'Node'), nodeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'node.label', default: 'Node'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
