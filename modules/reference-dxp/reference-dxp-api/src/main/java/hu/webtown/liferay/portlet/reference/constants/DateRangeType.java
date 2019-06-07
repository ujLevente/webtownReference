
package hu.webtown.liferay.portlet.reference.constants;

import java.util.HashMap;
import java.util.Map;

public enum DateRangeType {

    ANYTIME(1),
    BETWEEN(0),
    DATE_FROM(2),
    DATE_TO(3);

    private static final Map<Integer, DateRangeType> enumMap =
        new HashMap<Integer, DateRangeType>();

    static {
        enumMap.put(0, BETWEEN);
        enumMap.put(1, ANYTIME);
        enumMap.put(2, DATE_FROM);
        enumMap.put(3, DATE_TO);
    }

    private int primaryKey;

    DateRangeType(int primaryKey) {

        this.primaryKey = primaryKey;
    }

    public static DateRangeType getByPrimaryKey(int primaryKey) {

        return enumMap.get(primaryKey);
    }

    public int getPrimaryKey() {

        return primaryKey;
    }

    @Override
    public String toString() {

        return DateRangeType.class.getSimpleName() + "." + name();
    }

}
