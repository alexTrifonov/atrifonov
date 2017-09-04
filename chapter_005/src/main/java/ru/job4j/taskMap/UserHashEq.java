package ru.job4j.taskMap;

import java.util.Calendar;
/**
 * Class for user with overriding equals() and hashCode() for HashMap.
 * @author atrifonov.
 * @since 04.09.2017.
 * @version 1.
 */
public class UserHashEq extends User {
    public UserHashEq(String name, int children, Calendar birthday) {
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
                UserHashEq userHashEq = (UserHashEq) obj;
                boolean nameEqual = getName() != null ? getName().equals(userHashEq.getName()) : userHashEq.getName() != null;
                boolean childrenEql = getChildren() == userHashEq.getChildren();
                boolean birthdayEql = getBirthday() != null ? getBirthday().equals(userHashEq.getBirthday()) : userHashEq.getBirthday() != null;
                equal = nameEqual && childrenEql && birthdayEql;
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() * 31 + getChildren() + getBirthday().hashCode() * 31;
    }
}
