package ru.job4j.taskMap;

import java.util.Calendar;

/**
 * Class for user with overriding equals() for HashMap.
 * @author atrifonov.
 * @since 04.09.2017.
 * @version 1.
 */
public class UserEql extends User {
    public UserEql(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal;
        if(this == obj) {
            equal = true;
        } else {
            if(obj == null || getClass() != obj.getClass()) {
                equal = false;
            } else {
                UserEql userEql = (UserEql) obj;
                boolean nameEqual = getName() != null ? getName().equals(userEql.getName()) : userEql.getName() != null;
                boolean childrenEql = getChildren() == userEql.getChildren();
                boolean birthdayEql = getBirthday() != null ? getBirthday().equals(userEql.getBirthday()) : userEql.getBirthday() != null;
                equal = nameEqual && childrenEql && birthdayEql;
            }
        }
        return equal;
    }
}

