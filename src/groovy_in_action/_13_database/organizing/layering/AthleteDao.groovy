package groovy_in_action._13_database.organizing.layering

class AthleteDao extends DataAccessObject {

    @Override
    List getFields() {
        return [
            'firstname',   'VARCHAR(64)',
            'lastname',    'VARCHAR(64)',
            'dateOfBirth', 'DATE'
        ]
    }

}
