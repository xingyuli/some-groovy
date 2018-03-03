package groovy_in_action._12_gdk

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

// Creates a new method to get thread name
Thread.metaClass.static.getName = { Thread.currentThread().name }

// Creates shared queue
BlockingQueue sharedQueue = [] as LinkedBlockingQueue

// Starts thread producing 10 items
Thread.start('push') {
    10.times {
        try {
            println "${Thread.name}\t: $it"
            sharedQueue << it
            sleep 100
        } catch (InterruptedException ignore) {}
    }
}

// Starts thread consuming 10 items
Thread.start('pop') {
    for (i in 0..9) {
        sleep 200
        println "${Thread.name}\t: ${sharedQueue.take()}"
    }
}
