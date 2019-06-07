
package hu.webtown.liferay.portlet.reference.util.comparator;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceVersionComparator
    extends CustomOrderByComparator<Reference> {

    private static final long serialVersionUID = 2196276226560199818L;

    public static final String ORDER_BY_ASC = "Reference.version ASC";

    public static final String ORDER_BY_DESC = "Reference.version DESC";

    private static final String[] ORDER_BY_FIELDS = {
        "version"
    };

    public ReferenceVersionComparator() {

        this(false);
    }

    public ReferenceVersionComparator(boolean ascending) {

        super(ORDER_BY_ASC, ORDER_BY_DESC, ORDER_BY_FIELDS, ascending);
    }

    @Override
    public int doCompare(Reference o1, Reference o2) {

        if (o1.getVersion() < o2.getVersion()) {

            return -1;
        } else if (o1.getVersion() > o2.getVersion()) {

            return 1;
        }

        return 0;
    }

}
