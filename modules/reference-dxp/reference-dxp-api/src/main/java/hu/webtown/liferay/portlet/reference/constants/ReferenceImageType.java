
package hu.webtown.liferay.portlet.reference.constants;

import java.util.HashMap;
import java.util.Map;

public enum ReferenceImageType {

    SMALL_IMAGE(1),
    LARGE_IMAGE(2),
    OTHER_IMAGE(3);

    private int id;
    private static final Map<Integer, ReferenceImageType> enumMap;

    static {
        enumMap = new HashMap<Integer, ReferenceImageType>();

        for (ReferenceImageType referenceImageType : values()) {

            enumMap.put(referenceImageType.getId(), referenceImageType);
        }
    }

    ReferenceImageType(int id) {

        this.id = id;
    }

    public static ReferenceImageType getById(int id) {

        return enumMap.get(id);
    }

    public int getId() {

        return id;
    }

    @Override
    public String toString() {

        return ReferenceImageType.class.getSimpleName() + "." + name();
    }

}
