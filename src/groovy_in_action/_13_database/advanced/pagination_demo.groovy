package groovy_in_action._13_database.advanced

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
DbUtil.populate(sql)

def qry = 'SELECT * FROM Athlete'
assert sql.rows(qry, 1, 2)*.lastname == [ 'Tergat', 'Khannouchi' ]
assert sql.rows(qry, 3, 2)*.lastname == [ 'da Costa' ]

sql.close()