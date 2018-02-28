package groovy_in_action.oo

trait HasId {
    long id
}

trait HasVersion {
    long version
}

trait Persistent {
    boolean save() { println "saving ${this.dump()}" }
}

trait Entity implements Persistent, HasId, HasVersion {
    boolean save() {
        version++
        Persistent.super.save()
    }
}

/* intrusive way */

//class Publication implements Entity {
//    String title
//}
//class Book extends Publication {
//    String isbn
//}
//
//Entity gina = new Book(id: 1, version: 1, title: 'gina', isbn: '111111')
//gina.save()
//assert gina.version == 2


/* non-intrusive way */

class Publication {
    String title
}

class Book extends Publication {
    String isbn
}

Entity gina = new Book(title: 'gina', isbn: '111111') as Entity
// Note that gina is no longer of type Book as it was before. That's the price we pay for flexibility.
gina.id = 1
gina.version = 1
gina.save()
assert gina.version == 2
