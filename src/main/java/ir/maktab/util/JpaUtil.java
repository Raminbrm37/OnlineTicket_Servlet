package ir.maktab.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final String PERSISTENCE_UNIT_NAME="NewPersistenceUnit";
    private static EntityManagerFactory emf;
    public static EntityManagerFactory getEntityManagerFactory(){
        if(emf==null){
        return  emf= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);}
        return emf;
    }
    public static void shutDown(){
        if (emf!=null){
        emf.close();
    }}
}
