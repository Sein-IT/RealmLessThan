package sein_it.nl.realmlessthan;

import android.app.Application;
import android.test.ApplicationTestCase;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ApplicationTest extends ApplicationTestCase<Application> {
    RealmConfiguration realmConfig;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        String dbName = "lessthan.realm";
        realmConfig = new RealmConfiguration.Builder(getContext())
                .name(dbName)
                .build();
        Realm.deleteRealm(realmConfig);
    }

    public void testLessThan() {
        Realm realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();
        long id = -1;
        for (int i = 0; i < 10; i++) {
            LessThan lt = new LessThan();
            lt.setId(id--);

            realm.copyToRealm(lt);
        }
        realm.commitTransaction();

        RealmResults<LessThan> all = realm.where(LessThan.class).lessThan("id", 0).findAll();
//        RealmResults<LessThan> all = realm.where(LessThan.class).between("id", -100000, 0).findAll();
        assertEquals(10, all.size());

        realm.close();
    }

}