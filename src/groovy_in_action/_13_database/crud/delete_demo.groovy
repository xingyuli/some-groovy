package groovy_in_action._13_database.crud

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()
// Populates using helper method
DbUtil.populate(sql)

// Checks initially three rows
assert sql.firstRow('SELECT COUNT(*) as num FROM Athlete').num == 3

sql.execute "DELETE FROM Athlete WHERE lastname = 'Tergat'"

// Two rows left after delete
assert sql.firstRow('SELECT COUNT(*) as num FROM Athlete').num == 2

sql.close()
