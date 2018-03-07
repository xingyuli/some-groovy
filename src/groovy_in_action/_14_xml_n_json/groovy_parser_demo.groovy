package groovy_in_action._14_xml_n_json

// Parsing in one line
def plan = new XmlParser().parse(new File('data/plan.xml'))

assert plan.name() == 'plan'
assert plan.week[0].name() == 'week'
def firstTask = plan.week[0].task[0] // Referring to a node
assert firstTask.name() == 'task'
assert firstTask.text() == 'easy'
assert firstTask.@title == 'read XML chapter'

/* common methods of groovy.util.Node and GPathResult */

plan = new XmlSlurper().parse(new File('data/plan.xml'))

assert plan.week.task.size() == 5 // Five tasks in total
assert plan.week.task.@done*.toInteger().sum() == 6 // Six hours done so far
assert plan.week[1].task.every { it.@done == '0' } // No hours done for second week

// Breadth- and depth-first traversal
assert plan.breadthFirst()*.name().join('->') ==
        'plan->week->week->task->task->task->task->task'
assert plan.depthFirst()*.name().join('->') ==
        'plan->week->task->task->task->week->task->task'
assert plan.depthFirst()*.name() == plan.'**'*.name()


/* differences between XmlParser and XmlSlurper */


