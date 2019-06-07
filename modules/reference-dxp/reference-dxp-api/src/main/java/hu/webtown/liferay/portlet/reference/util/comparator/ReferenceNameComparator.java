
package hu.webtown.liferay.portlet.reference.util.comparator;

import hu.webtown.liferay.portlet.reference.model.Reference;

public class ReferenceNameComparator
    extends CustomOrderByComparator<Reference> {

    private static final long serialVersionUID = -4729380027510200224L;

    public static final String ORDER_BY_ASC = "Reference.name ASC";

    public static final String ORDER_BY_DESC = "Reference.name DESC";

    private static final String[] ORDER_BY_FIELDS = {
        "name"
    };

    public ReferenceNameComparator() {

        this(false);
    }

    public ReferenceNameComparator(boolean ascending) {

        super(ORDER_BY_ASC, ORDER_BY_DESC, ORDER_BY_FIELDS, ascending);
    }

    @Override
    public int doCompare(Reference o1, Reference o2) {

        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
    }

}
