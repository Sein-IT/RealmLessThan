package sein_it.nl.realmlessthan;

import io.realm.RealmObject;

public class LessThan extends RealmObject {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
