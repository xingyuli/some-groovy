package groovy_in_action._13_database.crud

import groovy_in_action.util.DbUtil

def sql = DbUtil.create()

sql.execute """
  INSERT INTO Athlete (lastname) VALUES ('da Costa');
"""

sql.execute '''
  UPDATE Athlete SET firstname='Ronaldo' WHERE lastname = 'da Costa';
'''

def updateCount = sql.executeUpdate '''
  UPDATE Athlete SET dateOfBirth = '1970-06-07' WHERE lastname = 'da Costa';
'''
// Checks one row was updated
assert updateCount == 1

sql.close()