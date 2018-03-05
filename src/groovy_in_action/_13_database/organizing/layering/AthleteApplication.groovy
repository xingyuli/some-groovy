package groovy_in_action._13_database.organizing.layering

class AthleteApplication {

    /* initialization */
    def helper = new DbHelper()
    def athleteDao = new AthleteDao(db: helper.db)
    def sortBy = 'athleteId'
    def done = false

    def init() { helper.executeDdl(athleteDao) }

    def exit() { done = true }

    def sort(field) {
        sortBy = field
        list()
    }

    def create(first, last = null, dob = null) {
        athleteDao.create([first, last, dob])
        list()
    }

    def update(id, field, newValue) {
        def count = athleteDao.update(field, newValue, id)
        println count + ' row(s) updated'
        list()
    }

    def delete(id) {
        def count = athleteDao.delete(id)
        println count + ' row(s) deleted'
        list()
    }

    def list() {
        def athletes = athleteDao.all(sortBy)
        println athletes.size() + ' Athlete(s) in DB: '
        println 'id firstname  lastname     dateOfBirth'
        athletes.each { athlete ->
            println athlete.athleteId + ': ' +
                    athlete.firstname.padRight(10) + ' ' +
                    athlete.lastname.padRight(12) + ' ' +
                    athlete.dateOfBirth
        }
    }

    def mainLoop() { // Entry point after initialization.
        def reader = System.in.newReader()
        while (!done) {
            println '\ncommands: create list update delete sort exit'
            // Commands are provided as methods, then arguments.
            def input = reader.readLine().tokenize()
            def method = input.remove(0)
            this."$method"(*input)
        }
    }

}
